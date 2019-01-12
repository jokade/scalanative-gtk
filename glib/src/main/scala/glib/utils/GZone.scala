package glib.utils

import glib.GSlice

import scala.annotation.tailrec
import scala.scalanative.native._

trait GZone extends Zone {
  def totalSize: CSize
  def statInfo: String
  def ref(): Unit
  def unref(): Unit
}
object GZone {

  var collectStats: Boolean = false
  var nodeAllocThreshold: CSize = 256
  var smallBlockSize: CSize = 4*1024

  lazy val zone: GZone = new PoolZone(nodeAllocThreshold,smallBlockSize)

  def apply[T](f: Zone => T): T = {
    zone.ref()
    try f(zone)
    finally zone.unref()
  }


  private final class PoolZone(nodeAllocThreshold: CSize, smallBlockSize: CSize) extends GZone {
    assert(nodeAllocThreshold<=smallBlockSize,"smallBlockSize must be >= nodeAllocThreshold")
    private var _level = 0

    private var _maxLevel = 0
    private var _numSmallAllocs = 0
    private var _numNodeAllocs = 0
    private var _maxSmallAllocSize: CSize = 0
    private var _maxNodeAllocSize: CSize = 0
    private var _totalSmallAllocSize: CSize = 0
    private var _totalNodeAllocSize: CSize = 0
    private var _minNodeAllocSize: CSize = 0
    private var _numBlockAllocs = 0
    private var _usedBlockSize: CSize = smallBlockSize
    private var _maxUsedBlockSize: CSize = smallBlockSize

    private var _freeBlocks: Block = null
    private var _blocks = allocBlock(null)
    private var _nodes: Node = null

    override def totalSize: CSize = _nodes match {
      case null => smallBlockSize
      case n => smallBlockSize + n.totalSize(0)
    }

    override def statInfo: String =
      s"""PoolZone Statistics:
         |-------------------
         |
         |        small block size: ${smallBlockSize} bytes
         |    node alloc threshold: ${nodeAllocThreshold} bytes
         |
         |              max. level: ${_maxLevel}
         |
         |          # small allocs: ${_numSmallAllocs}
         |  total small alloc size: ${_totalSmallAllocSize} bytes
         |   max. small alloc size: ${_maxSmallAllocSize} bytes
         |   mean small alloc size: ${if(_numSmallAllocs==0) 0 else _totalSmallAllocSize / _numSmallAllocs} bytes
         |
         |            # new blocks: ${_numBlockAllocs}
         |    total size of blocks: ${_maxUsedBlockSize} bytes
         |
         |           # node allocs: ${_numNodeAllocs}
         |   total node alloc size: ${_totalNodeAllocSize} bytes
         |    max. node alloc size: ${_maxNodeAllocSize} bytes
         |    min. node alloc size: ${_minNodeAllocSize} bytes
         |    mean node alloc size: ${if(_numNodeAllocs==0) 0 else _totalNodeAllocSize / _numNodeAllocs} bytes
       """.stripMargin

    override def ref(): Unit = {
      _level += 1
      if(collectStats || _level > _maxLevel)
        _maxLevel = _level
    }

    override def unref(): Unit =
      if(_level==0)
        throw new RuntimeException("cannot unref() PoolZone: level already 0!")
      else {
        _level -= 1
        freeNodes()
        freeBlocks()
        if(_nodes != null)
          throw new RuntimeException(s"inconsistent node PoolZone: node alloc pool should be zero at level=0, got: ${_nodes}")
      }

    override def alloc(size: CSize): Ptr[Byte] =
      if(size <= nodeAllocThreshold)
        allocSmall(size)
      else
        allocNode(size)



    private def allocSmall(size: CSize): Ptr[Byte] = {
      if(size > _blocks.freeSize)
        _blocks = allocBlock(_blocks)
      if(collectStats) {
        _numSmallAllocs += 1
        _totalSmallAllocSize += size
        if(size > _maxSmallAllocSize)
          _maxSmallAllocSize = size
      }
      val p = _blocks.nextPtr
      _blocks.nextPtr += size
      _blocks.freeSize -= size
      p
    }

    private def allocNode(size: CSize): Ptr[Byte] = {
      if(collectStats) {
        _numNodeAllocs += 1
        _totalNodeAllocSize += size
        if(size > _maxNodeAllocSize)
          _maxNodeAllocSize = size
        if(_minNodeAllocSize == 0)
          _minNodeAllocSize = size
        else if(size < _minNodeAllocSize)
          _minNodeAllocSize = size
      }
      _nodes = Node(_level,GSlice.alloc(size),size,_nodes)
      _nodes.ptr
    }

    private def allocBlock(nextBlock: Block): Block = {
      val newBlock =
        if(_freeBlocks != null) {
          val block = _freeBlocks
          _freeBlocks = _freeBlocks.nextBlock
          block.nextBlock = nextBlock
          block
        }
        else {
          if(collectStats) {
            _numBlockAllocs += 1
          }
          Block(_level,smallBlockSize,nextBlock)
        }
      if(collectStats) {
        _usedBlockSize += smallBlockSize
        if(_usedBlockSize >= _maxUsedBlockSize)
          _maxUsedBlockSize = _usedBlockSize
      }
      newBlock
    }

    private def freeBlocks(): Unit = {
      while(_blocks.nextBlock != null && (_blocks.level > _level || _level ==0)) {
        val free = _blocks
        free.nextBlock = _freeBlocks
        free.reset()
        _blocks = free.nextBlock
        if(collectStats)
          _usedBlockSize -= smallBlockSize
        _blocks = _blocks.nextBlock
      }
    }

    private def freeNodes(): Unit = if(_nodes!=null) {
      var node = _nodes
      while(node != null && node.level > _level) {
        GSlice.free1(node.size,node.ptr)
        node = node.next
      }
      _nodes = node
    }
  }

  private case class Node(level: Int, ptr: Ptr[Byte], size: CSize, next: Node) {
    @tailrec
    final def totalSize(prevSize: CSize): CSize = next match {
      case null => size + prevSize
      case tail => tail.totalSize(size + prevSize)
    }
  }

  private class Block(val level: Int, val ptr: Ptr[Byte], val size: CSize, var nextPtr: Ptr[Byte], var freeSize: CSize, var nextBlock: Block) {
    def free(): Unit = GSlice.free1(size,ptr)
    def reset(): Unit = {
      nextPtr = ptr
      freeSize = size
    }
  }
  private object Block {
    def apply(level: Int, size: CSize, next: Block): Block = {
      val ptr = GSlice.alloc(size)
      new Block(level,ptr,size,ptr,size,next)
    }
  }
  /*
  private class PoolZone(nodeAllocThreshold: CSize, smallBlockSize: CSize) extends GZone {
    private var _level= 0
    private var _block = GSlice.alloc(smallBlockSize)
    private var _next = _block
    private var _free: CSize = smallBlockSize
    private var _nodes: Node = null

    private var _maxLevel = 0
    private var _numSmallAllocs = 0
    private var _numSmallAllocOverflow = 0
    private var _numNodeAllocs = 0
    private var _maxSmallAllocSize: CSize = 0
    private var _maxNodeAllocSize: CSize = 0
    private var _totalSmallAllocSize: CSize = 0
    private var _totalNodeAllocSize: CSize = 0
    private var _totalSmallOverflowSize: CSize = 0
    private var _minNodeAllocSize: CSize = 0

    override def totalSize: CSize = _nodes match {
      case null => smallBlockSize
      case n => smallBlockSize + n.totalSize(0)
    }

    override def statInfo: String =
      s"""PoolZone Statistics:
         |-------------------
         |
         |        small block size: ${smallBlockSize} bytes
         |    node alloc threshold: ${nodeAllocThreshold} bytes
         |
         |              max. level: ${_maxLevel}
         |
         |          # small allocs: ${_numSmallAllocs}
         |  total small alloc size: ${_totalSmallAllocSize} bytes
         |   max. small alloc size: ${_maxSmallAllocSize} bytes
         |   mean small alloc size: ${if(_numSmallAllocs==0) 0 else _totalSmallAllocSize / _numSmallAllocs} bytes
         |
         | # small alloc overflows: ${_numSmallAllocOverflow}
         |     total overflow size: ${_totalSmallOverflowSize} bytes
         |      mean overflow size: ${if(_numSmallAllocOverflow==0) 0 else _totalSmallOverflowSize/_numSmallAllocOverflow} bytes
         |
         |           # node allocs: ${_numNodeAllocs}
         |   total node alloc size: ${_totalNodeAllocSize} bytes
         |    max. node alloc size: ${_maxNodeAllocSize} bytes
         |    min. node alloc size: ${_minNodeAllocSize} bytes
         |    mean node alloc size: ${if(_numNodeAllocs==0) 0 else _totalNodeAllocSize / _numNodeAllocs} bytes
       """.stripMargin

    override def ref(): Unit = {
      _level += 1
      if(collectStats || _level > _maxLevel)
        _maxLevel = _level
    }

    override def unref(): Unit =
      if(_level==0)
        throw new RuntimeException("cannot unref() PoolZone: level already 0!")
      else {
        _level -= 1
        if(_level==0) {
          freeNodes()
          freeBlock()
          if(_nodes != null)
            throw new RuntimeException(s"inconsistent node PoolZone: node alloc pool should be zero at level=0, got: ${_nodes}")
        }
      }

    override def alloc(size: CSize): Ptr[Byte] =
      if(size <= nodeAllocThreshold)
        allocSmall(size)
      else
        allocNode(size)


    private def allocSmall(size: CSize): Ptr[Byte] =
      if(size <= _free) {
        if(collectStats) {
          _numSmallAllocs += 1
          _totalSmallAllocSize += size
          if(size > _maxSmallAllocSize)
            _maxSmallAllocSize = size
        }
        val p = _next
        _next += size
        _free -= size
        p
      }
      else {
        if(collectStats) {
          _numSmallAllocOverflow += 1
          _totalSmallOverflowSize += size
        }
        allocNode(size)
      }

    private def allocNode(size: CSize): Ptr[Byte] = {
      if(collectStats) {
        _numNodeAllocs += 1
        _totalNodeAllocSize += size
        if(size > _maxNodeAllocSize)
          _maxNodeAllocSize = size
        if(_minNodeAllocSize == 0)
          _minNodeAllocSize = size
        else if(size < _minNodeAllocSize)
          _minNodeAllocSize = size
      }
      _nodes = Node(_level,GSlice.alloc(size),size,_nodes)
      _nodes.ptr
    }

    private def freeBlock(): Unit = {
      _next = _block
      _free = smallBlockSize
    }

    private def freeNodes(): Unit = if(_nodes!=null) {
      var node = _nodes
      while(node != null && node.level > _level) {
        GSlice.free1(node.size,node.ptr)
        node = node.next
      }
      _nodes = node
    }
  }

  private case class Node(level: Int, ptr: Ptr[Byte], size: CSize, next: Node) {
    @tailrec
    final def totalSize(prevSize: CSize): CSize = next match {
      case null => size + prevSize
      case tail => tail.totalSize(size + prevSize)
    }
  }
*/
  class StaticZoneImpl(val totalSize: CSize, collectStats: Boolean) extends GZone {
    private var _refs = 0
    private var _block = stdlib.malloc(totalSize)
    private var _next = _block
    private var _free = totalSize

    private var _numAllocs: CSize = 0
    private var _totalAllocSize: CSize = 0
    private var _minAllocSize: CSize = 0
    private var _maxAllocSize: CSize = 0
    private var _maxLevel: Int = 0


    override def statInfo: String =
      s"""GZone Statistics:
         |
         |      # allocations: ${_numAllocs}
         |         max. level: ${_maxLevel}
         |    min. alloc size: ${_minAllocSize} bytes
         |    max. alloc size: ${_maxAllocSize} bytes
         |    mean alloc size: ${_totalAllocSize/_numAllocs} bytes
         |  total alloc. size: ${_totalAllocSize} bytes
       """.stripMargin

    final def ref(): Unit = {
      _refs += 1
      if(collectStats && _refs > _maxLevel)
        _maxLevel = _refs
    }

    override final def alloc(size: CSize): Ptr[Byte] = {
      if(size > _free)
        throw new RuntimeException(s"SingleZone out of memory: cannot allocate $size bytes (free: ${_free})")
//        realloc(size)
      if(collectStats) {
        _numAllocs += 1
        _totalAllocSize += size
        if(size > _maxAllocSize)
          _maxAllocSize = size
        if(size == 0 || size < _minAllocSize)
          _minAllocSize = size
      }
      val ptr = _next
      _next += size
      _free -= size
      ptr
    }

    final def unref(): Unit = {
      println("unref() "+_refs)
      if(_refs==0)
        throw new RuntimeException("cannot unref() SingleZone: ref count already 0!")
      else
        _refs -= 1
      if(_refs==0) {
        _next = _block
        _free = totalSize

      }
    }
/*
    private def realloc(requiredSize: CSize): Unit = {
      val totalRequiredSize = _totalSize - _free + requiredSize
      val offset = _next - _block
      val newAllocSize =
        if( totalRequiredSize <= 0x200 )
          0x400
        else if( totalRequiredSize <= 0x400 )
          0x800
        else if( totalRequiredSize <= 0x800 )
          0x1000
        else if( totalRequiredSize <= 0x1000 )
          0x2000
        else if( totalRequiredSize <= 0x2000 )
          0x4000
        else if( totalRequiredSize <= 0x4000 )
          0x8000
        else if( totalRequiredSize <= 0x8000 )
          0x10000
        else if( totalRequiredSize <= 0x10000 )
          0x20000
        else if( totalRequiredSize <= 0x20000 )
          0x40000
        else if( totalRequiredSize <= 0x40000 )
          0x80000
        else if( totalRequiredSize <= 0x80000 )
          0x100000
        else if( totalRequiredSize <= 0x100000 )
          0x200000
        else if( totalRequiredSize <= 0x200000 )
          0x400000
        else if( totalRequiredSize <= 0x400000 )
          0x800000
        else if( totalRequiredSize <= 0x800000 )
          0x1000000
        else if( totalRequiredSize <= 0x1000000 )
          0x2000000
        else if( totalRequiredSize <= 0x2000000 )
          0x4000000
        else if( totalRequiredSize <= 0x4000000 )
          0x8000000
        else if( totalRequiredSize <= 0x8000000 )
          0x10000000
        else if( totalRequiredSize <= 0x10000000 )
          0x20000000
        else if( totalRequiredSize <= 0x20000000 )
          0x40000000
        else if ( totalRequiredSize <= 0x4000000 )
          0x80000000
        else
          throw new RuntimeException(s"cannot resize SingleZone to ${totalRequiredSize} bytes")

        _block = stdlib.realloc(_block,totalRequiredSize) match {
          case null => throw new RuntimeException(s"SingleZone: could not reallocate memory for $totalRequiredSize bytes")
          case ptr => ptr
        }

        _next = _block + offset
    }
    */
  }
}

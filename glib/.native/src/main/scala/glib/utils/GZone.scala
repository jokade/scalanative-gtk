package glib.utils

import scala.scalanative._
import scala.scalanative.interop.PoolZone
import scala.scalanative.libc.stdlib
import scala.scalanative.unsafe.{Ptr, _}

trait GZone extends Zone {
  def totalSize: CSize
  def statInfo: String
  def ref(): Unit
  def unref(): Unit
}
object GZone {

  var defaultCollectStats: Boolean = PoolZone.defaultCollectStats
  var defaultAllocThreshold: CSize = PoolZone.defaultAllocThreshold
  var defaultBlockSize: CSize = PoolZone.defaultBlockSize

  lazy val allocator: PoolZone = new PoolZone.PoolZoneImpl(defaultAllocThreshold,defaultBlockSize,defaultCollectStats)//new PoolZone(nodeAllocThreshold,smallBlockSize)

  def apply[T](f: Zone => T): T = {
    allocator.ref()
    try f(allocator)
    finally allocator.unref()
  }


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


    override def close(): Unit = ???
    override def isClosed: CBool = ???

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

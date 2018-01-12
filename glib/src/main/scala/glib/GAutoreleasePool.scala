// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

class GAutoreleasePool(private var _allocated: List[GAllocated]) {
  def register(obj: GAllocated): Unit = _allocated = obj::_allocated
  def releaseAll(): Unit = {
    _allocated.foreach(_.free())
    _allocated = Nil
  }
}

object GAutoreleasePool {
  def apply[R](block: GAutoreleasePool => R): R = {
    val pool = new GAutoreleasePool(Nil)
    val res = block(pool)
    pool.releaseAll()
    res
  }

}

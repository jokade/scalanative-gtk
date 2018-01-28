// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.convert

import java.util

import glib._

import scala.collection.{AbstractIterable, AbstractIterator, AbstractSeq, mutable}
import scalanative.native._

object Wrappers {
  case class NullTerminatedStringArray(array: Ptr[CString], length: Int)
    extends AbstractSeq[CString] with IndexedSeq[CString] with GSeq[CString] {
    def apply(idx: Int): CString = array(idx)
    override def free(): Unit = {
      GLib.strfreev(array)
    }
  }

  abstract class MutableList[T] extends mutable.AbstractSeq[T] with GAllocated {
    val raw: GListLike
    val valueWrapper: GWrapper[T]
    final override def free(): Unit = raw.free
    final override def iterator: Iterator[T] = new GListIterator(raw.ptr,valueWrapper)
    final override def apply(idx: Int): T = valueWrapper.wrap(!raw.nth(idx.toUInt)._1)
    final override def update(i: CInt, a: T): Unit = ???
    final override def length: Int = raw.length().toInt
    final def prepend(elem: T): this.type = {
      raw.prepend(valueWrapper.unwrap(elem))
      this
    }
    @inline final def +=:(elem: T): this.type = prepend(elem)
    final def append(elem: T): this.type = {
      raw.append(valueWrapper.unwrap(elem))
      this
    }
    @inline final def +=(elem: T): this.type = append(elem)
    final def ++=(xs: TraversableOnce[T]): this.type = {
      raw.appendAll(xs)(valueWrapper)
      this
    }
  }

  case class GSListWrapper[T](raw: GSList, valueWrapper: GWrapper[T]) extends MutableList[T]

  class GListIterator[T](private var _next: Ptr[GSListStruct], valueWrapper: GWrapper[T]) extends AbstractIterator[T] {
    override def hasNext: Boolean = _next != null

    override def next(): T = {
      val data = !_next._1
      _next = (!_next._2).cast[Ptr[GSListStruct]]
      valueWrapper.wrap(data)
    }
  }
}


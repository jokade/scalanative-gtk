// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.convert

import java.util

import glib._

import scala.collection.{AbstractIterable, AbstractIterator, AbstractSeq, mutable}
import scalanative._
import cobj._
import unsafe._
import unsigned._

object Wrappers {
  //  case class NullTerminatedStringArray(array: Ptr[CString], length: Int)
  //    extends AbstractSeq[CString] with IndexedSeq[CString] with GSeq[CString] {
  //    def apply(idx: Int): CString = array(idx)
  //    override def free(): Unit = {
  //      GLib.strfreev(array)
  //    }
  //  }

  class ListIterator[T](private var _next: Ptr[GSListStruct], valueWrapper: CObjectWrapper[T]) extends AbstractIterator[T] {
    override def hasNext: Boolean = _next != null

    override def next(): T = {
      val data = _next._1
      _next = (_next._2).asInstanceOf[Ptr[GSListStruct]]
      valueWrapper.wrap(data)
    }
  }

  class ListWrapper[T](val wrapped: GListLike[T])(implicit val valueWrapper: CObjectWrapper[T]) extends AbstractSeq[T] with GAllocated {

    override def __ptr: Ptr[Byte] = wrapped.__ptr

//    override def update(idx: Int, elem: T): Unit = ???

    override def length: Int = wrapped.size

    override def apply(idx: Int): T = wrapped.nthData(idx.toUInt)

    override def free(): Unit = wrapped.free()

    override def iterator: Iterator[T] = new ListIterator[T](wrapped.__ptr.asInstanceOf[Ptr[GSListStruct]],valueWrapper)
  }


}


// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

//import glib.convert.Wrappers.GListWrapper

import glib.convert.Wrappers.GListWrapper

import scalanative.native._
import cobj._
import scala.scalanative.native.cobj.runtime.CObjObject

/**
 * A linked list that can be iterated over in both directions.
 *
 * @note In deviation from the usual scalanative-gtk semantics, the reference
 *       hold by this wrapper is mutable, i.e. it is updated in place by methods
 *       that modify the underlying Gtk list (e.g. [[append()]].
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-Doubly-Linked-Lists.html]]
 */
@CObj
//@CObj.Mutable
class GList extends CObjObject with GListLike {
  /**
   * Creates an empty list.
   */
//  def this() = this(null)

  @inline def free(): Unit = extern

  @inline def ptr: Ptr[GSListStruct] = __ptr.cast[Ptr[GSListStruct]]

  @updatesThis
  @inline def append(data: gpointer): GList = extern

  @updatesThis
  @inline def prepend(data: gpointer): GList = extern

  @inline def length(): guint = extern //__ext.g_slist_length(__ref)

  @inline def nth(idx: guint): Ptr[GSListStruct] = extern

  @updatesThis
  @inline def reverse(): GList = extern

  @inline def foreach(func: GFunc, userData: gpointer): Unit = extern

  def appendAll[T](xs: TraversableOnce[T])(implicit valueWrapper: CObjWrapper[T]): GList = ???
  /*
  def appendAll[T](xs: TraversableOnce[T])(implicit valueWrapper: CObjWrapper[T]): GList = {
    val list = new GList(null)
    xs.foreach(p => list.prepend(valueWrapper.unwrap(p)))
    list.reverse()
    if(__ptr == null)
      __ptr = list.__ptr
    else {
      val last = lastPtr
      !lastPtr._2 = list.ptr.cast[Ptr[Byte]]
      !list.ptr.cast[Ptr[GListStruct]]._3 = last.cast[Ptr[Byte]]
    }
    this
  }
*/
  @inline def asScala[T](implicit valueWrapper: CObjWrapper[T]): GListWrapper[T] = GListWrapper(this,valueWrapper)
}

object GList {
  def apply[T](values: T*)(implicit valueWrapper: CObjWrapper[T]): GList = {
    val list = new GList(null)
    values.foreach(p => list.prepend(valueWrapper.unwrap(p)))
    list.reverse()
  }
}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scala.scalanative.native.CObj.updatesThis
import scalanative.native._

/**
 * A linked list that can be iterated over in both directions.
 *
 * @note In deviation from the usual scalanative-gtk semantics, the reference
 *       hold by this wrapper is mutable, i.e. it is updated in place by methods
 *       that modify the underlying Gtk list (e.g. [[append()]].
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-Doubly-Linked-Lists.html]]
 */
@CObj.Mutable
final class GList(var __ref: CObj.Ref[GListStruct]) extends CObj.CRef[GListStruct] with GListLike {
  /**
   * Creates an empty list.
   */
  def this() = this(null)

  @inline def free(): Unit = extern

  @inline def ptr: Ptr[GSListStruct] = __ref.cast[Ptr[GSListStruct]]

  @updatesThis
  @inline def append(data: gpointer): GList = extern

  @updatesThis
  @inline def prepend(data: gpointer): GList = extern

  @inline def length(): guint = extern //__ext.g_slist_length(__ref)

  @inline def nth(idx: guint): Ptr[GSListStruct] = extern

  @updatesThis
  @inline def reverse(): GList = extern

  @inline def foreach(func: GFunc, userData: gpointer): Unit = extern

  def appendAll[T](xs: TraversableOnce[T])(implicit valueWrapper: GWrapper[T]): GList = {
    val list = new GList()
    xs.foreach(p => list.prepend(valueWrapper.unwrap(p)))
    list.reverse()
    if(__ref == null)
      __ref = list.__ref
    else {
      val last = lastPtr
      !lastPtr._2 = list.ptr.cast[Ptr[Byte]]
      !list.ptr.cast[Ptr[GListStruct]]._3 = last.cast[Ptr[Byte]]
    }
    this
  }

}

object GList {
  def apply[T](values: T*)(implicit valueWrapper: GWrapper[T]): GList = {
    val list = new GList()
    values.foreach(p => list.prepend(valueWrapper.unwrap(p)))
    list.reverse()
  }
}

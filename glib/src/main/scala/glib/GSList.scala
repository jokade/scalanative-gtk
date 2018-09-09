// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import glib.convert.Wrappers.GSListWrapper

import scalanative.native._
import cobj._

/**
 * A singly-linked list.
 *
 * @note In deviation from the usual scalanative-gtk semantics, the reference
 *       hold by this wrapper is mutable, i.e. it is updated in place by methods
 *       that modify the underlying Gtk list (e.g. [[append()]].
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-Singly-Linked-Lists.html]]
 */
@CObj.Mutable(prefix = "g_slist_")
final class GSList(var __ref: Ref[GSListStruct]) extends CRef[GSListStruct] with GListLike {
  /**
   * Creates an empty list.
   */
  def this() = this(null)

  def free(): Unit = extern

  @inline def ptr: Ptr[GSListStruct] = __ref.cast[Ptr[GSListStruct]]

  @updatesThis
  @inline def append(data: gpointer): GSList = extern


  @updatesThis
  @inline def prepend(data: gpointer): GSList = extern

  @inline def length(): guint = extern

  @inline def nth(idx: guint): Ptr[GSListStruct] = extern

  @updatesThis
  @inline def reverse(): GSList = extern

  @inline def foreach(func: GFunc, userData: gpointer): Unit = extern

  @inline def asScala[T](implicit valueWrapper: GWrapper[T]): GSListWrapper[T] = GSListWrapper(this,valueWrapper)


  def appendAll[T](xs: TraversableOnce[T])(implicit valueWrapper: GWrapper[T]): GSList = {
    val list = new GSList()
    xs.foreach(p => list.prepend(valueWrapper.unwrap(p)))
    list.reverse()
    if(__ref == null)
      __ref = list.__ref
    else
      !lastPtr._2 = list.ptr.cast[Ptr[Byte]]
    this
  }


  /**
   * Returns the head element in this list, or null.
   */
//  def head: gpointer =
//    if(__ref ==null) null
//    else !__ref.cast[Ptr[GSListStruct]]._1

  /**
   * Returns the tail of this list, or null
   * @return
   */
//  def tail: GSList =
//    if(__ref == null) null
//    else new GSList((!__ref.cast[Ptr[GSListStruct]]._2).cast[CObj.Ref[GSListStruct]])


}

object GSList {
  def apply[T](values: T*)(implicit valueWrapper: GWrapper[T]): GSList = {
    val list = new GSList()
    values.foreach(p => list.prepend(valueWrapper.unwrap(p)))
    list.reverse()
  }
}

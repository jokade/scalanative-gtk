// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import de.surfice.smacrotools.debug

import scalanative._
import unsafe._
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
@CObj(prefix = "g_slist_")
class GSList[T<:CObject] extends GListLike[T] {

  override def append(data: T)(implicit wrapper: CObjectWrapper[T]): GSList[T] = extern

  override def prepend(data: T)(implicit wrapper: CObjectWrapper[T]): GSList[T] = extern

  override def length: guint = extern

  override def nthData(idx: guint)(implicit wrapper: CObjectWrapper[T]): T = extern

  override def reverse(): GSList[T] = extern

  override def insert(data: T, position: gint)(implicit wrapper: CObjectWrapper[T]): GSList[T] = extern

  override def appendAll(xs: TraversableOnce[T]): GSList[T] = new GSList( GSList.appendAll(this.__ptr,xs) )

  override def free(): Unit = extern
}

object GSList {
  private def appendAll[T<:CObject](list: Ptr[Byte], xs: TraversableOnce[T]): Ptr[Byte] = {
    val rev = __ext.g_slist_reverse(list)
    val upd = xs.foldLeft(rev)( (list,x) => __ext.g_slist_prepend(list,x.__ptr) )
    __ext.g_slist_reverse(upd)
  }

  private lazy val _empty = new GSList[Nothing](null)
  def empty[T<:CObject]: GSList[T] = _empty.asInstanceOf[GSList[T]]

  def apply[T<:CObject](xs: TraversableOnce[T]): GSList[T] = new GSList( appendAll(null,xs) )
}

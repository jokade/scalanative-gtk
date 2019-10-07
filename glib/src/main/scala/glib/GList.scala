// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import glib.convert.Wrappers.ListWrapper

import scala.scalanative._
import scala.scalanative.cobj._
import scala.scalanative.unsafe._

/**
 * A linked list that can be iterated over in both directions.
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-Doubly-Linked-Lists.html]]
 */
@CObj
class GList[T<:CObject] extends GListLike[T] {

  def append(data: T)(implicit wrapper: CObjectWrapper[T]): GList[T] = extern

  def prepend(data: T)(implicit wrapper: CObjectWrapper[T]): GList[T] = extern

  def length: guint = extern

  def free(): Unit = extern

  def nthData(idx: guint)(implicit wrapper: CObjectWrapper[T]): T = extern

  def reverse(): GList[T] = extern

  def insert(data: T, position: gint)(implicit wrapper: CObjectWrapper[T]): GList[T] = extern

  def appendAll(xs: TraversableOnce[T]): GList[T] = new GList( GList.appendAll(this.__ptr,xs) )

  def asScala(implicit valueWrapper: CObjectWrapper[T]): ListWrapper[T] = new ListWrapper[T](this)
}

object GList {

  private def appendAll[T<:CObject](list: Ptr[Byte], xs: TraversableOnce[T]): Ptr[Byte] = {
    val rev = __ext.g_list_reverse(list)
    val upd = xs.foldLeft(rev)( (list,x) => __ext.g_list_prepend(list,x.__ptr) )
    __ext.g_list_reverse(upd)
  }

  private lazy val _empty = new GList[Nothing](null)
  def empty[T<:CObject]: GList[T] = _empty.asInstanceOf[GList[T]]

  def apply[T<:CObject](xs: TraversableOnce[T]): GList[T] = new GList( appendAll(null,xs) )
}


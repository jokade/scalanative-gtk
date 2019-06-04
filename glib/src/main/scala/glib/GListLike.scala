// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scala.scalanative._
import unsafe._
import unsigned._
import cobj._
import scala.scalanative.cobj.CObj.CObjWrapper

trait GListLike[T] extends GAllocated {

  def __ptr: Ptr[Byte]

  /**
   * Appends a new element to the end of the list.
   *
   * @param data
   */
  def append(data: T)(implicit wrapper: CObjectWrapper[T]): GListLike[T]

  /**
   * Adds a new element to the start of the list.
   *
   * @note Returns the new head of the list.
   *
   * @param data the data for the new element
   */
  def prepend(data: T)(implicit wrapper: CObjectWrapper[T]): GListLike[T]

  /**
   * Returns the number of elements in this list.
   *
   * @note This function iterates over the whole list to count its elements. To check whether the list is non-empty,
   *       use [[nonEmpty]].
   */
  def length: guint

  /**
   * Returns the element at the gitven position.
   *
   * @param idx position of the element, counting from 0.
   * @return the specified element, or null
   */
  def nthData(idx: guint)(implicit wrapper: CObjectWrapper[T]): T

  /**
   * Reverses this list.
   *
   * @note This method updates this wrapper instance with the new reference to the underlying GLib list.
   */
  def reverse(): GListLike[T]

  /**
   * Inserts a new element into the list at the given position.
   *
   * @param data element to be added
   * @param position the position to insert the element. If this is negative, or is larger than the number of elements in the list, the new element is added on to the end of the list.
   * @return The (possibly changed) start of the list.
   */
  def insert(data: T, position: gint)(implicit wrapper: CObjectWrapper[T]): GListLike[T]

  /**
   * Calls a function for each element of this list.
   *
   * @param func function to call with each element's data (first arg to `func`)
   * @param userData user data to pass as second arg to `func`
   */
//  def foreach(func: GFunc, userData: gpointer): Unit

  /**
   * Calls the provided closure for every data element in this list.
   * The data element is wrapped using the implicitly provided CObjWrapper.
   *
   * @note Don't store the wrapped element passed to `f` for access outside of `f`!
   *       The wrapper my be reused to improve perfomance.
   *
   * @param f
   * @param wrapper used to wrap data elements before they are passed to `f`
   */
  def foreach(f: T=>_)(implicit wrapper: CObjectWrapper[T]): Unit = {
    var p = __ptr.asInstanceOf[Ptr[GSListStruct]]
    while(p != null) {
      f(wrapper.wrap(p._1))
      p = p._2.asInstanceOf[Ptr[GSListStruct]]
    }
  }

  def appendAll(xs: TraversableOnce[T]): GListLike[T]

  @inline final def size: Int = length.toInt
  @inline final def isEmpty: Boolean = __ptr == null
  @inline final def nonEmpty: Boolean = __ptr != null

  /**
   * Returns the specified element, wrapped by the implicitly provided CObjectWrapper.
   *
   * @param idx
   * @param wrapper
   */
  @inline def apply(idx: Int)(implicit wrapper: CObjectWrapper[T]): T = nthData(idx.toUInt)

  /**
   * Appends a single element, unwrapped using the implicitly provided CObjWrapper.
   * @param x element to append
   * @param valueWrapper CObjWrapper used for unwrapping of `x`
   * @tparam T
   */
//  @inline final def +=[T](x: T)(implicit valueWrapper: CObjWrapper[T]): GListLike = append(valueWrapper.unwrap(x))

  /**
   * Prepends a single element, unwrapped using the implicitly provided CObjWrapper.
   * @param x element to prepend
   * @param valueWrapper CObjWrapper used to unwrap `x`
   * @tparam T
   */
//  def +=:[T](x: T)(implicit valueWrapper: CObjWrapper[T]): GListLike = prepend(valueWrapper.unwrap(x))
}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scala.scalanative.native._

trait GListLike extends GAllocated {
  def __ref: Any

  /**
   * Appends a new element to the end of the list.
   * Note that append() has to traverse the entire list to find the end, which is inefficient when adding multiple elements.
   * A common idiom to avoid the inefficiency is to prepend the elements and reverse the list when all elements have been added.
   *
   * @note This method updates this wrapper instance with the new reference to the underlying GLib list.
   *
   * @param data
   *
   */
  def append(data: gpointer): GListLike

  /**
   * Adds a new element to the start of the list.
   *
   * @note This method updates this wrapper instance with the new reference to the underlying GLib list.
   *
   * @param data the data for the new element
   */
  def prepend(data: gpointer): GListLike

  /**
   * Returns the number of elements in this list.
   *
   * @note This function iterates over the whole list to count its elements. To check whether the list is non-empty,
   *       use [[nonEmpty]].
   */
  def length(): guint

  /**
   * Returns the element at the gitven position.
   *
   * @param idx position of the element, counting from 0.
   * @return the specified element, or null
   */
  def nth(idx: guint): Ptr[GSListStruct]

  /**
   * Reverses this list.
   *
   * @note This method updates this wrapper instance with the new reference to the underlying GLib list.
   */
  def reverse(): GListLike

  /**
   * Calls a function for each element of this list.
   *
   * @param func function to call with each element's data (first arg to `func`)
   * @param userData user data to pass as second arg to `func`
   */
  def foreach(func: GFunc, userData: gpointer): Unit

  /**
   * Calls the provided closure for every data element in this list.
   * The data element is wrapped using the implicitly provided GWrapper.
   *
   * @note Don't store the wrapped element passed to `f` for access outside of `f`!
   *       The wrapper my be reused to improve perfomance.
   *
   * @param f
   * @param valueWrapper used to wrap data elements before they are passed to `f`
   */
  final def foreach[T](f: T=>_)(implicit valueWrapper: GWrapper[T]): Unit = {
    var p = ptr
    while(p != null) {
      f(valueWrapper.wrap(!p._1))
      p = !p._2.cast[Ptr[Ptr[GSListStruct]]]
    }
  }

  def appendAll[T](xs: TraversableOnce[T])(implicit valueWrapper: GWrapper[T]): GListLike

  @inline final def size: Int = length().toInt
  @inline final def isEmpty: Boolean = __ref == null
  @inline final def nonEmpty: Boolean = __ref != null

  def ptr: Ptr[GSListStruct]

  final def lastPtr: Ptr[GSListStruct] =
    if(__ref==null) null
    else{
      var p = ptr
      while((!p._2) != null)
        p = (!p._2).cast[Ptr[GSListStruct]]
      p
    }

  /**
   * Returns the specified element, wrapped by the implicitly provided GWrapper.
   *
   * @param idx
   * @param valueWrapper
   * @tparam T
   */
  @inline final def apply[T](idx: Int)(implicit valueWrapper: GWrapper[T]): T = valueWrapper.wrap(!nth(idx.toUInt)._1)

  /**
   * Appends a single element, unwrapped using the implicitly provided GWrapper.
   * @param x element to append
   * @param valueWrapper GWrapper used for unwrapping of `x`
   * @tparam T
   */
  @inline final def +=[T](x: T)(implicit valueWrapper: GWrapper[T]): GListLike = append(valueWrapper.unwrap(x))

  /**
   * Prepends a single element, unwrapped using the implicitly provided GWrapper.
   * @param x element to prepend
   * @param valueWrapper GWrapper used to unwrap `x`
   * @tparam T
   */
  def +=:[T](x: T)(implicit valueWrapper: GWrapper[T]): GListLike = prepend(valueWrapper.unwrap(x))
}

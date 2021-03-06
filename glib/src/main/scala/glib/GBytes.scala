// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scalanative._
import unsafe._
import cobj._

/**
 * @see [[https://developer.gnome.org/glib/stable/glib-Byte-Arrays.html]]
 */
@CObj
class GBytes[T] extends CObject with GRefCounter {

  @returnsThis
  @inline def ref(): this.type = extern

  @inline def unref(): Unit = extern

  /**
   * Returns the byte data in this object. This data should not be modified.
   *
   * @param size location where size of data is returned
   * @return pointer to the bytes data. May be null, if size=0 (not guaranteed)
   */
  @inline def getData(implicit size: ResultPtr[gsize]): gconstpointer = extern

  /**
   * Returns the pointer to the data in this object.
   *
   * @return
   */
  @inline final def data: Ptr[T] = getData(null).asInstanceOf[Ptr[T]]

  /**
   * Returns the size of the byte data in this object.
   */
  @name("g_bytes_get_size")
  @inline def size: gsize = extern

//  @inline def free(): Unit = extern
}

object GBytes {
  /**
   * Creates a new GBytes instance from the provided data.
   *
   * @param data the data to be used for the bytes; if size=0, data my be null.
   * @param size the size of data
   */
  @name("g_bytes_new")
  def apply[T](data: Ptr[T], size: gsize): GBytes[T] = extern

}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scalanative.native._
import cobj._

/**
 * @see [[https://developer.gnome.org/glib/stable/glib-Byte-Arrays.html#g-bytes-new]]
 *
 * @constructor
 * @param data the data to be used for the bytes; if size=0, data my be null.
 * @param size the size of data
 */
@CObj
final class GBytes(data: gconstpointer, size: gsize) extends GAllocated with GRefCounter {
  @inline override def free(): Unit = unref()

  @inline def ref(): Unit = extern

  @inline def unref(): Unit = extern

  /**
   * Returns the byte data in this object. This data should not be modified.
   *
   * @param size location where size of data is returned
   * @return pointer to the bytes data. May be null, if size=0 (not guaranteed)
   */
  @inline def getData(implicit size: Out[gsize]): gconstpointer = extern

  /**
   * Returns the size of the byte data in this object.
   */
  @inline def getSize(): gsize = extern
}

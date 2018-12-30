// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import de.surfice.smacrotools.debug

import scalanative.native._
import cobj._
import scala.scalanative.native.cobj.runtime.CObjObject

/**
 * @see [[https://developer.gnome.org/glib/stable/glib-Byte-Arrays.html#g-bytes-new]]
 *
 * @constructor
 */
@CObj
class GBytes extends CObjObject with GRefCounter {

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
  def apply(data: gconstpointer, size: gsize): GBytes = extern
}

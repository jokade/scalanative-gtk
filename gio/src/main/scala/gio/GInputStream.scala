// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gio

import de.surfice.smacrotools.debug
import glib.{GBytes, GError, gsize, gssize}

import scalanative.native._
import cobj._
import gobject.GObject

/**
 * Base class for implementing streaming input.
 *
 * @see [[https://developer.gnome.org/gio/stable/GInputStream.html]]
 */
@CObj
class GInputStream extends GObject {
  /**
   * Tries to read count bytes from the stream into the buffer starting at buffer. Will block during this read.
   * The returned buffer is not a nul-terminated string, it can contain nul bytes at any position, and this function doesn't nul-terminate the buffer.
   *
   * @param buffer a buffer to read data into (which should be at least count bytes long
   * @param count the number of bytes that will be read from the stream
   * @param cancellable optional GCancellable object; set null to ignore
   * @param error location to store the error occurring, or null to ignore
   * @return number of bytes read, or -1 on error, or 0 on end of file.
   *
   * @see [[https://developer.gnome.org/gio/stable/GInputStream.html#g-input-stream-read]]
   */
  def read(buffer: Ptr[Byte], count: gsize, cancellable: GCancellable)(implicit error: Out[GError]): gssize = extern

  /**
   * Like [[read]], this tries to read count bytes from the stream in a blocking fashion.
   * However, rather than reading into a user-supplied buffer, this will create a new GBytes containing the data
   * that was read. This may be easier to use from language bindings.
   *
   * @param count maximum number of bytes that will be read from the stream. Common values include 4096 and 8192.
   * @param cancellable optional GCancellable, null to ingore
   * @param error location to store an occurring error, null to ignore
   * @return a new GBytes, or null on error
   *
   * @see [[https://developer.gnome.org/gio/stable/GInputStream.html#g-input-stream-read-bytes]]
   */
  def readBytes(count: gsize, cancellable: GCancellable)(implicit error: Out[GError]): GBytes = extern
}

package gio

import glib.{GBytes, GDestroyNotify, gssize}

import scalanative._
import unsafe._
import cobj._

/**
 * Streaming input operations on memory chunks.
 *
 * @see [[https://developer.gnome.org/gio/stable/GMemoryInputStream.html]]
 */
@CObj
class GMemoryInputStream extends GInputStream {

  /**
   * Appends data to this stream.
   *
   * @param data input data
   * @param len length of data (may be -1 if data is a nul-terminated string)
   * @param destroy function that is called to free data, or null
   */
  def addData(data: Ptr[Byte], len: gssize, destroy: GDestroyNotify): Unit = extern

  /**
   * Appends bytes to this stream.
   *
   * @param bytes
   */
  def addBytes(bytes: GBytes[Byte]): Unit = extern
}

object GMemoryInputStream {
  /**
   * Creates an empty input stream.
   */
  @name("g_memory_input_stream_new")
  def apply(): GInputStream = extern

  /**
   * Creates a new input stream with data in memory of a given size.
   *
   * @param data input data
   * @param len length of the data (ma be -1 if sata is a nul-terminated string)
   * @param destroy function that is called to free data, or null
   */
  @name("g_memory_input_stream_new_from_data")
  def fromData(data: Ptr[Byte], len: gssize, destroy: GDestroyNotify): GMemoryInputStream = extern

  /**
   * Creates a new input stream with data from the given bytes.
   *
   * @param bytes
   */
  @name("g_memory_input_stream_new_from_bytes")
  def fromBytes(bytes: GBytes[Byte]): GMemoryInputStream = extern
}

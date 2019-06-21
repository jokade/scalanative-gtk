// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.json

import glib.{GList, GRefCounter, guint}
import gobject.GBoxed

import scalanative._
import unsafe._
import cobj._

/**
 * A JSON array representation.
 *
 * @see [[https://developer.gnome.org/json-glib/stable/json-glib-JSON-Array.html#JsonArray]]
 */
@CObj
class JsonArray extends CObject with GBoxed with GRefCounter {

  /**
   * Returns the number of elements in this array
   */
  @name("json_array_get_length")
  def length: guint = extern

  /**
   * Returns the element at the specified index.
   * @param index
   */
  def getElement(index: guint): JsonNode = extern

  /**
   * Gets the elements of this array as a list of [[JsonNode]]s
   */
  def getElements(): GList[JsonNode] = extern

  @returnsThis
  override def ref(): this.type = extern
  override def unref(): Unit = extern
  override def free(): Unit = unref()
}

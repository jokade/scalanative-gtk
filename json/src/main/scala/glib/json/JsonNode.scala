// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.json

import de.surfice.smacrotools.debug
import glib.{GAllocated, gboolean, gdouble, gint64}
import gobject.GBoxed

import scalanative._
import unsafe._
import cobj._

/**
 * Node in a JSON object model.
 *
 * @see [[https://developer.gnome.org/json-glib/stable/json-glib-JSON-Node.html#JsonNode]]
 */
@CObj
class JsonNode extends CObject with GBoxed with GAllocated {
  override def free(): Unit = extern

  @name("json_node_get_node_type")
  def nodeType: JsonNodeType = extern

  /**
   * Retrieves the JsonObject stored inside this node.
   */
  def getObject(): JsonObject = extern

  /**
   * Retrieves the JsonArray stored inside this node.
   */
  def getArray(): JsonArray = extern

  /**
   * Returns the string value stored in this node.
   */
  def getString(): CString = extern

  /**
   * Returns the boolean value stored inside this node.
   */
  def getBoolean(): gboolean = extern

  /**
   * Returns the int value stored inside this node.
   */
  def getInt(): gint64 = extern

  /**
   * Returns the double value stored inside this node.
   */
  def getDouble(): gdouble = extern
}


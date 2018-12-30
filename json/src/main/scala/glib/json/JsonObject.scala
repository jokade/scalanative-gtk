// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.json

import glib.{GRefCounter, gboolean, guint}
import gobject.GBoxed

import scalanative.native._
import cobj._
import scala.scalanative.native.cobj.runtime.CObjObject

/**
 * A JSON object representation.
 */
@CObj
class JsonObject extends CObjObject with GBoxed with GRefCounter {

  /**
   * Returns the number of members in this object.
   */
  @name("json_object_get_size")
  def size: guint = extern

  /**
   * Checks whether this object has a memeber with the specified name.
   * @param name member name to be checked for
   */
  def hasMember(name: CString): gboolean = extern

  /**
   * Retrieves the node containing the value of the specified member.
   * @param name the name of the object member to access
   * @return the member node, or null
   */
  @nullable
  def getMember(name: CString): JsonNode = extern

  /**
   * Retrieves the JsonNode for the specified member.
   *
   * @param name member name
   */
  def member(name: CString): Option[JsonNode] = getMember(name) match {
    case null => None
    case m => Some(m)
  }

  /**
   * Retrieves the string value of the specified member.
   *
   * @param name member name
   */
  def string(name: CString): Option[String] = member(name).map(m => fromCString(m.getString()))

  /**
   * Retrieves the int value of the specified member.
   *
   * @param name member name
   */
  def int(name: CString): Option[Int] = member(name).map( _.getInt().toInt )

  /**
   * Retrieves the boolean value of the specified member.
   *
   * @param name member name
   */
  def boolean(name: CString): Option[Boolean] = member(name).map(_.getBoolean())

  /**
   * Retrieves the double value of the specified member.
   *
   * @param name member name
   */
  def double(name: CString): Option[Double] = member(name).map(_.getDouble())

  /**
   * Retrieves the specified member as an array.
   *
   * @param name member name
   */
  def array(name: CString): Option[JsonArray] = member(name).map(_.getArray())

  /**
   * Retrieves the specified member as an object.
   *
   * @param name member name
   */
  def obj(name: CString): Option[JsonObject] = member(name).map(_.getObject())

  override def ref(): Unit = extern
  override def unref(): Unit = extern
}

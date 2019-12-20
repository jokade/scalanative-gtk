// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.json

import glib.{GRefCounter, gboolean, guint}
import gobject.GBoxed

import scalanative._
import unsafe._
import cobj._
import scala.collection.mutable
import scala.scalanative.runtime.RawPtr

/**
 * A JSON object representation.
 */
@CObj
class JsonObject extends CObject with GBoxed with GRefCounter {

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

  def foreachMember(f: CFuncPtr4[Ptr[Byte],Ptr[Byte],Ptr[Byte],RawPtr,Unit], data: RawPtr): Unit = extern
  def foreachMember(f: Function2[CString,JsonNode,Unit]): Unit = {
    val fPtr = interop.objectToRawPtr(f)
    foreachMember(JsonObject.foreachCB,fPtr)
  }

  def mapMembers[T](f: (String,JsonNode) => T): Iterable[T] = {
    val buf = mutable.UnrolledBuffer.empty[Any]
    foreachMember((name,node) => {
      buf += f(fromCString(name),node)
    })
    buf.asInstanceOf[mutable.Iterable[T]]
  }

  @returnsThis
  override def ref(): this.type = extern
  override def unref(): Unit = extern
  override def free(): Unit = unref()
}
object JsonObject {
  private val foreachCB = new CFuncPtr4[Ptr[Byte],Ptr[Byte],Ptr[Byte],RawPtr,Unit]{
    override def apply(obj: Ptr[Byte], name: CString, nodePtr: Ptr[Byte], fPtr: RawPtr): Unit = {
      val f = interop.rawPtrToObject(fPtr).asInstanceOf[Function2[CString,JsonNode,Unit]]
      val node = new JsonNode(nodePtr)
      f(name,node)
    }
  }
}

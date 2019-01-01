// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gobject

import de.surfice.smacrotools.debug
import glib._

import scalanative.native._
import cobj._
import scala.scalanative.native.cobj.runtime.CObjObject

@CObj
class GObject extends CObjObject with GAllocated with GRefCounter with GSignalReceiver {
  @inline def ref(): Unit = extern
  @inline def unref(): Unit = extern
  @inline def free(): Unit = unref()

  /**
   * Returns the value of the specified gint property.
   *
   * @param propName Name of the property
   */
  def getInt(propName: CString): gint = {
    val v = stackalloc[gint]
    !v = 0
    GObject.ext.g_object_get(__ptr,propName,v.cast[Ptr[Ptr[Byte]]], null)
    !v
  }

  /**
   * Sets the value for the specified gint property.
   *
   * @param propName Name of the property
   * @param value property value
   */
  def setInt(propName: CString, value: gint): Unit = GObject.ext.g_object_set(__ptr,propName,value.cast[Ptr[Byte]],null)

  /**
   * Returns the value of the specified guint property.
   *
   * @param propName Name of the property
   */
  def getUInt(propName: CString): guint = {
    val v = stackalloc[guint]
    !v = 0.toUInt
    GObject.ext.g_object_get(__ptr,propName,v.cast[Ptr[Ptr[Byte]]], null)
    !v
  }

  /**
   * Sets the value for the specified guint property.
   *
   * @param propName Name of the property
   * @param value property value
   */
  def setUInt(propName: CString, value: guint): Unit = GObject.ext.g_object_set(__ptr,propName,value.cast[Ptr[Byte]],null)

  /**
   * Returns the value of the specified string property.
   *
   * @param propName Name of the propertx
   */
  def getString(propName: CString): CString = {
    val v = stackalloc[CString]
    !v = null
    GObject.ext.g_object_get(__ptr,propName,v,null)
    !v
  }

  /**
   * Sets the value for the specified string property.
   *
   * @param propName Name of the property
   * @param value property value
   */
  def setString(propName: CString, value: CString): Unit = GObject.ext.g_object_set(__ptr,propName,value.cast[Ptr[Byte]],null)

  /**
   * Returns the value of the specified object property (i.e. a pointer to the object, or null).
   *
   * @param propName Name of the property
   */
  def getObject(propName: CString): Ptr[Byte] = {
    val v = stackalloc[Ptr[Byte]]
    !v = null
    GObject.ext.g_object_get(__ptr,propName,v,null)
    !v
  }
}

object GObject {
 @extern
 protected[gobject] object ext {
   def g_object_get(self: Ptr[Byte], name: CString, ptr: Ptr[Ptr[Byte]], last: Ptr[Byte]): Unit = extern
   def g_object_set(self: Ptr[Byte], name: CString, ptr: Ptr[Byte], last: Ptr[Byte]): Unit = extern
 }
}


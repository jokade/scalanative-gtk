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
  def getIntProp(propName: CString): gint = {
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
  def setIntProp(propName: CString, value: gint): Unit = GObject.ext.g_object_set(__ptr,propName,value.cast[Ptr[Byte]],null)

  /**
   * Returns the value of the specified guint property.
   *
   * @param propName Name of the property
   */
  def getUIntProp(propName: CString): guint = {
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
  def setUIntProp(propName: CString, value: guint): Unit = GObject.ext.g_object_set(__ptr,propName,value.cast[Ptr[Byte]],null)

  /**
   * Returns the value of the specified float property.
   *
   * @param propName NAme of the property
   */
  def getFloatProp(propName: CString): gfloat = {
    val v = stackalloc[gfloat]
    !v = 0.0f
    GObject.ext.g_object_getFloat(__ptr,propName,v, null)
    !v
  }

  /**
   * Sets the value for the specified gfloat property.
   *
   * @param propName Name of the property.
   * @param value property value
   */
  def setFloatProp(propName: CString, value: gfloat): Unit = GObject.ext.g_object_setFloat(__ptr,propName,value,null)

  /**
   * Returns the value of the specified boolean property.
   *
   * @param propName Name of the property
   */
  def getBooleanProp(propName: CString): gboolean = {
    val v = stackalloc[gboolean]
    !v = false
    GObject.ext.g_object_get(__ptr,propName,v.cast[Ptr[Ptr[Byte]]], null)
    !v
  }

  /**
   * Sets the value for the specified boolean property.
   *
   * @param propName Name of the property.
   * @param value property value
   */
  def setBooleanProp(propName: CString, value: gboolean): Unit = GObject.ext.g_object_set(__ptr,propName,value.cast[Ptr[Byte]],null)

  /**
   * Returns the value of the specified string property.
   *
   * @param propName Name of the propertx
   */
  def getCStringProp(propName: CString): CString = {
    val v = stackalloc[CString]
    !v = null
    GObject.ext.g_object_get(__ptr,propName,v,null)
    !v
  }

  def getStringProp(propName: CString): String = fromCString(getCStringProp(propName))

  /**
   * Sets the value for the specified string property.
   *
   * @param propName Name of the property
   * @param value property value
   */
  def setStringProp(propName: CString, value: CString): Unit = GObject.ext.g_object_set(__ptr,propName,value.cast[Ptr[Byte]],null)

  def setStringProp(propName: CString, value: String): Unit = PoolZone{ implicit z => setStringProp(propName,toCString(value))}

  /**
   * Returns the value of the specified object property (i.e. a pointer to the object, or null).
   *
   * @param propName Name of the property
   */
  def getObjectProp(propName: CString): Ptr[Byte] = {
    val v = stackalloc[Ptr[Byte]]
    !v = null
    GObject.ext.g_object_get(__ptr,propName,v,null)
    !v
  }
}

object GObject {

 @extern
 object ext {
   def g_object_get(self: Ptr[Byte], name: CString, ptr: Ptr[Ptr[Byte]], last: Ptr[Byte]): Unit = extern
   @name("g_object_get")
   def g_object_getFloat(self: Ptr[Byte], name: CString, ptr: Ptr[Float], last: Ptr[Byte]): Unit = extern
   def g_object_set(self: Ptr[Byte], name: CString, ptr: Ptr[Byte], last: Ptr[Byte]): Unit = extern
   @name("g_object_set")
   def g_object_setFloat(self: Ptr[Byte], name: CString, value: CFloat, last: Ptr[Byte]): Unit = extern

//   def g_object_new(objectType: GType, last: CString): Ptr[Byte] = extern
 }
}


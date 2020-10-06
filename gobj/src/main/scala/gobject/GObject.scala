// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gobject

import de.surfice.smacrotools.debug
import glib._
import glib.utils.GZone

import scalanative._
import unsafe._
import unsigned._
import interop._
import cobj._

@CObj
class GObject extends GRefCounter with GSignalReceiver {

  /**
   * Returns the value of the specified gint property.
   *
   * @param propName Name of the property
   */
  def getIntProp(propName: CString): gint = {
    val v = stackalloc[gint]
    v := 0
    GObject.ext.g_object_getInt(__ptr,propName,v, null)
    !v
  }

  /**
   * Sets the value for the specified gint property.
   *
   * @param propName Name of the property
   * @param value property value
   */
  def setIntProp(propName: CString, value: gint): Unit = GObject.ext.g_object_setInt(__ptr,propName,value,null)


  /**
   * Returns the value of the specified guint property.
   *
   * @param propName Name of the property
   */
  def getUIntProp(propName: CString): guint = {
    val v = stackalloc[guint]
    v := 0.toUInt
    GObject.ext.g_object_getUInt(__ptr,propName,v, null)
    !v
  }

  def getInt64Prop(propName: CString): gint64 = {
    val v = stackalloc[gint64]
    v := 0
    GObject.ext.g_object_getInt64(__ptr,propName,v, null)
    !v
  }
  def setInt64Prop(propName: CString, value: gint64): Unit = GObject.ext.g_object_setInt64(__ptr,propName,value,null)

  def getUInt64Prop(propName: CString): guint64 = {
    val v = stackalloc[guint64]
    v := 0.toULong
    GObject.ext.g_object_getUInt64(__ptr,propName,v, null)
    !v
  }
  def setUInt64Prop(propName: CString, value: guint64): Unit = GObject.ext.g_object_setUInt64(__ptr,propName,value,null)

  /**
   * Sets the value for the specified guint property.
   *
   * @param propName Name of the property
   * @param value property value
   */
  def setUIntProp(propName: CString, value: guint): Unit = GObject.ext.g_object_setUInt(__ptr,propName,value,null)

  /**
   * Returns the value of the specified float property.
   *
   * @param propName NAme of the property
   */
  def getFloatProp(propName: CString): gfloat = {
    val v = stackalloc[gfloat]
    v := 0.0f
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
   * Returns the value of the specified double property.
   *
   * @param propName NAme of the property
   */
  def getDoubleProp(propName: CString): gdouble = {
    val v = stackalloc[gdouble]
    v := 0.0
    GObject.ext.g_object_getDouble(__ptr,propName,v, null)
    !v
  }

  /**
   * Sets the value for the specified gdouble property.
   *
   * @param propName Name of the property.
   * @param value property value
   */
  def setDoubleProp(propName: CString, value: gdouble): Unit = GObject.ext.g_object_setDouble(__ptr,propName,value,null)


  /**
   * Returns the value of the specified boolean property.
   *
   * @param propName Name of the property
   */
  def getBooleanProp(propName: CString): gboolean = {
    val v = stackalloc[gboolean]
    v := false
    GObject.ext.g_object_getBoolean(__ptr,propName,v.asInstanceOf[Ptr[CBool]], null)
    !v
  }

  /**
   * Sets the value for the specified boolean property.
   *
   * @param propName Name of the property.
   * @param value property value
   */
  def setBooleanProp(propName: CString, value: gboolean): Unit = GObject.ext.g_object_setBoolean(__ptr,propName,value,null)

  /**
   * Returns the value of the specified string property.
   *
   * @param propName Name of the propertx
   */
  def getCStringProp(propName: CString): CString = {
    val v = stackalloc[CString]
    v := null
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
  def setStringProp(propName: CString, value: CString): Unit = GObject.ext.g_object_set(__ptr,propName,value.asInstanceOf[Ptr[Byte]],null)

  def setStringProp(propName: CString, value: String): Unit = GZone{ implicit z => setStringProp(propName,toCString(value))}

  /**
   * Returns the value of the specified object property (i.e. a pointer to the object, or null).
   *
   * @param propName Name of the property
   */
  def getObjectProp(propName: CString): Ptr[Byte] = {
    val v = stackalloc[Ptr[Byte]]
    v := null
    GObject.ext.g_object_get(__ptr,propName,v,null)
    !v
  }

  /**
   * Sets the value of the specified object property.
   *
   * @param propName name of the property
   * @param p pointer to the new object
   */
  def setObjectProp(propName: CString, p: Ptr[Byte]): Unit = {
    GObject.ext.g_object_set(__ptr,propName,p,null)
  }

  /**
   * Increases the reference count on this object.
   */
  @returnsThis
  override def ref(): this.type = extern

  /**
   * Decreases the reference count on this object. This may result in the object being freed.
   */
  override def unref(): Unit = extern
}

object GObject {

 @extern
 @external
 @debug
 object ext {
   def g_object_get(self: Ptr[Byte], name: CString, ptr: Ptr[Ptr[Byte]], last: Ptr[Byte]): Unit = extern
   @name("g_object_get")
   def g_object_getFloat(self: Ptr[Byte], name: CString, ptr: Ptr[Float], last: Ptr[Byte]): Unit = extern
   @name("g_object_get")
   def g_object_getDouble(self: Ptr[Byte], name: CString, ptr: Ptr[Double], last: Ptr[Byte]): Unit = extern
   @name("g_object_get")
   def g_object_getBoolean(self: Ptr[Byte], name: CString, ptr: Ptr[CBool], last: Ptr[Byte]): Unit = extern
   @name("g_object_get")
   def g_object_getInt(self: Ptr[Byte], name: CString, ptr: Ptr[gint], last: Ptr[Byte]): Unit = extern
   @name("g_object_get")
   def g_object_getUInt(self: Ptr[Byte], name: CString, ptr: Ptr[guint], last: Ptr[Byte]): Unit = extern
   @name("g_object_get")
   def g_object_getInt64(self: Ptr[Byte], name: CString, ptr: Ptr[gint64], last: Ptr[Byte]): Unit = extern
   @name("g_object_get")
   def g_object_getUInt64(self: Ptr[Byte], name: CString, ptr: Ptr[guint64], last: Ptr[Byte]): Unit = extern


   def g_object_set(self: Ptr[Byte], name: CString, ptr: Ptr[Byte], last: Ptr[Byte]): Unit = extern
   @name("g_object_set")
   def g_object_setFloat(self: Ptr[Byte], name: CString, value: CFloat, last: Ptr[Byte]): Unit = extern
   @name("g_object_set")
   def g_object_setDouble(self: Ptr[Byte], name: CString, value: CDouble, last: Ptr[Byte]): Unit = extern
   @name("g_object_set")
   def g_object_setBoolean(self: Ptr[Byte], name: CString, value: CBool, last: Ptr[Byte]): Unit = extern
   @name("g_object_set")
   def g_object_setInt(self: Ptr[Byte], name: CString, value: gint, last: Ptr[Byte]): Unit = extern
   @name("g_object_set")
   def g_object_setUInt(self: Ptr[Byte], name: CString, value: guint, last: Ptr[Byte]): Unit = extern
   @name("g_object_set")
   def g_object_setInt64(self: Ptr[Byte], name: CString, value: gint64, last: Ptr[Byte]): Unit = extern
   @name("g_object_set")
   def g_object_setUInt64(self: Ptr[Byte], name: CString, value: guint64, last: Ptr[Byte]): Unit = extern

//   def g_object_new(objectType: GType, last: CString): Ptr[Byte] = extern
 }
}


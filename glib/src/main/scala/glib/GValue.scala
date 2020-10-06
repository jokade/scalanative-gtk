package glib

import scalanative._
import unsafe._
import unsigned._
import cobj._

@CObj
class GValue extends CObject {
  @returnsThis
  def init(tpe: GType): GValue = extern

  def getChar(): gchar = extern
  def setChar(c: gchar): Unit = extern

  @name("g_value_get_uchar")
  def getUChar(): guchar = extern
  @name("g_value_set_uchar")
  def setUChar(c: guchar): Unit = extern

  def getBoolean(): gboolean = extern
  def setBoolean(b: gboolean): Unit = extern

  def setInt(i: gint): Unit = extern
  def getInt(): gint = extern

  @name("g_value_get_uint")
  def getUInt(): guint = extern
  @name("g_value_set_uint")
  def setUInt(i: guint): Unit = extern

  def setLong(i: glong): Unit = extern
  def getLong(): glong = extern

  @name("g_value_get_ulong")
  def getULong(): gulong = extern
  @name("g_value_set_ulong")
  def setULong(i: gulong): Unit = extern

  def setInt64(i: gint64): Unit = extern
  def getInt64(): gint64 = extern

  @name("g_value_get_uint64")
  def getUInt64(): guint64 = extern
  @name("g_value_set_uint64")
  def setUInt64(i: guint64): Unit = extern

  @name("g_value_get_string")
  def getCString(): CString = extern
  @name("g_value_set_string")
  def setCString(s: CString): Unit = extern

  def getString(): String = fromCString(getCString())
  def setString(s: String)(implicit zone: Zone): Unit = setCString(toCString(s))

  @name("g_value_get_pointer")
  def getPointer(): Ptr[Byte] = extern
  @name("g_value_set_pointer")
  def setPointer(s: Ptr[Byte]): Unit = extern
}
object GValue {
  @name("snhelper_gvalue_getsize")
  private def getStructSize(): CSize = extern

  private lazy val structSize = getStructSize()

  def apply()(implicit zone: Zone): GValue = {
    val v = zone.alloc(structSize)
    !v.asInstanceOf[Ptr[CUnsignedLong]] = 0.toULong
    new GValue(v)
  }

  def char(c: gchar = 0.toByte)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.Char)
    g.setChar(c)
    g
  }

  def uchar(c: guchar)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.UChar)
    g.setUChar(c)
    g
  }

  def boolean(b: gboolean)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.Boolean)
    g.setBoolean(b)
    g
  }

  def int(v: gint = 0)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.Int)
    g.setInt(v)
    g
  }

  def uint(v: guint = 0.toUInt)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.UInt)
    g.setUInt(v)
    g
  }

  def long(v: glong = 0)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.Long)
    g.setLong(v)
    g
  }

  def ulong(v: gulong = 0.toULong)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.ULong)
    g.setULong(v)
    g
  }

  def int64(v: gint64 = 0)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.Int64)
    g.setInt64(v)
    g
  }

  def uint64(v: guint64 = 0.toUInt)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.UInt64)
    g.setUInt64(v)
    g
  }

  def cstring(v: CString = null)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.String)
    g.setCString(v)
    g
  }

  def string(s: String)(implicit zone: Zone): GValue = cstring(toCString(s))

  def pointer(v: Ptr[Byte] = null)(implicit zone: Zone): GValue = {
    val g = apply().init(GType.Pointer)
    g.setPointer(v)
    g
  }
}

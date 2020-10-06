package glib

import scalanative._
import unsafe._
import unsigned._
import cobj._

@CObj
object GType {

  @name("snhelper_gtype_char")
  private def getTypeChar(): CUnsignedLong = extern

  @name("snhelper_gtype_uchar")
  private def getTypeUChar(): CUnsignedLong = extern

  @name("snhelper_gtype_boolean")
  private def getTypeBoolean(): CUnsignedLong = extern

  @name("snhelper_gtype_int")
  private def getTypeInt(): CUnsignedLong = extern

  @name("snhelper_gtype_uint")
  private def getTypeUInt(): CUnsignedLong = extern

  @name("snhelper_gtype_long")
  private def getTypeLong(): CUnsignedLong = extern

  @name("snhelper_gtype_ulong")
  private def getTypeULong(): CUnsignedLong = extern

  @name("snhelper_gtype_int64")
  private def getTypeInt64(): CUnsignedLong = extern

  @name("snhelper_gtype_uint64")
  private def getTypeUInt64(): CUnsignedLong = extern

  @name("snhelper_gtype_float")
  private def getTypeFloat(): CUnsignedLong = extern

  @name("snhelper_gtype_double")
  private def getTypeDouble(): CUnsignedLong = extern

  @name("snhelper_gtype_string")
  private def getTypeString(): CUnsignedLong = extern

  @name("snhelper_gtype_pointer")
  private def getTypePointer(): CUnsignedLong = extern

  lazy val Char: GType = getTypeChar()
  lazy val UChar: GType = getTypeUChar()
  lazy val Boolean: GType = getTypeBoolean()
  lazy val Int: GType = getTypeInt()
  lazy val UInt: GType = getTypeUInt()
  lazy val Long: GType = getTypeLong()
  lazy val ULong: GType = getTypeULong()
  lazy val Int64: GType = getTypeInt64()
  lazy val UInt64: GType = getTypeUInt64()
  lazy val String: GType = getTypeString()
  lazy val Pointer: GType = getTypePointer()
}

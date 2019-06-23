// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import scala.scalanative._
import unsafe._

package object glib {
  /* gtypes.h */
  type gchar    = CChar
  type gshort   = CShort
  type glong    = CLong
  type gint     = CInt
  type gboolean = Boolean
  type guchar   = CUnsignedChar
  type gushort  = CUnsignedShort
  type gulong   = CUnsignedLong
  type guint    = CUnsignedInt
  type gfloat   = CFloat
  type gdouble  = CDouble
  type gpointer = Ptr[Byte]
  type gconstpointer = gpointer

  // TODO: ensure that these types have the specified width on all supported targets
  type gint8    = CChar
  type guint8   = CUnsignedChar
  type gint16   = CShort
  type guint16  = CUnsignedShort
  type gint32   = CInt
  type guint32  = CUnsignedInt
  type gint64   = CLong
  type guint64  = CUnsignedLong

  type gsize    = CSize
  type gssize   = CSSize
  type goffset  = gint64
  type gintptr  = CLong
  type guintptr = CUnsignedLong

  type GQuark   = guint32

  type GFunc = CFuncPtr2[gpointer,gpointer,_]

  type GSListStruct = CStruct2[gpointer,Ptr[Byte]]
  type GListStruct  = CStruct3[gpointer,Ptr[Byte],Ptr[Byte]]

  type GDestroyNotify = CFuncPtr1[gpointer,Unit]

  /**
   * A type in the GVariant type system.
   *
   * Two types may not be compared by value; use [[GVariantType.equal]] or [[GVariantType.isSubtypeOf]].
   * May be copied using [[GVariantType.copy]] and freed using [[GVariantType.free]]
   */
  type GVariantType = CString

  type GHashTableIter = CStruct6[gpointer,gpointer,gpointer,CInt,gboolean,gpointer]
  type GHashFunc = CFuncPtr1[gconstpointer,guint]
  type GEqualFunc = CFuncPtr2[gconstpointer,gconstpointer,gboolean]

  type GSourceFunc = CFuncPtr1[gpointer,gboolean]

}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import glib.gsize

import scala.scalanative.native._

package object gobject {

  /* gtype.h */
  type GType = gsize
//  val G_TYPE_FUNDAMENTAL_SHIFT = 2
  val G_TYPE_INVALID      = 0 << 2
  val G_TYPE_NONE         = 1 << 2
  val G_TYPE_INTERFACE    = 2 << 2
  val G_TYPE_CHAR         = 3 << 2
  val G_TYPE_UCHAR        = 4 << 2
  val G_TYPE_BOOLEAN      = 5 << 2
  val G_TYPE_INT          = 6 << 2
  val G_TYPE_UINT         = 7 << 2
  val G_TYPE_LONG         = 8 << 2
  val G_TYPE_ULONG        = 9 << 2
  val G_TYPE_INT64        = 10 << 2
  val G_TYPE_UINT64       = 11 << 2
  val G_TYPE_ENUM         = 12 << 2
  val G_TYPE_FLAGS        = 13 << 2
  val G_TYPE_FLOAT        = 14 << 2
  val G_TYPE_DOUBLE       = 15 << 2
  val G_TYPE_STRING       = 16 << 2
  val G_TYPE_POINTER      = 17 << 2
  val G_TYPE_BOXED        = 18 << 2
  val G_TYPE_PARAM        = 19 << 2
  val G_TYPE_OBJECT       = 20 << 2
  val G_TYPE_VARIANT      = 21 << 2
  val G_TYPE_FUNDAMENTAL_MAX = 255 << 2

  /* gclosure.h */
  type GCallback           = CFunctionPtr
//  type GCallback0[R]       = CFunctionPtr0[R]
//  type GCallback1[T1,R]    = CFunctionPtr1[T1,R]
//  type GCallback2[T1,T2,R] = CFunctionPtr2[T1,T2,R]

  type GClosureMarshal     = CFunctionPtr0[Unit]
  type GVaClosureMarshal   = CFunctionPtr0[Unit]
  type GClosureNotify      = CFunctionPtr0[Unit]

  /* gsignal.h */
  type GConnectFlags = UInt
  object GConnectFlags {
    val AFTER: GConnectFlags = (1 << 0).toUInt
    val SWAPPED: GConnectFlags = (1 << 1).toUInt
  }

}

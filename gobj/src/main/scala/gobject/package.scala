// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import glib.gsize

import scala.scalanative.native._

package object gobject {

  /* gtype.h */
  type GType = gsize
  object GType {
    //  val G_TYPE_FUNDAMENTAL_SHIFT = 2
    val INVALID         :GType = 0 << 2
    val NONE            :GType = 1 << 2
    val INTERFACE       :GType = 2 << 2
    val CHAR            :GType = 3 << 2
    val UCHAR           :GType = 4 << 2
    val BOOLEAN         :GType = 5 << 2
    val INT             :GType = 6 << 2
    val UINT            :GType = 7 << 2
    val LONG            :GType = 8 << 2
    val ULONG           :GType = 9 << 2
    val INT64           :GType = 10 << 2
    val UINT64          :GType = 11 << 2
    val ENUM            :GType = 12 << 2
    val FLAGS           :GType = 13 << 2
    val FLOAT           :GType = 14 << 2
    val DOUBLE          :GType = 15 << 2
    val STRING          :GType = 16 << 2
    val POINTER         :GType = 17 << 2
    val BOXED           :GType = 18 << 2
    val PARAM           :GType = 19 << 2
    val OBJECT          :GType = 20 << 2
    val VARIANT         :GType = 21 << 2
    val FUNDAMENTAL_MAX :GType = 255 << 2
  }

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

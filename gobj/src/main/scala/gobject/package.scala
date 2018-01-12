// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import scala.scalanative.native._

package object gobject {
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
  val G_CONNECT_AFTER: GConnectFlags   = (1 << 0).toUInt
  val G_CONNECT_SWAPPED: GConnectFlags = (1 << 1).toUInt
}

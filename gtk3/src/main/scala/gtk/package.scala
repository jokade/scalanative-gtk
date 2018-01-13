// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import scalanative.native._

package object gtk {
  /* gtkwindow.h */
  type GtkWindowType = UInt
  object GtkWindowType {
    val TOPLEVEL: GtkWindowType = 0.toUInt
    val POPUP: GtkWindowType = 1.toUInt
  }
}

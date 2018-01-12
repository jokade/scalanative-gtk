// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import scalanative.native._

package object gtk {
  /* gtkwindow.h */
  type GtkWindowType = UInt
  val GTK_WINDOW_TOPLEVEL: GtkWindowType = 0.toUInt
  val GTK_WINDOW_POPUP: GtkWindowType = 1.toUInt
}

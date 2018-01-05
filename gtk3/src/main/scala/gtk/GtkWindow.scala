// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import de.surfice.smacrotools.debug
import gobj.GObj

import scala.scalanative.native._

class GtkWindow extends GtkWidget {

}

object GtkWindow {
//  def apply(topLevel: Boolean): Window = ext.gtk_window_new(if())

  def apply(windowType: GtkWindowType): GtkWindow = ext.gtk_window_new(windowType)

  @extern
  object ext {
    def gtk_window_new(windowType: GtkWindowType): GtkWindow = extern
  }

  type GtkWindowType = UInt
  val GTK_WINDOW_TOPLEVEL: GtkWindowType = 0.toUInt
  val GTK_WINDOW_POPUP: GtkWindowType = 1.toUInt
}

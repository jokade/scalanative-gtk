// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import de.surfice.smacrotools.debug
import gobj.GObj

import scalanative.native._

@GObj
@debug
class GtkWidget {
  def showAll(): Unit = extern
}

object GtkWidget {
//  @extern
//  object __ext {
//    def gtk_widget_show_all(self: GtkWidget) : Unit = extern
//  }
}

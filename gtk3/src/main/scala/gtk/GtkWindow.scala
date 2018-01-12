// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import de.surfice.smacrotools.debug

import scala.scalanative.native._

@CObj
class GtkWindow extends GtkWidget {

}

object GtkWindow {

  /**
   * Creates a new window.
   *
   * @param topLevel if true, a new window of type [[GTK_WINDOW_TOPLEVEL]] is created, otherwise a [[GTK_WINDOW_POPUP]]
   */
  def apply(topLevel: Boolean = true): GtkWindow =
    if(topLevel) apply(GTK_WINDOW_TOPLEVEL)
    else apply(GTK_WINDOW_POPUP)

  /**
   * Creates a new window
   * @param windowType window type
   */
  def apply(windowType: GtkWindowType): GtkWindow = extern

}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import glib.gboolean

import scala.scalanative.native._
import cobj._

/**
 * Toplevel which can conatin other widgets.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkWindow.html]]
 * @constructor
 * @param windowType type of window
 */
@CObj
//@debug
class GtkWindow(windowType: GtkWindowType) extends GtkBin {

  /**
   * Creates a new toplevel window.
   */
  def this() = this(GtkWindowType.TOPLEVEL)

  /**
   * Sets the title of the window.
   *
   * @param title title of the window
   */
  @inline def setTitle(title: CString): Unit = extern

  /**
   * Sets whether the user can resize this window.
   * Windows are user resizable by default.
   *
   * @param resizable true, if the user can resize the window
   */
  @inline def setResizable(resizable: gboolean): Unit = extern

  /**
   * Returns true if this window is resizable by the user.
   */
  @inline def getResizable(): gboolean = extern
}

object GtkWindow {


}

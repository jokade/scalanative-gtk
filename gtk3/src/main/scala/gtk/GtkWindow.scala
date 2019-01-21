package gtk

import glib.{gboolean, gint}
import gtk.gdk.GdkPixbuf

import scala.scalanative.native._
import cobj._

/**
 * Toplevel which can conatin other widgets.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkWindow.html]]
 */
@CObj
//@debug
class GtkWindow extends GtkBin {

  /**
   * Sets the title of the window.
   *
   * @param title title of the window
   */
  def setTitle(title: CString): Unit = extern

  def title_=(title: String): Unit = setStringProp(c"title",title)
  def title: String = getStringProp(c"title")

  def modal: gboolean = getBooleanProp(c"modal")
  def modal_=(setting: gboolean): Unit = setBooleanProp(c"modal",setting)

  def resizable: gboolean = getBooleanProp(c"resizable")
  def resizable_=(setting: gboolean): Unit = setBooleanProp(c"resizable",setting)

  def getIcon(): GdkPixbuf = extern
  def setIcon(icon: GdkPixbuf): Unit = extern

  /**
   * Resizes the window as if the user had done so.
   *
   * @param width width in pixels
   * @param height height in pixels
   */
  def resize(width: gint, height: gint): Unit = extern

  /**
   * Presents this window to the user.
   *
   * This may mean raising the window in the stacking order, deiconifying it,
   * moving it to the current desktop, and/or giving it the keyboard focus.
   */
  def present(): Unit = extern

  /**
   * Sets whether the user can resize this window.
   * Windows are user resizable by default.
   *
   * @param resizable true, if the user can resize the window
   */
//  @inline def setResizable(resizable: gboolean): Unit = extern

  /**
   * Returns true if this window is resizable by the user.
   */
//  @inline def getResizable(): gboolean = extern
}

object GtkWindow {

  def apply(): GtkWindow = apply(GtkWindowType.TOPLEVEL)

  @name("gtk_window_new")
  def apply(windowType: GtkWindowType): GtkWindow = extern
}

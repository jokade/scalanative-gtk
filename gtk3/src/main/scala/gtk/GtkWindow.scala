package gtk

import glib.{gboolean, gint}
import gtk.gdk.GdkPixbuf

import scala.scalanative._
import unsafe._
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
   * Sets the default size for this window.
   *
   * @param width width in pixels, or -1 to unset the default width
   * @param height height in pixels, or -1 to unset the default height
   */
  def setDefaultSize(width: gint, height: gint): Unit = extern

//  /**
//   * Sets the title of the window.
//   *
//   * @param title title of the window
//   */
//  def setTitle(title: CString): Unit = extern

  def title_=(title: String): Unit = setStringProp(c"title",title)
  def title: String = getStringProp(c"title")

  def isMaximized: gboolean = getBooleanProp(c"is-maximized")

  def modal: gboolean = getBooleanProp(c"modal")
  def modal_=(f: gboolean): Unit = setBooleanProp(c"modal",f)

  def resizable: gboolean = getBooleanProp(c"resizable")
  def resizable_=(setting: gboolean): Unit = setBooleanProp(c"resizable",setting)

  def iconName: String = getStringProp(c"icon-name")
  def iconName_=(name: String): Unit = setStringProp(c"icon-name",name)

  def getIcon(): GdkPixbuf = extern
  def setIcon(icon: GdkPixbuf): Unit = extern

  def setTitlebar(titlebar: GtkWidget): Unit = extern
  def getTitlebar(): GtkWidget = extern

  def setTransientFor(parent: GtkWindow): Unit = extern
  def getTransientFor(): GtkWindow = extern
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

}

object GtkWindow {

  def apply(): GtkWindow = apply(GtkWindowType.TOPLEVEL)

  @name("gtk_window_new")
  def apply(windowType: GtkWindowType): GtkWindow = extern
}

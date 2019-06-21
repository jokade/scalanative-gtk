package gtk

import scalanative._
import unsafe._
import cobj._

/**
 * GtkWindow subclass with GtkApplication support.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkApplicationWindow.html]]
 */
@CObj
class GtkApplicationWindow extends GtkWindow {

}

object GtkApplicationWindow {
  @name("gtk_application_window_new")
  def apply(application: GtkApplication): GtkApplicationWindow = extern

}

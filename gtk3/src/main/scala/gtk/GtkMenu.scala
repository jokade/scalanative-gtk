package gtk

import scalanative._
import unsafe._
import cobj._

/**
 * A menu widget.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkMenu.html]]
 */
@CObj
class GtkMenu extends GtkMenuShell {

}

object GtkMenu {
  /**
   * Creates a new GtkMenu.
   */
  @name("gtk_menu_new")
  def apply(): GtkMenu = extern
}
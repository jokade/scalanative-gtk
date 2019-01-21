package gtk

import gio.GMenuModel

import scalanative.native._
import cobj._

/**
 * A subclass of [[GtkMenuShell]] which holds [[GtkMenuItem]] widgets.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkMenuBar.html]]
 */
@CObj
class GtkMenuBar extends GtkMenuShell {

  /**
   * Pack direction of this menu bar.
   */
  def packDirection: GtkPackDirection = getIntProp(c"pack-direction")
  def packDirection_(dir: GtkPackDirection): Unit = setIntProp(c"pack-direction",dir)

  /**
   * Pack direction of the child menu items.
   */
  def childPackDirection: GtkPackDirection = getIntProp(c"child-pack-direction")
  def childPackDirection_(dir: GtkPackDirection): Unit = setIntProp(c"child-pack-direction",dir)
}

object GtkMenuBar {
  /**
   * Creates a new GtkMenuBar.
   */
  @name("gtk_menu_bar_new")
  def apply(): GtkMenuBar = extern

  /**
   * Creates a new GtkMenuBar and populates it with the menu items from the provided model.
   *
   * @param model
   */
  @name("gtk_menu_bar_new_from_model")
  def apply(model: GMenuModel): GtkMenuBar = extern
}

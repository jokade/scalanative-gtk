package gtk

import scalanative.native._
import cobj._

/**
 * Widget used for items in menus.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkMenuItem.html]]
 */
@CObj
class GtkMenuItem extends GtkBin {

}

object GtkMenuItem {
  /**
   * Creates a new menu item.
   */
  @name("gtk_menu_item_new")
  def apply(): GtkMenuItem = extern

  /**
   * Creates a new menu item whose child is a [[GtkLabel]].
   *
   * @param label the text for the label
   */
  @name("gtk_menu_item_new_with_label")
  def withLabel(label: CString): GtkMenuItem = extern

  def withLabel(label: String): GtkMenuItem = PoolZone{ implicit z => withLabel(toCString(label)) }

}

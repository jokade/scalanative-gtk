package gtk

import scalanative.native._
import cobj._

/**
 * A base class for menu objects.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkMenuShell.html]]
 */
@CObj
class GtkMenuShell extends GtkContainer {

  /**
   * Adds a new GtkMenuItem to the end of this menu shell's item list.
   *
   * @param child
   */
  def append(child: GtkMenuItem): Unit = extern
}

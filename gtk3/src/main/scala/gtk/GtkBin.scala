package gtk

import de.surfice.smacrotools.debug

import scalanative.native._
import cobj._

/**
 * A container with just one child.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkBin.html]]
 */
@CObj
class GtkBin extends GtkContainer {
  /**
   * Gets the child of this GtkBin, or NULL if the bin contains no child widget.
   *
   * The returned widget does not have a reference added, so you do not need to unref it.
   */
  @inline def getChild(): GtkWidget = extern
}

package gtk

import scalanative.native._
import cobj._

/**
 * Renders text in a cell.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkCellRendererText.html#gtk-cell-renderer-text-new]]
 */
@CObj
class GtkCellRendererText extends  GtkCellRenderer {

}

object GtkCellRendererText {
  @name("gtk_cell_renderer_text_new")
  def apply(): GtkCellRendererText = extern
}

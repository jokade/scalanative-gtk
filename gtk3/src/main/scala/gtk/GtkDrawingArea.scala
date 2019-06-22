package gtk

import scalanative._
import unsafe._
import cobj._

/**
 *  A widget for custom user interface elements.
 *
 *  @see [[https://developer.gnome.org/gtk3/stable/GtkDrawingArea.html]]
 */
@CObj
class GtkDrawingArea extends GtkWidget {

}

object GtkDrawingArea {
  @name("gtk_drawing_area_new")
  def apply(): GtkDrawingArea = extern
}

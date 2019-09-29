package gtk.ui

import glib.gint
import gtk.{GtkBox, GtkOrientation}

trait UIBox extends GtkBox with UIContainer

/**
 * Base class for components that extend GtkBox
 *
 * @param __ptr
 */
abstract class UIBoxComponent(__ptr: GtkPtr) extends GtkBox(__ptr) with UIBox

object UIBoxComponent extends UIComponentFactory[UIBoxComponent] {
  def create(orientation: GtkOrientation, spacing: gint): GtkPtr = GtkBox.__ext.gtk_box_apply(orientation,spacing)
}


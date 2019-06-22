package gtk

import glib.gdouble

import scalanative._
import unsafe._
import cobj._

/**
 * A slider widget for selecting a value from a range.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkScale.html]]
 */
@CObj
class GtkScale extends GtkRange {

}

object GtkScale {
  @name("gtk_scale_new")
  def apply(orientation: GtkOrientation, adjustment: GtkAdjustment): GtkScale = extern

  @name("gtk_scale_new_with_range")
  def withRange(orientation: GtkOrientation, min: gdouble, max: gdouble, step: gdouble): GtkScale = extern
}

package gtk

import scalanative.native._
import cobj._
import glib.gdouble
import gobject.GObject

/**
 * A representation of an adjustable bounded value.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkAdjustment.html]]
 */
@CObj
class GtkAdjustment extends GObject {

}

object GtkAdjustment {
  /**
   * Creates a new GtkAdjustment.
   *
   * @param value the initial value
   * @param lower the minimum value
   * @param upper the maximum value
   * @param stepIncrement the step increment
   * @param pageIncrement the page increment
   * @param pageSize the page size
   * @return
   */
  @name("gtk_adjustment_new")
  def apply(value: gdouble, lower: gdouble, upper: gdouble,
            stepIncrement: gdouble, pageIncrement: gdouble,
            pageSize: gdouble): GtkAdjustment = extern
}

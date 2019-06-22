package gtk

import glib.{gdouble, gulong}

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone

/**
 * Base class for widgets which visualize an adjustment.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkRange.html]]
 */
@CObj
class GtkRange extends GtkWidget {

  /**
   * Returns the current value.
   */
  def getValue(): gdouble = extern
  def setValue(value: gdouble): Unit = extern

  /**
   * Sets the allowable values, and clamps the range value to be between `min` and `max` .
   *
   * @param min minimum range value
   * @param max maximum range value
   */
  def setRange(min: gdouble, max: gdouble): Unit = extern

  def onValueChanged(handler: Function0[Unit])(implicit refZone: RefZone): gulong =
    connect0(c"value-changed",handler)
//  def onValueChanged[T<:GtkRange](handler: Function1[T,Unit])(implicit refZone: RefZone, wrapper: CObjectWrapper[T]): gulong =
//    connect1(c"value-changed", (arg1: Ptr[Byte]) => handler(wrapper.wrap(arg1)))
}

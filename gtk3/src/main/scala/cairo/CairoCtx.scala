package cairo

import scalanative._
import unsafe._
import cobj._

/**
 * The cairo drawing context.
 *
 * @see [[https://developer.gnome.org/cairo/stable/cairo-cairo-t.html#cairo-t]]
 */
@CObj(prefix = "cairo_")
class CairoCtx {
  def setSourceRgb(red: CDouble, green: CDouble, blue: CDouble): Unit = extern
  def rectangle(x: CDouble, y: CDouble, width: CDouble, height: CDouble): Unit = extern
  def fill(): Unit = extern
}

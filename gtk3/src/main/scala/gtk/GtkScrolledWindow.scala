package gtk

import glib.{gboolean, gint}

import scalanative._
import unsafe._
import cobj._

/**
 * Adds scrollbars to its child widget.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkScrolledWindow.html]]
 */
@CObj
class GtkScrolledWindow extends GtkBin {

  def minContentHeight: gint = getIntProp(c"min-content-height")
  def minContentHeight_=(height: gint): Unit = setIntProp(c"min-content-height",height)

  def minContentWidth: gint = getIntProp(c"min-content-width")
  def minContentWidth_=(width: gint): Unit = setIntProp(c"min-content-width",width)

  def maxContentHeight: gint = getIntProp(c"max-content-height")
  def maxContentHeight_=(height: gint): Unit = setIntProp(c"max-content-height",height)

  def maxContentWidth: gint = getIntProp(c"max-content-width")
  def maxContentWidth_=(width: gint): Unit = setIntProp(c"max-content-width",width)

  def propagateNaturalHeight: gboolean = getBooleanProp(c"propagate-natural-height")
  def propagateNaturalHeight_=(setting: gboolean): Unit = setBooleanProp(c"propagate-natural-height",setting)

  def propagateNaturalWidth: gboolean = getBooleanProp(c"propagate-natural-width")
  def propagateNaturalWidth_=(setting: gboolean): Unit = setBooleanProp(c"propagate-natural-width",setting)
}

object GtkScrolledWindow {
  /**
   * Creates a new scrolled window.
   *
   * The two arguments are the scrolled window's adjustments; these will be shared with the scrollbars and the child widget
   * to keep the bars in sync with the child. Usually you want to pass null for the adjustments,
   * which will cause the scrolled window to create them for you.
   *
   * @param hadjustment horizontal adkustment, or null
   * @param vadjustment vertical adjustment, or null
   * @return
   */
  @name("gtk_scrolled_window_new")
  def apply(hadjustment: GtkAdjustment, vadjustment: GtkAdjustment): GtkScrolledWindow = extern

  /**
   * Creates a new scrolled window with default adjustments.
   */
  def apply(): GtkScrolledWindow = apply(null,null)
}

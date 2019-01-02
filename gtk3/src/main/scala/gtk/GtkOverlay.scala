// Copyright (c) 2019. Distributed under the MIT License (see included LICENSE file).
package gtk

import glib.{gboolean, gint}

import scalanative.native._
import cobj._

/**
 * A container which overlays widgets on top of each other.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkOverlay.html]]
 */
@CObj
class GtkOverlay extends GtkBin {

  /**
   * Adds the specified widget to this overlay.
   *
   * The widget will be stacked on top of the main widget addedd with [[GtkContainer.add()]]
   *
   * @param widget
   */
  def addOverlay(widget: GtkWidget): Unit = extern

  /**
   * Moves child to a new index in the list of overlay children.
   *
   * @param child
   * @param position
   */
  def reorderOverlay(child: GtkWidget, position: gint): Unit = extern

  /**
   * Convenience function to get the value of the "pass-through" child property for the specified widget.
   *
   * @param widget
   */
  def getOverlayPassThrough(widget: GtkWidget): gboolean = extern

  /**
   * Convenience function to set the value of the "pass-through" child property for the specified widget.
   *
   * @param widget
   * @param passThrough
   */
  def setOverlayPassThrough(widget: GtkWidget, passThrough: gboolean): Unit = extern
}

object GtkOverlay {
  @name("gtk_overlay_new")
  def apply(): GtkOverlay = extern
}

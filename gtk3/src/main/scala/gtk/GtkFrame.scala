// Copyright (c) 2019. Distributed under the MIT License (see included LICENSE file).
package gtk

import glib.gfloat

import scalanative.native._
import cobj._

/**
 * A bin with a decorative frame and optional label.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkFrame.html]]
 */
@CObj
class GtkFrame extends GtkBin {

  /**
   * Replaces the current label text.
   *
   * @param label
   */
  def setLabel(label: CString): Unit = extern

  /**
   * Replaces the widget used as label.
   * @param widget
   */
  def setLabelWidget(widget: GtkWidget): Unit = extern

  /**
   * Sets the alignment of the label.
   *
   * @param xalign from 0 (left) to 1 (right)
   * @param yalign from 0 (under the frame) to 1 (above the frame)
   */
  def setLabelAlign(xalign: gfloat, yalign: gfloat): Unit = extern

  /**
   * Sets the shadow type for this frame.
   *
   * @param shadowType
   */
  def setShadowType(shadowType: GtkShadowType): Unit = extern

  /**
   * Returns the current label text, or null.
   */
  def getLabel(): CString = extern

  def getLabelAlign(xalign: Out[gfloat], yalign: Out[gfloat]): Unit = extern

  @nullable
  def getLabelWidget(): GtkWidget = extern

  def getShadowType(): GtkShadowType = extern
}

object GtkFrame {

  def apply(): GtkFrame = apply(null)

  /**
   * Creates a new GtkFrame with optional label.
   *
   * @param label text to use as label, or null
   */
  @name("gtk_frame_new")
  def apply(label: CString): GtkFrame = extern
}

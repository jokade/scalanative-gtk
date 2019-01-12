package gtk

import glib.{gboolean, gint, guint}

import scalanative.native._
import cobj._

/**
 * A container box.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkBox.html]]
 *
 * @constructor
 * @param orientation the box's orientation
 * @param spacing  the number of pixels to place by default between children
 */
@CObj
class GtkBox(orientation: GtkOrientation, spacing: gint) extends GtkContainer {
  /**
   * Adds `child` to this box, packed with reference to the start of the box.
   * The child is packed after any other child packed with reference to the start of the box.
   *
   * @param child widget to be added to the box.
   * @param expand true if the new child is to be given extra space allocated to this box.
   *               The extra space will be divided evenly between all children that use this option.
   * @param fill true if the space given to the child by the `expand` option is actually allocated to the child,
   *             rather than just padding it. this parameter has no effect if `expand` is set to false.
   * @param padding extra space in pixels to put between this child and its neighbors,
   *                over and above the global amount specified by “spacing” property.
   *                If the child is a widget at one of the reference ends of this box,
   *                then padding pixels are also put between child and the reference edge of the box.
   */
  @inline def packStart(child: GtkWidget, expand: gboolean, fill: gboolean, padding: guint): Unit = extern

  /**
   * Adds `child` to this box, packed with reference to the end of the box.
   * The child is packed after (away from end of) any other child packed with reference to the end of this box .
   *
   * @param child widget to be added to the box.
   * @param expand true if the new child is to be given extra space allocated to this box.
   *               The extra space will be divided evenly between all children that use this option.
   * @param fill true if the space given to the child by the `expand` option is actually allocated to the child,
   *             rather than just padding it. this parameter has no effect if `expand` is set to false.
   * @param padding extra space in pixels to put between this child and its neighbors,
   *                over and above the global amount specified by “spacing” property.
   *                If the child is a widget at one of the reference ends of this box,
   *                then padding pixels are also put between child and the reference edge of the box.
   */
  @inline def packEnd(child: GtkWidget, expand: gboolean, fill: gboolean, padding: guint): Unit = extern

  /**
   * Returns whether the box is homogeneous (all children are the same size).
   */
  @inline def getHomogeneous(): gboolean = extern

  /**
   * Sets the “homogeneous” property of box, controlling whether or not all children of this box are given equal space in the box.
   *
   * @param homogeneous true to create equal allotmenets, false for variable allotments
   */
  @inline def setHomogeneous(homogeneous: gboolean): Unit = extern

  /**
   * Returns the current spacing of this box.
   */
  @inline def getSpacing(): gint = extern

  /**
   * Sets the “spacing” property of this box, which is the number of pixels to place between children of the box .
   *
   * @param spacing the number of pixels to put between children
   */
  @inline def setSpacing(spacing: gint): Unit = extern
}

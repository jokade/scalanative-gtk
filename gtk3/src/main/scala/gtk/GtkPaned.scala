package gtk

import glib.{gboolean, gint}

import scala.scalanative.native._
import cobj._

/**
 * A widget with two adjustable panes.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkPaned.html]]
 *
 * @constructor
 * @param orientation the paned's orientation
 */
@CObj
class GtkPaned(orientation: GtkOrientation) extends GtkContainer {

  /**
   * Adds a child to the top or left pane with default parameters.
   * This is equivalent to `pack1(child, FALSE, TRUE)`.
   *
   * @param child the child to add
   */
  @inline def add1(child: GtkWidget): Unit = extern

  /**
   * Adds a child to the bottom or right pane with default parameters.
   * This is equivalent to `pack2(child, TRUE, TRUE)`.
   *
   * @param child the child to add
   */
  @inline def add2(child: GtkWidget): Unit = extern

  /**
   * Adds a child to the top or left pane.
   *
   * @param child the child to add
   * @param resize if true, the child should expand when this paned widget is resized
   * @param shrink if true, the child can be made smaller than its requisition
   */
  @inline def pack1(child: GtkWidget, resize: gboolean, shrink: gboolean): Unit = extern

  /**
   * Adds a child to the bottom or right pane.
   *
   * @param child the child to add
   * @param resize if true, the child should expand when this paned widget is resized
   * @param shrink if true, the child can be made smaller than its requisition
   */
  @inline def pack2(child: GtkWidget, resize: gboolean, shrink: gboolean): Unit = extern

  /**
   * Returns the first child of the paned widget, or null.
   */
  @nullable
  @inline def getChild1(): GtkWidget = extern

  /**
   * Returns the second child of the paned widget, or null.
   */
  @nullable
  @inline def getChild2(): GtkWidget = extern

  /**
   * Sets the position of the divider between the two panes.
   *
   * @param position pixel position of the divider, a negative value means that the position is unset.
   */
  @inline def setPosition(position: gint): Unit = extern

  /**
   * Returns the current pixel position of the divider between the two panes.
   */
  @inline def getPosition(): gint = extern

  // @inline def getHandleWindow(): GdkWindow = extern

  /**
   * Sets the "wide-handle" property.
   *
   * @param wide new value for the "wide-handle" property
   */
  @inline def setWideHandle(wide: gboolean): Unit = extern

  /**
   * Returns the value of the "wide-handle" property.
   */
  @inline def getWideHandle(): gboolean = extern


}

object GtkPaned {
  @name("gtk_paned_new")
  def apply(orientation: GtkOrientation): GtkPaned = extern

  def horizontal(): GtkPaned = apply(GtkOrientation.HORIZONTAL)

  def vertical(): GtkPaned = apply(GtkOrientation.VERTICAL)
}

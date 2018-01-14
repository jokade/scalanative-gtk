// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import scala.scalanative.native._

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
}

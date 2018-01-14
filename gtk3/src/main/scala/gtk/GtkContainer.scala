// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import de.surfice.smacrotools.debug
import glib.{gint, guint}

import scalanative.native._

/**
 * Base class for widgets which contain other widgets.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkContainer.html]]
 */
@CObj
class GtkContainer extends GtkWidget {

  /**
   * Adds a widget to this container.
   *
   * Typically used for simple containers such as [[GtkWindow]], [[GtkFrame]], or [[GtkButton]];
   * for more complicated layout containers such as [[GtkBox]] or [[GtkGrid]], this function will pick default
   * packing parameters that may not be correct. So consider functions such as [[GtkBox#packStart]] and [[GtkGrid#attach]]
   * as an alternative to gtk_container_add() in those cases. A widget may be added to only one container at a time;
   * you can’t place the same widget inside two different containers.
   *
   * @param widget
   */
  @inline def add(widget: GtkWidget): Unit = extern

  /**
   * Removes a widget from this container.
   *
   * The widget must be inside this container. Note that container will own a reference to the widget,
   * and that this may be the last reference held; so removing a widget from its container can destroy that widget.
   *
   * @param widget
   */
  @inline def remove(widget: GtkWidget): Unit = extern

  /**
   * Sets the border width of this container.
   * The border width of a container is the amount of space to leave around the outside of the container.
   * The only exception to this is [[GtkWindow]]; because toplevel windows can’t leave space outside, they leave the space inside.
   * The border is added on all sides of the container. To add space to only one side, use a specific “margin” property
   * on the child widget, for example “margin-top”.
   *
   * @param borderWidth amount of blank space to leave outside the container. Valid values are in the range (0-65535 pixels)
   */
  @inline def setBorderWidth(borderWidth: guint): Unit = extern

  /**
   * Retrieves the border width of this conatiner.
   */
  @inline def getBorderWidth(): guint = extern
}

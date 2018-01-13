// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import de.surfice.smacrotools.debug

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

}

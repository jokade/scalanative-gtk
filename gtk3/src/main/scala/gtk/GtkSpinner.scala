package gtk

import scalanative.native._
import cobj._

/**
 * Show a spinner animation.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkSpinner.html]]
 */
@CObj
class GtkSpinner extends GtkWidget {

  /**
   * Starts the animation of the spinner.
   */
  def start(): Unit = extern

  /**
   * Stops the animation of the spinner.
   */
  def stop(): Unit = extern
}

object GtkSpinner {
  @name("gtk_spinner_new")
  def apply(): GtkSpinner = extern
}

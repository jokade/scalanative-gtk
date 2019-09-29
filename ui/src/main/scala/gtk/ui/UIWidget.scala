package gtk.ui

import gtk.GtkWidget

import scala.scalanative.interop.RefZone
import scala.scalanative.unsafe._

/**
 * Base trait for Gtk widgets written in Scala.
 */
trait UIWidget extends GtkWidget {
  /**
   * Default handler for signal 'destroy'
   */
  def onDestroy(): Unit = {}

  /**
   * Default handler for signa 'show'
   */
  def onShow(): Unit = {}

  /**
   * Connects all default signal handlers for this widget.
   */
  def connectDefaultSignalHandlers()(implicit refZone: RefZone): this.type = {
    connect0(c"destroy",onDestroy)
    connect0(c"show",onShow)
    this
  }
}

object UIWidget {
}

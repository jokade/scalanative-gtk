package gtk.ui

import gtk.{GtkWindow, GtkWindowType}
import scalanative._
import unsafe._

trait UIWindow extends GtkWindow with UIContainer {
}

abstract class UIWindowComponent(__ptr: GtkPtr) extends GtkWindow(__ptr) with UIWindow {

}

object UIWindowComponent extends UIComponentFactory[UIWindowComponent] {
  def create(windowType: GtkWindowType): GtkPtr = GtkWindow.__ext.gtk_window_apply(windowType)

}

package gtk.ui

import gtk.GtkFrame

trait UIFrame extends GtkFrame with UIContainer {

}

abstract class UIFrameComponent(__ptr: GtkPtr) extends GtkFrame(__ptr) with UIFrame

object UIFrameComponent {
  def create(): GtkPtr = GtkFrame.__ext.gtk_frame_apply(null)
}

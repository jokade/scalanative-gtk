package gtk.gdk

import scalanative._
import unsafe._
import cobj._

@CObj
object GdkQuartzWindow {
  @name("gdk_quartz_window_get_nsview")
  def getNSView(gdkWindow: Ptr[Byte]): Ptr[Byte] = extern

  def getNSView(gdkWindow: GdkWindow): Ptr[Byte] = getNSView(gdkWindow.handle)
}

package gtk.gdk

import glib.{gboolean, guintptr}
import gobject.GObject

import scalanative._
import unsafe._
import unsigned._
import cobj._
import scala.scalanative.runtime.Intrinsics

/**
 * Onscreen display areas in the target window system.
 *
 * @see [[https://developer.gnome.org/gdk3/stable/gdk3-Windows.html]]
 */
@CObj
class GdkWindow extends GObject {

  def ensureNative(): gboolean = extern

  def handle: Ptr[Byte] = __ptr
}

package gtk.gdk

import gio.{GCancellable, GInputStream}
import glib.GError

import scala.scalanative.native._
import cobj._

/**
 * Image data.
 */
@CObj
class GdkPixbuf {

}

object GdkPixbuf {
  /**
   * Creates a new pixbuf by loading an image from an input stream, or null.
   *
   * The file format is detected automatically. If null is returned, then error will be set.
   *
   * @param stream input stream to load the pixbuf from.
   * @param cancelable optional GCancellable, or null
   * @param error return location for an error, or null
   */
  @nullable
  @name("gdk_pixbuf_new_from_stream")
  def fromStream(stream: GInputStream, cancelable: GCancellable)(implicit error: Out[GError]): GdkPixbuf = extern
}

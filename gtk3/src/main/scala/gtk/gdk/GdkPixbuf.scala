package gtk.gdk

import gio.{GCancellable, GInputStream}
import glib.{GError, gboolean, gint}

import scala.scalanative._
import unsafe._
import cobj._

/**
 * Image data.
 */
@CObj
class GdkPixbuf extends CObject {

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
  def fromStream(stream: GInputStream, cancelable: GCancellable)(implicit error: ResultPtr[GError]): GdkPixbuf = extern

  /**
   * Creates a new pixbuf by loading an image from an input stream.
   *
   * @param stream input stream to load the pixbuf from.
   * @param width The width the image should have, or -1 to not constrain the width
   * @param height The height the image should have, or -1 to not constrain the height
   * @param preserveAspectRatio true to preserve the image's aspect ratio
   * @param cancellable optional GCancellable, or null
   * @param error return location for an error, or null
   * @return
   */
  @nullable
  @name("gdk_pixbuf_new_from_stream_at_scale")
  def fromStreamAtScale(stream: GInputStream, width: gint, height: gint, preserveAspectRatio: gboolean, cancellable: GCancellable)(implicit error: ResultPtr[GError]): GdkPixbuf = extern
}

package gtk

import gtk.gdk.GdkPixbuf

import scalanative.native._
import cobj._

/**
 * A widget displaying an image.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkImage.html]]
 */
@CObj
class GtkImage extends GtkMisc {

  def setFromPixbuf(pixbuf: GdkPixbuf): Unit = extern

  /**
   * Resets the image to be empty.
   */
  def clear(): Unit = extern
}

object GtkImage {
  /**
   * Creates an empty GtkImage.
   */
  @name("gtk_image_new")
  def apply(): GtkImage = extern

  /**
   * Cretes a new GtkImage from the specified file.
   *
   * @param filename
   */
  @name("gtk_image_new_from_file")
  def fromFile(filename: CString): GtkImage = extern
}

package gtk

import glib.{GError, GList, gboolean, gint}
import gobject.GObject
import gtk.gdk.GdkPixbuf

import scalanative._
import unsafe._
import cobj._
import scala.util.{Failure, Success, Try}

/**
 * Looking up icons by name.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkIconTheme.html]]
 */
@CObj
class GtkIconTheme extends GObject {

  /**
   * Checks whether this theme includes the specified icon.
   *
   * @param name the name of the icon
   */
  def hasIcon(name: CString): gboolean = extern

  /**
   * Looks up an icon in this theme, scales it to the given size and renders it into a pixbuf.
   *
   * @param name the name of the icon to look up
   * @param size the desired icon size. The resulting icon may not be exactly this size
   * @param flags flags modifying the behaviour of the icon lookup
   * @param err location to store error information on failure, or null
   */
  def loadIcon(name: CString, size: gint, flags: GtkIconLookupFlags)(implicit err: ResultPtr[GError]): GdkPixbuf = extern

  /**
   * Returns the specified icon, scaled to the given size.
   *
   * @param name the name of the icon to look up.
   * @param size the desired icon size. The resulting icon may not be exactly this size
   * @param flags flags modifying the behaviour of the icon lookup
   */
//  def loadIcon(name: String, size: gint, flags: GtkIconLookupFlags = 0): Try[GdkPixbuf] =
//    GError[Try[GdkPixbuf]](implicit err => PoolZone(implicit z => Success(loadIcon(toCString(name),size,flags)))){ err =>
//      Failure(new RuntimeException(s"could not load icon '$name': ${fromCString(err.message)}"))
//    }

  /**
   * Lists the icons in this theme.
   *
   * @param context a string identifying a particular type of icon, or null to list all icons
   * @return a list holding the names of all icons in this theme.
   */
  def listIcons(context: CString): GList[GtkImage] = extern

  /**
   * Returns a list with the names of all icons defined in this theme.
   *
   * @note The returned list needs to be freed!
   */
//  def allIcons: GList[String] = listIcons(null).asScala[String]
}

object GtkIconTheme {
  /**
   * Returns the icon theme for the default screen
   */
  def getDefault(): GtkIconTheme = extern
}

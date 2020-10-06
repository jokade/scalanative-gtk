package gtk

import glib.utils.GZone
import gobject.GObject

import scalanative._
import unsafe._
import cobj._

/**
 * A filter for selecting a file subset.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkFileFilter.html]]
 */
@CObj
class GtkFileFilter extends GObject {
  def getName(): CString = extern
  def setName(name: CString): Unit = extern

  def name: String = fromCString( getName() )
  def name_=(name: String): Unit = GZone{implicit z => setName(toCString(name))}

  def addMimeType(mimeType: CString): Unit = extern
  def addMimeType(mimeType: String): Unit = GZone{ implicit z => addMimeType(toCString(mimeType))}

  def addPattern(pattern: CString): Unit = extern
  def addPattern(pattern: String): Unit = GZone{ implicit z => addPattern(toCString(pattern)) }
}
object GtkFileFilter {
  @name("gtk_file_filter_new")
  def apply(): GtkFileFilter = extern
}
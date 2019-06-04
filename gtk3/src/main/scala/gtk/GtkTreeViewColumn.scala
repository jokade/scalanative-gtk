package gtk

import glib.gint
import gobject.GObject

import scalanative._
import unsafe._
import cobj._

/**
 * A visible column in a [[GtkTreeView]].
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkTreeViewColumn.html]]
 */
@CObj
class GtkTreeViewColumn extends GObject {

  /**
   * Returns the title of this column.
   */
  def getTitle(): CString = extern
  def setTitle(title: CString): Unit = extern

}

object GtkTreeViewColumn {
  @name("gtk_tree_view_column_new")
  def apply(): GtkTreeViewColumn = extern

  def apply(title: CString, renderer: GtkCellRenderer, attribute: CString, column: gint): GtkTreeViewColumn = new GtkTreeViewColumn(
    ext.gtk_tree_view_column_new_with_attributes(title,renderer.__ptr,attribute,column,null)
  )

  def text(title: CString, column: gint): GtkTreeViewColumn = {
    val renderer = GtkCellRendererText()
    apply(title,renderer,c"text",column)
  }

  @extern
  object ext {
    def gtk_tree_view_column_new_with_attributes(title: CString, renderer: Ptr[Byte], attribute: CString, column: gint, last: Ptr[Byte]): Ptr[Byte] = extern
  }
}

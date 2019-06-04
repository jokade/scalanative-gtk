package gtk

import glib.GLib
import gobject.GObject

import scalanative._
import unsafe._
import cobj._

/**
 * The tree interface used by [[GtkTreeView]].
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkTreeModel.html#GtkTreeModel-struct]]
 */
@CObj
class GtkTreeModel extends GObject {

  /**
   * Returns the string value of the specified  column from the row pointed to be `iter`.
   *
   * @param column column index (starting at 0)
   * @param iter iterator pointing to the row from which the value should be retrieved.
   */
  def getString(column: Int)(implicit iter: GtkTreeIter): String = {
    val ptr = getCString(column)
    val s =fromCString(ptr)
    GLib.free(ptr)
    s
  }

  /**
   * Returns the string value of the specified column from the row pointed to by `iter`.
   *
   * @param column column index (starting at 0)
   * @param iter iterator pointing to the row from which the value should be retireved
   *
   * @note the returned value has to be freed.
   */
  def getCString(column: Int)(implicit iter: GtkTreeIter): CString = {
    val v = stackalloc[CString]
    !v = null
    GtkTreeModel.ext.getString(__ptr,iter.__ptr,column,v,-1)
    !v
  }

  def getPtr(column: Int)(implicit iter: GtkTreeIter): Ptr[Byte] = {
    val v = stackalloc[Ptr[Byte]]
    !v = null
    GtkTreeModel.ext.getString(__ptr,iter.__ptr,column,v,-1)
    !v
  }
}

object GtkTreeModel {
  @extern
  object ext {
//    @name("gtk_tree_model_get")
//    def get(model: Ptr[Byte], iter: Ptr[Ptr[Byte]], column: Int, value: Ptr[Byte], last: Int): Unit = extern
//    @name("gtk_tree_model_get")
//    def getInt(model: Ptr[Byte], iter: Ptr[Byte], column: Int, value: Ptr[Byte], last: Int): Unit = extern
    @name("gtk_tree_model_get")
    def getString(model: Ptr[Byte], iter: Ptr[Byte], column: Int, value: Ptr[CString], last: Int): Unit = extern
  }
}

package gtk

import glib.utils.GZone
import glib.{gboolean, gdouble, gint, gpointer}
import gobject.{GObject, GType}

import scalanative.native._
import cobj._

/**
 * A list-like data structure that can be used with the [[GtkTreeView]]
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkListStore.html]]
 */
@CObj
class GtkListStore extends GtkTreeModel {

  /**
   * Appends a new row to this list store. `iter` will be changed to point to this new row.
   *
   * @param iter A tree iter to be set to the appended row
   */
  def append(implicit iter: GtkTreeIter): Unit = extern

  def set(column: Int, value: CString)(implicit iter: GtkTreeIter): Unit = GtkListStore.ext.gtk_list_store_set(__ptr,iter.__ptr,column,value,-1)
  def set(column: Int, value: String)(implicit iter: GtkTreeIter): Unit = GZone{ implicit z => set(column,toCString(value))}
  def set(column: Int, value: Int)(implicit iter: GtkTreeIter): Unit = GtkListStore.ext.gtk_list_store_set(__ptr,iter.__ptr,column,value,-1)
  def set(column: Int, value: Double)(implicit iter: GtkTreeIter): Unit = GtkListStore.ext.gtk_list_store_set(__ptr,iter.__ptr,column,value,-1)
  def set(column: Int, value: Boolean)(implicit iter: GtkTreeIter): Unit = GtkListStore.ext.gtk_list_store_set(__ptr,iter.__ptr,column,value,-1)

  /**
   * Removes all rows from the list store
   */
  def clear(): Unit = extern

  def appendRow(values: Any*)(implicit iter: GtkTreeIter): Unit = {
    append
    values.zipWithIndex foreach {
//      case (s:Ptr[_],idx) => set(idx,s.cast[CString])
      case (s: String, idx) => set(idx,s)
      case (i: Int, idx)    => set(idx,i)
      case (b: Boolean, idx)=> set(idx,b)
      case (d: Double, idx) => set(idx,d)
      case _ => throw new IllegalArgumentException("unsupported value type")
    }
  }
}

object GtkListStore {
  /**
   * Creates a new list store with the specified column types.
   *
   * @param nColumns number of columns
   * @param types an array of GType types for the columns, from first to last
   */
  @name("gtk_list_store_newv")
  def apply(nColumns: gint, types: Ptr[GType]): GtkListStore = extern

  def apply(types: GType*): GtkListStore = {
    val tarray = stackalloc[GType](types.size)
    for(i<-0 until types.size) {
      !(tarray + i) = types(i)
    }
    apply(types.size,tarray)
  }

  @extern
  object ext {
    def gtk_list_store_set(store: Ptr[Byte],iter: Ptr[Byte],column: gint, value: gint, last: gint): Unit = extern
    def gtk_list_store_set(store: Ptr[Byte],iter: Ptr[Byte],column: gint, value: CString, last: gint): Unit = extern
    def gtk_list_store_set(store: Ptr[Byte],iter: Ptr[Byte],column: gint, value: gdouble, last: gint): Unit = extern
    def gtk_list_store_set(store: Ptr[Byte],iter: Ptr[Byte],column: gint, value: gboolean, last: gint): Unit = extern
  }
}

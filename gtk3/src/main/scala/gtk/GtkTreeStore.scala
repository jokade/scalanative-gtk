package gtk

import glib.utils.GZone
import glib.{gboolean, gdouble, gint}
import gobject.{GObject, GType}

import scalanative._
import unsafe._
import cobj._

/**
 * A tree-like data structure that can be used with the GtkTreeView.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkTreeStore.html]]
 */
@CObj
class GtkTreeStore extends GtkTreeModel {

  /**
   * Appends a new row to this store.
   * If `parent` is non-null, then it will append the new row after the last child of parent.
   *
   * @param iter
   * @param parent
   */
  def append(iter: GtkTreeIter, parent: GtkTreeIter): Unit = extern

  /**
   * Removes all rows from the list store
   */
  def clear(): Unit = extern


  def set(column: Int, value: CString)(implicit iter: GtkTreeIter): Unit = {
    GtkTreeStore.ext.gtk_tree_store_set(__ptr,iter.__ptr,column,value,-1)
  }
  def set(column: Int, value: String)(implicit iter: GtkTreeIter): Unit = GZone{ implicit z => set(column,toCString(value))}
  def set(column: Int, value: Int)(implicit iter: GtkTreeIter): Unit = GtkTreeStore.ext.gtk_tree_store_set(__ptr,iter.__ptr,column,value,-1)
  def set(column: Int, value: Double)(implicit iter: GtkTreeIter): Unit = GtkTreeStore.ext.gtk_tree_store_set(__ptr,iter.__ptr,column,value,-1)
  def set(column: Int, value: Boolean)(implicit iter: GtkTreeIter): Unit = GtkTreeStore.ext.gtk_tree_store_set(__ptr,iter.__ptr,column,value,-1)

  def appendRow(parent: GtkTreeIter, values: Any*)(implicit iter: GtkTreeIter): Unit = {
    append(iter,parent)
    values.zipWithIndex foreach {
      case (s:Ptr[_],idx) => set(idx,s.asInstanceOf[CString])
      case (s: String, idx) => set(idx,s)
      case (i: Int, idx)    => set(idx,i)
      case (b: Boolean, idx)=> set(idx,b)
      case (d: Double, idx) => set(idx,d)
      case (s: CString, idx) => set(idx,s)
      case (p,idx) => set(idx,p.asInstanceOf[Object].asInstanceOf[Ptr[Byte]])
      case _ => throw new IllegalArgumentException("GtkTreeStore.appendRow(): received unsupported value type")
    }
  }
}

object GtkTreeStore {
  /**
   * Creates a new tree store with the specified column types.
   *
   * @param nColumns number of columns
   * @param types an array of GType types for the columns, from first to last
   */
  @name("gtk_tree_store_newv")
  def apply(nColumns: gint, types: Ptr[GType]): GtkTreeStore = extern

  def apply(types: GType*): GtkTreeStore = {
    val tarray = stackalloc[GType](types.size)
    for(i<-0 until types.size) {
      !(tarray + i) = types(i)
    }
    apply(types.size,tarray)
  }

  @extern
  object ext {
    def gtk_tree_store_set(store: Ptr[Byte],iter: Ptr[Byte],column: gint, value: gint, last: gint): Unit = extern
    def gtk_tree_store_set(store: Ptr[Byte],iter: Ptr[Byte],column: gint, value: CString, last: gint): Unit = extern
    def gtk_tree_store_set(store: Ptr[Byte],iter: Ptr[Byte],column: gint, value: gdouble, last: gint): Unit = extern
    def gtk_tree_store_set(store: Ptr[Byte],iter: Ptr[Byte],column: gint, value: gboolean, last: gint): Unit = extern
  }
}
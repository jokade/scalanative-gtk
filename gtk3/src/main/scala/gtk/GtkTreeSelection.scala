package gtk

import glib.{GDestroyNotify, gboolean, gpointer}
import gobject.GObject

import scalanative._
import unsafe._
import cobj._

/**
 * The selection object for GtkTreeView.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkTreeSelection.html#gtk-tree-selection-get-selected]]
 */
@CObj
class GtkTreeSelection extends GObject {

  /**
   * Returns the current selection function, or null.
   */
  @nullable
  def getSelectFunction(): GtkTreeSelectionFunc = extern

  /**
   * Sets the selection function.
   *
   * @param func the selection function (may be null)
   * @param data the selection function's data (may be null)
   * @param destroy the destroy function for user data (may be null)
   */
  def setSelectFunction(func: GtkTreeSelectionFunc, data: gpointer, destroy: GDestroyNotify): Unit = extern

  /**
   *  Returns the current user data for the selection function.
   */
  def getUserData(): gpointer = extern

  /**
   * Returns the tree view associated with this selection.
   */
  def getTreeView(): GtkTreeView = extern

  /**
   * Sets the specified tree iter to the currently selected node, if [[mode]] is set to [[GtkSelectionMode.SINGLE]] or [[GtkSelectionMode.BROWSE]].
   *
   * @param model If not null, contains the data model for the selection on return
   * @param iter the iterator to use (may be null, if you only wnat to test if there is a selected row)
   * @return true if there is a selected node
   */
  def getSelected(implicit model: ResultPtr[GtkTreeModel], iter: GtkTreeIter): gboolean = extern

  /**
   * Returns the current selection mode.
   */
  def mode: GtkSelectionMode = getIntProp(c"mode")
  def mode_=(mode: GtkSelectionMode): Unit = setIntProp(c"mode",mode)
}

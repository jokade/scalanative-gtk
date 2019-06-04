package gtk

import de.surfice.smacrotools.debug
import glib.{GList, gboolean, gint, guint}

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.PoolZone

/**
 * A widget for displaying both trees and lists.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkTreeView.html#gtk-tree-view-new]]
 */
@CObj
class GtkTreeView extends GtkContainer {

  private lazy val _selection = getSelection()

  /**
   * Returns the amount in pixels of extra indentation for child levels.
   */
  def getLevelIndentation(): gint = extern
  def setLevelIndentation(indentation: gint): Unit = extern

  /**
   * Returns whether or not expanders are drawn.
   */
  def getShowExpanders(): gboolean = extern
  def setShowExpanders(enabled: gboolean): Unit = extern

  /**
   * Returns the underlying data model.
   */
  def getModel(): GtkTreeModel = extern
  def setModel(model: GtkTreeModel): Unit = extern

  /**
   * Returns the selection object associated with this tree.
   */
  def getSelection(): GtkTreeSelection = extern

  /**
   * Returns true if the headers are visible.
   */
  def getHeadersVisible(): gboolean = extern
  def setHeadersVisible(visible: gboolean): Unit = extern

  /**
   * Returns whether all header columns are clickable.
   * @return
   */
  def getHeadersClickable(): gboolean = extern
  def setHeadersClickable(clickable: gboolean): Unit = extern

  /**
   * Returns true if rows are activated by a single click isntead of a double click.
   */
  def getActivateOnSingleClick(): gboolean = extern
  def setActivateOnSingleClick(activate: gboolean): Unit = extern

  /**
   * Resizes all columns to their optimal width
   */
  def columnsAutosize(): Unit = extern

  /**
   * Appends a column to this tree view.
   *
   * @param column column to be appended.
   * @return The number of columns in this tree view after appending.
   */
  def appendColumn(column: GtkTreeViewColumn): gint = extern

  /**
   * Appends a text column to this view.
   *
   * @param title title of the new column
   * @return The appended column.
   */
  def appendTextColumn(title: String): GtkTreeViewColumn = PoolZone{ implicit z =>
    val col = GtkTreeViewColumn.text(toCString(title),getNColumns().toInt)
    appendColumn(col)
    col
  }

  /**
   * Removes the specified column from this tree view.
   *
   * @param column
   * @return The number of columns in this tree view after removing.
   */
  def removeColumn(column: GtkTreeViewColumn): gint = extern

  /**
   * Inserts a column into this tree view at the specified position.
   *
   * @param column column to be inserted
   * @param position The position to insert column in; if -1, then the column is appended
   * @return the number of columns after insertion.
   */
  def insertColumn(column: GtkTreeViewColumn, position: gint): gint = extern

  /**
   * Returns the number of columns.
   */
  def getNColumns(): guint = extern

  /**
   * Returns the column at the specified position, or null.
   *
   * @param index The position of the column (counting from 0)
   */
  @nullable
  def getColumn(index: gint): GtkTreeViewColumn = extern

  /**
   * Returns a list with all columns in this tree view.
   */
  def getColumns(): GList[GtkTreeViewColumn] = extern

  def withSelection(f: GtkTreeIter=>Any): Unit = GtkTreeIter{ implicit iter =>
    _selection.getSelected(null,iter)
    f(iter)
  }

//  def onRowActivated[T](callback: CFuncPtr4[Ptr[Byte],Ptr[Byte],Ptr[Byte],T,_], data: T)(implicit wrapper: CObjectWrapper[T]): Unit = connect(c"row-activated",callback,data)
}

object GtkTreeView {
  /**
   * Creates a new GtkTreeView widget.
   */
  @name("gtk_tree_view_new")
  def apply(): GtkTreeView = extern

  /**
   * Creates a new tree view using the specified model.
   *
   * @param model
   */
  def apply(model: GtkTreeModel): GtkTreeView = newWithModel(model)

  def newWithModel(model: GtkTreeModel): GtkTreeView = extern
}

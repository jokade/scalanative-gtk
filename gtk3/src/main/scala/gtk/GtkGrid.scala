package gtk

import glib.{gboolean, gint, guint}

import scalanative._
import unsafe._
import cobj._

/**
 * Pack widgets in rows and columns.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkGrid.html]]
 */
@CObj
class GtkGrid extends GtkContainer {

  /**
   * Adds a widget to this grid.
   * The position of child is determined by `left` and `top`.
   * The number of “cells” that child will occupy is determined by `width` and `height`.
   *
   * @param child the widget to add
   * @param left the column number to attach the left side of child to
   * @param top the row number to attach the top side of child to
   * @param width the number of columns that child will span
   * @param height the number of rows the child will span
   */
  @inline def attach(child: GtkWidget, left: gint, top: gint, width: gint, height: gint): Unit = extern

  /**
   * Adds a widget to the grid, placed next to `sibling`, on the side determined by `side`.
   * When sibling is null, the widget is placed in row (for left or right placement) or column 0 (for top or bottom placement),
   * at the end indicated by side.
   *
   * @param child the widget to add
   * @param sibling the child of grid that child will be placed next to, or null to place child at the beginning or end.
   * @param side the side of sibling that child is positioned next to
   * @param width the number of columns that child will span
   * @param height the number of rows that child will span
   */
  @inline def attachNextTo(child: GtkWidget, sibling: GtkWidget, side: GtkPositionType, width: gint, height: gint): Unit = extern

  /**
   * Gets the child of this grid whose area covers the grid cell whose upper left corner is at `left`, `top`.
   *
   * @param left the left edge of the cell
   * @param top the top edge of the cell
   * @return the child at the given position, or null
   */
  @inline def getChildAt(left: gint, top: gint): GtkWidget = extern

  /**
   * Inserts a row at the specified position.
   * Children which are attached at or below this position are moved one row down.
   * Children which span across this position are grown to span the new row.
   *
   * @param position the position to insert the row at
   */
  @inline def insertRow(position: gint): Unit = extern

  /**
   * Inserts a column at the specified position.
   * Children which are attached at or to the right of this position are moved one column to the right.
   * Children which span across this position are grown to span the new column.
   *
   * @param position the position to insert the column at
   */
  @inline def insertColumn(position: gint): Unit = extern

  /**
   * Removes a row from the grid.
   * Children that are placed in this row are removed, spanning children that overlap this row have their height reduced by one,
   * and children below the row are moved up.
   *
   * @param position the position of the row to remove
   */
  @inline def removeRow(position: gint): Unit = extern

  /**
   * Removes a column from the grid.
   * Children that are placed in this column are removed, spanning children that overlap this column have their width
   * reduced by one, and children after the column are moved to the left.
   *
   * @param position the position of the column to remove
   */
  @inline def removeColumn(position: gint): Unit = extern

  /**
   * Sets the amount of space between rows of this grid.
   *
   * @param spacing amount of space to insert between rows
   */
  @inline def setRowSpacing(spacing: guint): Unit = extern

  /**
   * Returns the amount of space between rows in this grid.
   */
  @inline def getRowSpacing(): guint = extern

  /**
   * Sets the amount of space between columns of this grid.
   *
   * @param spacing amount of space to insert between columns
   */
  @inline def setColumnSpacing(spacing: guint): Unit = extern

  /**
   * Returns the amount of space between columns in this grid.
   */
  @inline def getColumnSpacing(): guint = extern

  /**
   * If true, the rows are all the same height.
   */
  def rowHomogeneous: gboolean = getBooleanProp(c"row-homogeneous")
  def rowHomogeneous_=(flag: gboolean): Unit = setBooleanProp(c"row-homogeneous",flag)

  /**
   * If true, the columns are all the same height.
   */
  def columnHomogeneous: gboolean = getBooleanProp(c"column-homogeneous")
  def columnHomogeneous_=(flag: gboolean): Unit = setBooleanProp(c"column-homogeneous",flag)

  def rowSpacing: Int = getIntProp(c"row-spacing")
  def rowSpacing_=(spacing: Int): Unit = setIntProp(c"row-spacing",spacing)

  def columnSpacing: Int = getIntProp(c"column-spacing")
  def columnSpacing_=(spacing: Int): Unit = setIntProp(c"column-spacing",spacing)
}

object GtkGrid {
  @name("gtk_grid_new")
  def apply(): GtkGrid = extern

  @name("gtk_grid_new")
  def newRaw(): Ptr[Byte] = extern
}

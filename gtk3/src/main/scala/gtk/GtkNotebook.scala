package gtk

import glib.{gboolean, gint}

import scalanative._
import unsafe._
import cobj._

/**
 * A tabbed notebook container.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkNotebook.html]]
 */
@CObj
class GtkNotebook extends GtkContainer {

  /**
   * Appends a page to this notebook.
   *
   * @param child the widget to use as the content of the page
   * @param tabLabel the widget to be used as the label for the page, or null to use the default
   * @return the index (starting from 0) of the appended page in the notebook, or -1 if an error occurred
   */
  @inline final def appendPage(child: GtkWidget, tabLabel: GtkWidget): gint = extern

  /**
   * Appends a page to this notebook, specifying the widget to use as the label in the popup menu.
   *
   * @param child the widget to use as the content of the page
   * @param tabLabel the widget to be used as the label for the page, or null to use the default
   * @param menuLabel the widget to use as a label for the page-switch menu, if that is enabled.
   *                  If null, and tabLabel is a GtkLabel or null, then the menu label will be a newly
   *                  created label with the same text as tabLabel. If tabLabel is not a GtkLabel,
   *                  menuLabel must be specified if the page-switch menu is to be used
   * @return the index (starting from 0) of the appended page in the notebook, or -1 if an error occurred
   */
  @inline final def appendPageMenu(child: GtkWidget, tabLabel: GtkWidget, menuLabel: GtkWidget): gint = extern

  /**
   * Prepends a page to this notebook.
   *
   * @param child the widget to use as the content of the page
   * @param tabLabel the widget to be used as the label for the page, or null to use the default
   * @return the index (starting from 0) of the prepended page in the notebook, or -1 if an error occurred
   */
  @inline final def prependPage(child: GtkWidget, tabLabel: GtkWidget): gint = extern

  /**
   * Prepends a page to this notebook, specifying the widget to use as the label in the popup menu.
   *
   * @param child the widget to use as the content of the page
   * @param tabLabel the widget to be used as the label for the page, or null to use the default
   * @param menuLabel the widget to use as a label for the page-switch menu, if that is enabled.
   *                  If null, and tabLabel is a GtkLabel or null, then the menu label will be a newly
   *                  created label with the same text as tabLabel. If tabLabel is not a GtkLabel,
   *                  menuLabel must be specified if the page-switch menu is to be used
   * @return the index (starting from 0) of the prepended page in the notebook, or -1 if an error occurred
   */
  @inline final def prependPageMenu(child: GtkWidget, tabLabel: GtkWidget, menuLabel: GtkWidget): gint = extern

  /**
   * Inserts a page into this notebook at the given position.
   *
   * @param child the widget to use as the content of the page
   * @param tabLabel the widget to be used as the label for the page, or null to use the default
   * @param position the index (starting at 0) at which to insert the page, or -1 to append the page after
   *                 all other pages
   * @return the index ( starting from 0) of the inserted page, or -1 if an error occurred
   */
  @inline final def insertPage(child: GtkWidget, tabLabel: GtkWidget, position: gint): gint = extern

  /**
   * Inserts a page into this notebook at the given position, specifying the widget to use as the label in the popup menu.
   *
   * @param child the widget to use as the content of the page
   * @param tabLabel the widget to be used as the label for the page, or null to use the default
   * @param menuLabel the widget to use as a label for the page-switch menu, if that is enabled.
   *                  If null, and tabLabel is a GtkLabel or null, then the menu label will be a newly
   *                  created label with the same text as tabLabel. If tabLabel is not a GtkLabel,
   *                  menuLabel must be specified if the page-switch menu is to be used
   * @param position the index (starting at 0) at which to insert the page, or -1 to append the page after
   *                 all other pages
   * @return the index ( starting from 0) of the inserted page, or -1 if an error occurred
   */
  @inline final def insertPageMenu(child: GtkWindow, tabLabel: GtkWidget, menuLabel: GtkWindow, position: gint): gint = extern

  /**
   * Removes a page from this notebook.
   *
   * @param pageNum the index of the page to be removed (starting from 0). If -1, the last page will be removed
   */
  @inline final def removePage(pageNum: gint): Unit = extern

  /**
   * Removes the child from this notebook.
   * This function is very similar to [[GtkContainer#remove]], but additionally informs the notebook
   * that the removal is happening as part of a tab DND operation, which should not be cancelled.
   *
   * @param child the child to be detached
   */
  @inline final def detachTab(child: GtkWidget): Unit = extern

  /**
   * Returns the index of the page which contains the given child, or -1 if child is not in this notebook.
   *
   * @param child a GtkWidget
   */
  @inline final def pageNum(child: GtkWidget): gint = extern

  /**
   * Switches to the next page.
   * Nothing happens if the current page is the last page.
   */
  @inline final def nextPage(): Unit = extern

  /**
   * Switches to the previous page.
   * Nothing happens if the current page is the first page.
   */
  @inline final def prevPage(): Unit = extern

  /**
   * Reorders the page containing `child`, so that it appears in position `position`.
   * If position is greater than or equal to the number of children in the list or negative,
   * child will be moved to the end of the list.
   *
   * @param child the child to move
   * @param position the new position, or -1 to move to the end
   */
  @inline final def reorderChild(child: GtkWidget, position: gint): Unit = extern

  /**
   * Sets the edge at which the tabs for switching pages in the notebook are drawn.
   *
   * @param pos the edge to draw the tabs at
   */
  @inline final def setTabPos(pos: GtkPositionType): Unit = extern

  /**
   * Sets whether to show the tabs for the notebook or not.
   *
   * @param showTabs true if the tabs should be shown
   */
  @inline final def setShowTabs(showTabs: gboolean): Unit = extern

  /**
   * Sets whether a bevel will be drawn around the notebook pages.
   * This only has a visual effect when the tabs are not shown.
   *
   * @param showBorder true if a bevel should be drawn around the notebook
   */
  @inline final def setShowBorder(showBorder: gboolean): Unit = extern

  /**
   * Sets whether the tab label area will have arrows for scrolling if there are too many tabs to fit in the area.
   *
   * @param scrollable true if scroll arrows should be added
   */
  @inline final def setScrollable(scrollable: gboolean): Unit = extern

  /**
   * Enables the popup menu: if the user clicks with the right mouse button on the tab labels,
   * a menu with all pages will be popped up.
   */
  @inline final def popupEnable(): Unit = extern

  /**
   * Disables the popup menu.
   */
  @inline final def popupDisable(): Unit = extern

  /**
   * Returns the page number of the current page (starting from 0).
   * If the notebook has no pages, then -1 will be returned.
   */
  @inline final def getCurrentPage(): gint = extern

  /**
   * Retrieves the menu label widget of the page containing `child`,
   * or null if the notebook page does not have a menu label other than the default.
   *
   * @note the returned widget is always a `GtkWidget`, not one of its subclass!
   *
   * @param child a widget contained in a page of this notebook
   */
  @nullable
  @inline final def getMenuLabel(child: GtkWidget): GtkWidget = extern

  /**
   * Returns the child widget contained in the given page number,
   * or null if the page does not exist.
   *
   * @note the returned widget is always a `GtkWidget`, not one of its subclass!
   *
   * @param pageNum the index of the page, or -1 to get the last page
   */
  @nullable
  @inline final def getNthPage(pageNum: gint): GtkWidget = extern

  /**
   * Returns the number of pages in this notebook.
   */
  @inline final def getNPages(): gint = extern

  /**
   * Returns the tab label widget for the given child,
   * or null if the child is not present in this notebook,
   * or no tab label has been set for the child.
   *
   * @note the returned widget is always a `GtkWidget`, not one of its subclass!
   *
   * @param child the page
   */
  @nullable
  @inline final def getTabLabel(child: GtkWidget): GtkWidget = extern

  /**
   * Changes the menu label for the page containing `child`.
   *
   * @param child the child widget
   * @param menuLabel the menu label, or null for default
   */
  @inline final def setMenuLabel(child: GtkWidget, menuLabel: GtkWidget): Unit = extern

  /**
   * Creates a new label and sets it as the menu label of `child`.
   *
   * @param child the child widget
   * @param menuText the label text
   */
  @inline final def setMenuLabelText(child: GtkWidget, menuText: CString): Unit = extern

  /**
   * Changes the tab label for the given child.
   *
   * @param child the page
   * @param tabLabel the tab label, or null for the default tab label
   */
  @inline final def setTabLabel(child: GtkWidget, tabLabel: GtkWidget): Unit = extern

  /**
   * Creates a new label and sets it as the tab label for the page containing child.
   *
   * @param child the page
   * @param tabText the label text
   */
  @inline final def setTabLabelText(child: GtkWidget, tabText: CString): Unit = extern

  /**
   * Sets whether the notebook tab can be reordered via drag and drop, or not.
   *
   * @param child the page
   * @param reorderable true if the page can be reordered
   */
  @inline final def setTabReorderable(child: GtkWidget, reorderable: gboolean): Unit = extern

  /**
   * Sets whether the tab can be detached from this notebook to another notebook or widget.
   *
   * @param child the page
   * @param detachable true if the tab is detachable
   */
  @inline final def setTabDetachable(child: GtkWidget, detachable: gboolean): Unit = extern

  /**
   * Returns the text of the menu label for the page containing `child`, or null if the widget
   * does not have a menu label othe than the default, or the menu label widget is not a
   * GtkLabel.
   *
   * @param child the child widget of a page of the notebook
   */
  @inline final def getMenuLabelText(child: GtkWidget): CString = extern

  /**
   * Returns whether the tab label area has arrows for scrolling.
   */
  @inline final def getScrollable(): gboolean = extern

  /**
   * Returns whether a bevel will be drawn around the notebook pages.
   */
  @inline final def getShowBorder(): gboolean = extern

  /**
   * Returns whether the tabs of the notebook are shown.
   */
  @inline final def getShowTabs(): gboolean = extern

  /**
   * Returns the tab label for the page containing `child`, or null if the tab label
   * widget is not a GtkLabel. The string is owned by the widget and must not be freed.
   */
  @inline final def getTabLabelText(): CString = extern

  /**
   * Returns the edge at which the tabs for switching pages in the notebook are drawn.
   */
  @inline final def getTabPos(): GtkPositionType = extern

  /**
   * Returns whether the tab can be reordered via drag and drop.
   *
   * @param child a child GtkWidget
   */
  @inline final def getTabReorderable(child: GtkWidget): gboolean = extern

  /**
   * Returns whether the tab contents can be detached from this notebook.
   *
   * @param child a child widget
   */
  @inline final def getTabDetachable(child: GtkWidget): gboolean = extern

  /**
   * Switches to the given page number.
   *
   * @param pageNum index of the page to switch to (starting from 0). If negative,
   *                the last page will be used. If greater than the number of pages in the notebook,
   *                nothing will be done
   */
  @inline final def setCurrentPage(pageNum: gint): Unit = extern

  /**
   * Sets a group name for this notebook.
   * Notebooks with the same name will be able to exchange tabs via drag and drop.
   * A notebook with a null group name will not be able to exchange tabs with any other notebook.
   *
   * @param groupName the name of the notebook group, or null
   */
  @inline final def setGroupName(groupName: CString): Unit = extern

  /**
   * Returns the group name of this notebook.
   */
  @inline final def getGroupName(): CString = extern

  /**
   * Sets `widget` as one of the action widgets.
   *
   * Depending on the pack type the widget will be placed before or after the tabs.
   * You can use a GtkBox if you need to pack more than one widget on the same side.
   *
   * Note that action widgets are “internal” children of the notebook and thus not included in the list returned from
   * [[GtkContainer#foreach]]
   *
   * @param widget a GtkWidget
   * @param packType pack type of the action widget
   */
  @inline final def setActionWidget(widget: GtkWidget, packType: GtkPackType): Unit = extern

  /**
   * Returns one of the action widgets, or null when the action widget has not been set.
   *
   * @param packType pack type of the action widget to retrive
   */
  @nullable
  @inline final def getActionWidget(packType: GtkPackType): GtkWidget = extern
}

object GtkNotebook {
  @name("gtk_notebook_new")
  def apply(): GtkNotebook = extern
}
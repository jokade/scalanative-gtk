package gtk

import glib.guint

import scalanative._
import unsafe._
import cobj._

/**
 * A full width bar for presenting contextual actions
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkActionBar.html]]
 */
@CObj
class GtkActionBar extends GtkBin {
  /**
   * Adds the specified child to the start of this bar.
   *
   * @param child
   */
  def packStart(child: GtkWidget): Unit = extern

  /**
   * Adds the specfified child to the end of this bar.
   * @param child
   */
  def packEnd(child: GtkWidget): Unit = extern

  def getCenterWidget(): GtkWidget = extern

  def setCenterWidget(widget: GtkWidget): Unit = extern

  def packType: GtkPackType = getIntProp(c"pack-type")
  def packType_=(ptype: GtkPackType): Unit = setIntProp(c"pack-type",ptype)

  def position: guint = getUIntProp(c"position")
  def position_=(pos: guint): Unit = setUIntProp(c"position",pos)
}

object GtkActionBar {
  @name("gtk_action_bar_new")
  def apply(): GtkActionBar = extern
}

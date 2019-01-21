package gtk

import scalanative.native._
import cobj._

/**
 * A box with a centered child.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkHeaderBar.html]]
 */
@CObj
class GtkHeaderBar extends GtkContainer {
  def title: String = getStringProp(c"title")
  def title_=(title: String): Unit = setStringProp(c"title",title)

  def subtitle: String = getStringProp(c"subtitle")
  def subtitle_=(subtitle: String): Unit = setStringProp(c"subtitle",subtitle)

  /**
   * Adds the specified child to the start of this bar.
   *
   * @param child
   */
  def packStart(child: GtkWidget): Unit = extern

  /**
   * Adds the specified child to the end of this bar.
   * @param child
   */
  def packEnd(child: GtkWidget): Unit = extern
}

object GtkHeaderBar {
  @name("gtk_header_bar_new")
  def apply(): GtkHeaderBar = extern
}

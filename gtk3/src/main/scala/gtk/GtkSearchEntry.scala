// Copyright (c) 2019. Distributed under the MIT License (see included LICENSE file).
package gtk

import scalanative.native._
import cobj._

/**
 * An entry which shows a search icon.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkSearchEntry.html]]
 */
@CObj
class GtkSearchEntry extends GtkEntry {

}

object GtkSearchEntry {
  @name("gtk_search_entry_new")
  def apply(): GtkSearchEntry = extern
}

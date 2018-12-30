// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import scalanative.native._
import cobj._

/**
 * A single line text entry field.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkEntry.html]]
 */
@CObj
class GtkEntry extends GtkWidget {

  /**
   * Sets the text in the widget to the given value, replacing the current contents.
   *
   * @param text the new text
   */
  @inline def setText(text: CString): Unit = extern

  /**
   * Retrieves the contents of the entry widget.
   *
   * @return a pointer to the contents of the widget as a string.
   *         This string points to internally allocated storage in the widget and must not be freed, modified or stored.
   */
  @inline def getText(): CString = extern

  /**
   * Retrieves the contents of the entry widget.
   */
  @inline def text: String = fromCString(getText())

}

object GtkEntry {
  @name("gtk_entry_new")
  def apply(): GtkEntry = extern
}
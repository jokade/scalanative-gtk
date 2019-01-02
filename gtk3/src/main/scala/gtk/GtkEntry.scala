// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import glib.{gboolean, gfloat, gint, guint16}

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
  def setText(text: CString): Unit = extern

  /**
   * Retrieves the contents of the entry widget.
   *
   * @return a pointer to the contents of the widget as a string.
   *         This string points to internally allocated storage in the widget and must not be freed, modified or stored.
   */
  def getText(): CString = extern

  /**
   * Retrieves the contents of the entry widget.
   */
  def text: String = fromCString(getText())

  /**
   * Returns the current length of the text entry.
   */
  def getTextLength(): guint16 = extern

  /**
   * Sets whether the contents of the entry are visible or not.
   *
   * @param visible
   */
  def setVisibility(visible: gboolean): Unit = extern

  /**
   * Sets the maximum allowed length of the contents of the widget.
   *
   * @param max the maxmimum length of the entry, or 0 for no maximum.
   */
  def setMaxLength(max: gint): Unit = extern

  /**
   * If true, pressing Enter in the entry will activate the default widget for the window containing the entry.
   * This usually means that the dialog box containing the entry will be closed.
   */
  def getActivatesDefault(): gboolean = extern
  def setActivatesDefault(setting: gboolean): Unit = extern

  /**
   * Returns true when the entry has a beveled frame around it.
   */
  def getHasFrame(): gboolean = extern
  def setHasFrame(setting: gboolean): Unit = extern

  /**
   * Returns the preferred size request for this entry in characters.
   */
  def getWidthChars(): gint = extern

  /**
   * Changes the size request of this entry to be about the right size for nChars characters.
   *
   * @param nChars width in chars
   */
  def setWidthChars(nChars: gint): Unit = extern

  /**
   * Returns the desired maximum width in characters.
   */
  def getMaxWidthChars(): gint = extern

  /**
   * Sets the desired maximum width in characters.
   * @param nChars
   */
  def setMaxWidthChars(nChars: gint): Unit = extern

  /**
   * Returns the horizontal alignment for the contents in this entry.
   * @return 0 (left) to 1 (right). Reversed for RTL layouts.
   */
  def getAlignment(): gfloat = extern

  /**
   * Sets the alignment for the contents of this entry.
   *
   * @param xalign The horizontal alignment, from 0 (left) to 1 (right). Reversed for RTL layouts.
   */
  def setAlignment(xalign: gfloat): Unit = extern

  /**
   * Returns the text displayed when the entry box is empty.
   */
  def getPlaceholderText(): CString = extern

  /**
   * Sets the text to be displayed in this entry when it is empty and unfocused.
   *
   * @param text
   */
  def setPlaceholderText(text: CString): Unit = extern

  /**
   * Returns whether text is overwritten when typing in the GtkEntry.
   */
  def getOverwriteMode(): gboolean = extern

  /**
   * Sets whether the text is overwritten when typing in this entry.
   *
   * @param overwrite
   */
  def setOverwriteMode(overwrite: gboolean): Unit = extern
}

object GtkEntry {
  @name("gtk_entry_new")
  def apply(): GtkEntry = extern
}
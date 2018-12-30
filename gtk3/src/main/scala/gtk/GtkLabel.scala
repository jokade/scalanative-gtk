// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import scalanative.native._
import cobj._

/**
 * A widget that display a small to medium amount of text.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkLabel.html]]
 */
@CObj
class GtkLabel extends GtkMisc {
  /**
   * Sets the text within the GtkLabel widget. It overwrites any text that was there before.
   *
   * @param str new text for the label
   */
  @inline def setText(str: CString): Unit = extern

  /**
   * Fetches the text from this label, as displayed on the screen.
   * This does not include any embedded underlines indicating mnemonics or Pango markup.
   */
  @inline def getText(): CString = extern

  /**
   * Fetches the text from this label, as displayed on the screen.
   * This does not include any embedded underlines indicating mnemonics or Pango markup.
   */
  @inline def text: String = fromCString(getText())

  /**
   * Sets the text within the GtkLabel widget. It overwrites any text that was there before.
   */
  def text_=(t: String)(implicit z: Zone): Unit =
    if(z==null) Zone{ implicit z => setText(toCString(t)) }
    else setText(toCString(t))

  /**
   * Parses str which is marked up with the Pango text markup language,
   * setting the label’s text and attribute list based on the parse results.
   *
   * @param str amarkup string (see Pango markup format)
   */
  @inline def setMarkup(str: CString): Unit = extern

  /**
   * Parses str which is marked up with the Pango text markup language,
   * setting the label’s text and attribute list based on the parse results.
   *
   * @param str amarkup string (see Pango markup format)
   */
  def setMarkup(str: String)(implicit z: Zone = null): Unit =
    if(z==null) Zone{ implicit z => setMarkup(toCString(str)) }
    else setMarkup(toCString(str))
}

object GtkLabel {
  /**
   * Creates a new GtkLabel.
   *
   * @param str the text of the label
   */
  @name("gtk_label_new")
  def apply(str: CString): GtkLabel = extern
}
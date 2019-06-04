package gtk

import glib.utils.GZone
import glib.{gboolean, gfloat}

import scalanative._
import unsafe._
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
  def text_=(t: String): Unit = GZone{ implicit z => setText(toCString(t)) }

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
   * @param str a markup string (see Pango markup format)
   */
  def setMarkup(str: String): Unit = GZone{ implicit z =>
    setMarkup(toCString(str))
  }

  /**
   * If set, wrap lines if the text becomes too wide.
   * @return
   */
  def wrap: gboolean = getBooleanProp(c"wrap")
  def wrap_=(flag: gboolean): Unit = setBooleanProp(c"wrap",flag)

  /**
   * If line wrapping is on, this controls how the line wrapping is done.
   *
   * Default value: PangoWrapMode.WORD
   */
  def wrapMode: PangoWrapMode = getIntProp(c"wrap-mode")
  def wrapMode_=(mode: PangoWrapMode): Unit = setIntProp(c"wrap-mode",mode)

  /**
   * The xalign property determines the horizontal alignment of the label text inside the labels size allocation.
   *
   * Allowed values: [0,1]
   * Default value: 0.5
   */
  def xalign: gfloat = getFloatProp(c"xalign")
  def xalign_=(xalign: gfloat): Unit = setFloatProp(c"xalign",xalign)

  /**
   * The yalign property determines the vertical alignment of the label teyt inside the labels size allocation.
   *
   * Allowed values: [0,1]
   * Default value: 0.5
   */
  def yalign: gfloat = getFloatProp(c"yalign")
  def yalign_=(yalign: gfloat): Unit = setFloatProp(c"yalign",yalign)

  /**
   * Returns the justification of this label.
   */
  def getJustify(): GtkJustification = extern

  /**
   * Sets the alignment of the lines in the text of the label relative to each other.
   */
  def setJustify(jtype: GtkJustification): Unit = extern

  /**
   * Returns true if the user can copy text from the label
   */
  def getSelectable(): gboolean = extern

  /**
   * If true, allow the user to select text from the label.
   *
   * @param setting
   */
  def setSelectable(setting: gboolean): Unit = extern
}

object GtkLabel {
  /**
   * Creates a new GtkLabel.
   *
   * @param str the text of the label
   */
  @name("gtk_label_new")
  def apply(str: CString): GtkLabel = extern

  def apply(str: String): GtkLabel = GZone{ implicit z => apply(toCString(str)) }

  def apply(): GtkLabel = apply(c"")
}
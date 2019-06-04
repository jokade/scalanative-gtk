package gtk

import de.surfice.smacrotools.debug
import glib.gboolean
import gobject.GObject

import scalanative._
import unsafe._
import cobj._

/**
 * A position in the buffer preserved across buffer modifications
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkTextMark.html]]
 * @constructor
 * @param name mark name or null; if name is null, the mark is anonymous; otherwise the
 * @param leftGravity whether the mark should have left gravity
 */
@CObj
class GtkTextMark(name: CString, leftGravity: gboolean) extends GObject {

  /**
   * Sets the visibility of the this mark.
   *
   * @param setting visibility of this mark
   */
  @inline def setVisible(setting: gboolean): Unit = extern

  /**
   * Returns true if this mark is visible (i.e. a cursor is displayed for it).
   */
  @inline def getVisible(): gboolean = extern

  /**
   * Returns true if the mark has been removed from its buffer.
   */
  @inline def getDeleted(): gboolean = extern

  /**
   * Returns the mark name, or null.
   */
  @inline def getName(): CString = extern

  /**
   * Returns the buffer in which this mark is located, or null if the mark is deleted.
   */
  @nullable
  @inline def getBuffer(): GtkTextBuffer = extern

  /**
   * Returns whether the mark has left gravity.
   */
  @inline def getLeftGravity(): gboolean = extern
}

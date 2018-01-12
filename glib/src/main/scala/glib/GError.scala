// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import de.surfice.smacrotools.debug

import scalanative.native._
import CObj.implicits._

/**
 * @see [[https://developer.gnome.org/glib/stable/glib-Error-Reporting.html#g-error-new]]
 */
// TODO: add CVararg* (currently crashes due to https://github.com/scala-native/scala-native/issues/1142)
@CObj
class GError(domain: GQuark, code: gint, format: Ptr[gchar])
  extends CObj.CRef[CStruct3[GQuark,gint,CString]] with GAllocated {
  /**
   * Error domain, e.g. `G_FILE_ERROR`
   */
  def domain: GQuark = !__ref.toPtr._1

  /**
   * Error code, e.g. `G_FILE_ERROR_NOENT`
   */
  def code: gint = !__ref.toPtr._2

  /**
   * Human-readable error message
   */
  def message: CString = !__ref.toPtr._3

  def free(): Unit = extern
}
object GError {
  def NULL: GError = new GError(null:CObj.Ref[CStruct3[GQuark,gint,CString]])
}

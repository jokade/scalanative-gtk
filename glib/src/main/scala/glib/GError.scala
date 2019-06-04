// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import de.surfice.smacrotools.debug

import scalanative._
import cobj._
import scala.scalanative.interop.PoolZone
import unsafe._
//import Implicits._

/**
 * @see [[https://developer.gnome.org/glib/stable/glib-Error-Reporting.html#g-error-new]]
 */
// TODO: add CVararg* (currently crashes due to https://github.com/scala-native/scala-native/issues/1142)
@CObj
class GError extends CObject with GAllocated {
  type GErrorStruct = CStruct3[GQuark,gint,CString]

  @inline def __struct: Ptr[GErrorStruct] = __ptr.asInstanceOf[Ptr[GErrorStruct]]

  /**
   * Error domain, e.g. `G_FILE_ERROR`
   */
//  def domain: GQuark = !__struct._1

  /**
   * Error code, e.g. `G_FILE_ERROR_NOENT`
   */
//  def code: gint = !__struct._2

  /**
   * Human-readable error message
   */
//  def message: CString = !__struct._3

  def free(): Unit = extern
}

object GError {

  /**
   * Creates a new GError instance.
   *
   * @param domain
   * @param code
   * @param format
   */
  @name("g_error_new")
  def apply(domain: GQuark, code: gint, format: Ptr[gchar]): GError = extern
  //def NULL: GError = new GError(null)

  def apply[R](f: Out[GError]=>R)(onError: GError=>R): R = PoolZone{ implicit z =>
    val err = Out.alloc[GError]
    val result = f(err)
    if(err.isDefined)
      onError(err.value)
    else
      result
  }
}

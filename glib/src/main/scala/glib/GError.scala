// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import de.surfice.smacrotools.debug
import glib.GError.GErrorStruct
import glib.utils.GZone

import scalanative._
import cobj._
import unsafe._
//import Implicits._

/**
 * @see [[https://developer.gnome.org/glib/stable/glib-Error-Reporting.html#g-error-new]]
 */
// TODO: add CVararg* (currently crashes due to https://github.com/scala-native/scala-native/issues/1142)
@CObj
@debug
class GError extends CObject with GAllocated {

  @inline final def __struct: Ptr[GErrorStruct] = __ptr.asInstanceOf[Ptr[GErrorStruct]]

  /**
   * Error domain, e.g. `G_FILE_ERROR`
   */
  @inline final def domain: GQuark = __struct._1

  /**
   * Error code, e.g. `G_FILE_ERROR_NOENT`
   */
  @inline final def code: gint = __struct._2

  /**
   * Human-readable error message
   */
  @inline final def cmessage: CString = __struct._3

  /**
   * Human-readable error message
   */
  @inline final def message: String = fromCString(cmessage)

  def free(): Unit = extern
}

object GError {

  type GErrorStruct = CStruct3[GQuark,gint,CString]

  /**
   * Creates a new GError instance.
   *
   * @param domain
   * @param code
   * @param format
   */
  @name("g_error_new")
  def apply(domain: GQuark, code: gint, format: CString): GError = extern
  //def NULL: GError = new GError(null)


  def apply[R](f: ResultPtr[GError]=>R)(onError: GError=>R): R = GZone{ implicit z =>
    val err = ResultPtr.alloc[GError]
    val result = f(err)
    if(err.isDefined)
      onError(err.wrappedValue)
    else
      result
  }



  @inline final def free(ptr: Ptr[GErrorStruct]): Unit = ext.g_error_free(ptr)

  @extern
  object ext {
    def g_error_free(ptr: Ptr[GErrorStruct]): Unit = extern
  }
}

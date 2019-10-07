// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scalanative._
import cobj._
import unsafe._

@CObj(prefix = "glib_")
object GLib {
  /* gversion.h */
  @inline def majorVersion: guint = globals.glib_major_version
  @inline def minorVersion: guint = globals.glib_minor_version
  @inline def microVersion: guint = globals.glib_micro_version
  @inline def binaryAge: guint = globals.glib_binary_age
  @inline def interfaceAge: guint = globals.glib_interface_age
  /**
   * Checks the GLib runtime version.
   * @param requiredMajor the required major version
   * @param requiredMinor the required minro version
   * @param requiredMicro the required micro version
   * @return null, if the runtime version matches the requirements, otherwise an error message
   */
  @inline def checkVersion(requiredMajor: guint, requiredMinor: guint, requiredMicro: guint): CString = extern

  @name("g_free")
  @inline final def free(ptr: Ptr[Byte]): Unit = extern

  /**
   * Converts the specified CString into a String and then calls g_free() on the pointer.
   *
   * @param s
   * @return
   */
  def toStringAndFree(s: CString): String = {
    val str = fromCString(s)
    free(s)
    str
  }

  /**
   * Frees a null-terminated array of strings, as well as each string it contains.
   *
   * @param str_array array to be freed
   */
  @name("g_strfreev")
  @inline final def strfreev(str_array: Ptr[CString]): Unit = extern
//  def checkVersion(requiredMajor: guint, requiredMinor: guint, requiredMicro: guint): Try[Unit] = {
//    checkVersion(requiredMajor,requiredMinor,requiredMicro) match {
//      case null => Success(())
//      case err => Failure(new RuntimeException(fromCString(err)))
//    }
//  }

  @extern
  object globals {
    val glib_major_version: guint = extern
    val glib_minor_version: guint = extern
    val glib_micro_version: guint = extern
    val glib_binary_age: guint = extern
    val glib_interface_age: guint = extern
  }
}



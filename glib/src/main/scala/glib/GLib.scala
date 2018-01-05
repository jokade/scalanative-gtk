// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scala.util.{Failure, Success, Try}
import scalanative.native._

object GLib {
  import ext._
  /* gversion.h */
  @inline def majorVersion: guint = glib_major_version
  @inline def minorVerison: guint = glib_minor_version
  @inline def microVersion: guint = glib_micro_version
  @inline def binaryAge: guint = glib_binary_age
  @inline def interfaceAge: guint = glib_interface_age

  /**
   * Checks the GLib runtime version.
   * @param requiredMajor the required major version
   * @param requiredMinor the required minro version
   * @param requiredMicro the required micro version
   * @return Success if the runtime version matches the requirements, otherwise Failure with the GLib error message
   */
  def checkVersion(requiredMajor: guint, requiredMinor: guint, requiredMicro: guint): Try[Unit] = {
    glib_check_version(requiredMajor,requiredMinor,requiredMicro) match {
      case null => Success(())
      case err => Failure(new RuntimeException(fromCString(err)))
    }
  }
}



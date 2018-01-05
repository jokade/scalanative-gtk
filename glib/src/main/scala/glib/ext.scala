// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scala.scalanative.native._

@extern
object ext {
  /* gversion.h */
  val glib_major_version: guint = extern
  val glib_minor_version: guint = extern
  val glib_micro_version: guint = extern
  val glib_binary_age: guint = extern
  val glib_interface_age: guint = extern
  def glib_check_version(required_major: guint, required_minor: guint, required_micro: guint): CString = extern
}

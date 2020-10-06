// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gobject

import glib.gpointer

import scala.scalanative._
import unsafe._
import cobj._

/**
 * A generic wrapper mechanism for arbitrary C structures.
 *
 * @see [[https://developer.gnome.org/gobject/stable/gobject-Boxed-Types.html]]
 */
@CObj
trait GBoxed extends CObject {
  def free(): Unit = extern
  def copy(): gpointer = extern
}

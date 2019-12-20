package glib

import scalanative._
import unsafe._
import cobj._
import utils.GZone

/**
 * Quarks — a 2-way association between a string and a unique integer identifier.
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-Quarks.html]]
 */
@CObj
object GQuark {
  @name("g_quark_from_string")
  def apply(s: CString): GQuark = extern

  def apply(s: String): GQuark = GZone{ implicit z =>
    apply(toCString(s))
  }

  @name("g_quark_to_string")
  def cstring(quark: GQuark): CString = extern

  @inline def string(quark: GQuark): String = fromCString(cstring(quark))
}

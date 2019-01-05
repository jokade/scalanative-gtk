//     Project: scalanative-libsoup
//      Module:
// Description:
package libsoup

import glib.GAllocated

import scalanative.native._
import cobj._

/**
 * HTTP message headers.
 *
 * @see [[https://developer.gnome.org/libsoup/stable/SoupMessageHeaders.html]]
 */
@CObj
class SoupMessageHeaders extends GAllocated {
  override def free(): Unit = extern

  def getEncoding: SoupEncoding = extern
}

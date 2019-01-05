//     Project: scalanative-libsoup
//      Module:
// Description:
package libsoup

import de.surfice.smacrotools.debug
import gobject.GBoxed

import scala.scalanative.native._
import cobj._

/**
 * HTTP message body.
 *
 * @see [[https://developer.gnome.org/libsoup/stable/SoupMessageBody.html]]
 */
@CObj
class SoupMessageBody extends SoupBuffer {
  override def free(): Unit = extern
}

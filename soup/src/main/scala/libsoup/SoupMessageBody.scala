package libsoup

import de.surfice.smacrotools.debug
import gobject.GBoxed

import scalanative._
import unsafe._
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

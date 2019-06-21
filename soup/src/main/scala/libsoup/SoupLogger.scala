package libsoup

import gobject.GObject
import scalanative._
import unsafe._
import cobj._

/**
 * Debug logging support.
 *
 * @see [[https://developer.gnome.org/libsoup/stable/SoupLogger.html]]
 */
@CObj
class SoupLogger extends GObject with SoupSessionFeature {

}

object SoupLogger {
  @name("soup_logger_new")
  def apply(level: SoupLoggerLogLevel, maxBodySize: Int): SoupLogger = extern
}

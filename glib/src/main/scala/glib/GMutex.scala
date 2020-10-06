package glib

import de.surfice.smacrotools.debug

import scalanative._
import unsafe._
import unsigned._
import cobj._
import scala.scalanative.interop.AutoReleasable

@CObj
class GMutex extends CObject with AutoReleasable {
  def lock(): Unit = extern
  def unlock(): Unit = extern

  @name("snhelper_g_mutex_free")
  def free(): Unit = extern

  def withLock[R](f: =>R): R =
    try{
      lock()
      f
    } finally {
      unlock()
    }

}
object GMutex {
  @name("snhelper_g_mutex_new")
  def apply(): GMutex = extern
}
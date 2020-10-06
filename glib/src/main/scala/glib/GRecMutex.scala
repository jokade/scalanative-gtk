package glib

import scala.scalanative._
import scala.scalanative.cobj._
import scala.scalanative.interop.AutoReleasable
import scala.scalanative.unsafe._

@CObj
class GRecMutex extends CObject with AutoReleasable {
  def lock(): Unit = extern
  def unlock(): Unit = extern

  @name("snhelper_g_rec_mutex_free")
  def free(): Unit = extern

  def withLock[R](f: =>R): R =
    try{
      println("locking")
      lock()
      f
    } finally {
      unlock()
      println("unlocked")
    }
}
object GRecMutex {
  @name("snhelper_g_rec_mutex_new")
  def apply(): GMutex = extern
}

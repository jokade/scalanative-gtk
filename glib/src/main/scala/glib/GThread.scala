package glib

import scalanative._
import unsafe._
import unsigned._
import cobj._

@CObj
class GThread extends CObject {

}
object GThread {
  def self(): GThread = extern

  @name("g_thread_self")
  def selfPtr: Ptr[Byte] = extern
}
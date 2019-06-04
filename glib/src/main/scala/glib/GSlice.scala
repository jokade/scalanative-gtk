package glib

import scala.scalanative._
import cobj._
import unsafe._

@CObj
object GSlice {
  def alloc(size: gsize): gpointer = extern
  def alloc0(size: gsize): gpointer = extern
  def free1(size: gsize, block: gpointer): Unit = extern
}

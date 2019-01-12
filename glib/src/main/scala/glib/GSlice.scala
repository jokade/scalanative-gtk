package glib

import scala.scalanative.native._
import cobj._

@CObj
object GSlice {
  def alloc(size: gsize): gpointer = extern
  def alloc0(size: gsize): gpointer = extern
  def free1(size: gsize, block: gpointer): Unit = extern
}

package glib

import scalanative._
import unsafe._
import unsigned._
import cobj._
import scala.scalanative.interop.RefZone
import scala.scalanative.runtime.RawPtr
import scala.scalanative.runtime.Intrinsics

@CObj
object GTimeout {
  /**
   * Sets a function to be called at regular intervals, with the default priority, G_PRIORITY_DEFAULT.
   * The function is called repeatedly until it returns `false`.
   *
   * @param interval the time between calls to the function, in milliseconds
   * @param function function to call
   * @param data data to pass to the function
   * @return The ID of the event source
   */
  def add(interval: guint, function: GSourceFunc, data: gpointer): guint = extern

  @name("g_timeout_add")
  private def addRaw(interval: guint, function: CFuncPtr1[RawPtr,Boolean], data: RawPtr): guint = extern

  /**
   * Calls the provided function at regular intervals, with the default priority.
   * The function is called repeatedly until it returns `false`.
   *
   * @param interval the time between calls to the function
   * @param f function to call
   * @param refZone
   * @return The ID of the event source
   */
  def add(interval: Int, f: Function0[Boolean])(implicit refZone: RefZone): guint =
    addRaw(interval.toUInt,new CFuncPtr1[RawPtr,gboolean] {
      override def apply(arg1: RawPtr): gboolean = Intrinsics.castRawPtrToObject(arg1).asInstanceOf[Function0[Boolean]].apply()
    },refZone.export(f))
}

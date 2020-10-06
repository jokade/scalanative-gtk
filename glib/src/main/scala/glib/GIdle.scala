package glib

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone
import scala.scalanative.runtime.RawPtr

@CObj
object GIdle {
  /**
   * Adds a function to be called whenever there are no higher priority events pending to the default main loop.
   * The function is given the default idle priority, G_PRIORITY_DEFAULT_IDLE.
   * If the function returns FALSE it is automatically removed from the list of event sources and will not be called again.
   *
   * @param f function to call
   * @param data data to pass to function
   * @return
   */
  def add(f: CFuncPtr1[RawPtr,Boolean], data: RawPtr): guint = extern

  def add(f: ()=>Boolean)(implicit refZone: RefZone): guint = {
    add(new CFuncPtr1[RawPtr,Boolean] {
      override def apply(arg1: RawPtr): gboolean = {
        val func = interop.rawPtrToObject(arg1).asInstanceOf[Function0[Boolean]]
        val res = func()
        if(!res)
          refZone.release(f)
        res
      }
    },refZone.`export`(f))
  }
}

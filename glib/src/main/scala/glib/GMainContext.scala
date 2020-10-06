package glib

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone
import scala.scalanative.runtime.RawPtr

@CObj
class GMainContext extends CObject {

}
object GMainContext {

  def default(): GMainContext = extern

  /**
   * Invokes a function in such a way that context is owned during the invocation of function.
   * If context is NULL then the global default main context — as returned by [[default()]] — is used.
   *
   * @param context a GMainContext, or null
   * @param f function to call
   * @param data data to pass to function
   */
  def invoke(context: GMainContext, f: CFuncPtr1[RawPtr,Boolean], data: RawPtr): Unit = extern

  def invoke(f: ()=>Unit)(implicit refZone: RefZone): Unit = {
    invoke(
      null,
      new CFuncPtr1[RawPtr,Boolean] {
        override def apply(arg1: RawPtr): gboolean = {
          val func = interop.rawPtrToObject(arg1).asInstanceOf[Function0[Unit]]
          func()
//          refZone.release(f)
          false
        }
      },
      refZone.`export`(f))
  }
}

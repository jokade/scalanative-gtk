package gio

import glib.gulong
import glib.utils.GZone
import gobject.GObject

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone

/**
 * Core application class.
 *
 * @see [[https://developer.gnome.org/gio/unstable/GApplication.html]]
 */
@CObj
class GApplication extends GObject {

  def run(argc: Int, argv: Ptr[CString]): Int = extern
  def run(args: Array[String]): Int = GZone{ implicit z =>
    val argc = args.size
    val argv = gio.argsToArgv(args)
    run(argc,argv)
  }

  /**
   * The unqiue identifier for the application.
   */
  def applicationId: String = getStringProp(c"application-id")
  def applicationId_=(id: String): Unit = setStringProp(c"application-id",id)

  /**
   * Registers the handler for the 'activate' signal.
   */
  def onActivate[T>:GApplication](handler: Function1[T,Unit])(implicit refZone: RefZone):gulong =
    connect1(c"activate", (arg1: Ptr[Byte]) => handler(new GApplication(arg1)))


  /**
   * Registers the handler for the 'shutdown' signal.
   */
  def onShutdown(handler: Function0[Unit])(implicit refZone: RefZone): gulong = connect0(c"shutdown",handler)

//  def onOpen(handler: Function0[Unit])(implicit refZone: RefZone): gulong = connect0(c"open",handler)
}

object GApplication {
  @name("g_application_new")
  def apply(applicationId: CString, flags: GApplicationFlags): GApplication = extern
}

package gtk

import gio.{GApplication, GApplicationFlags}
import glib.gulong
import glib.utils.GZone

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone

/**
 * Application class.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkApplication.html]]
 */
@CObj
class GtkApplication extends GApplication {

  def addWindow(winndow: GtkWindow): Unit = extern

  /**
   * Registers the handler for the 'activate' signal.
   */
  override def onActivate[T >: GtkApplication](handler: T => Unit)(implicit refZone: RefZone): gulong =
    connect1(c"activate", (arg1: Ptr[Byte]) => handler(new GtkApplication(arg1)))
}

object GtkApplication {
  @name("gtk_application_new")
  def apply(applicationId: CString, flags: GApplicationFlags): GtkApplication = extern

  def apply(applicationId: String, flags: GApplicationFlags = GApplicationFlags.FLAGS_NONE): GtkApplication = GZone{ implicit z =>
    apply(toCString(applicationId),flags)
  }
}
package gtkosx

import gobject.{GObject, GType}
import gtk.GtkMenuShell

import scalanative.native._
import cobj._

@CObj(prefix = "gtkosx_application_")
class GtkOSXApplication extends GObject {

  /**
   * Inform Cocoa that application initialization is complete.
   */
  def ready(): Unit = extern

  def getBundlePath(): CString = extern

  lazy val bundlePath: String = fromCString(getBundlePath())

  def getResourcePath(): CString = extern

  lazy val resourcePath: String = fromCString(getResourcePath())

  def getBundleInfo(key: CString): CString = extern

  def getBundleInfo(key: String): String = PoolZone{ implicit z =>
    fromCString(getBundleInfo(toCString(key)))
  }

  def setMenuBar(menubar: GtkMenuShell): Unit = extern

}

object GtkOSXApplication {
  /**
   * Returns the singleton application object
   */
  @name("gtkosx_application_get")
  def apply(): GtkOSXApplication = extern //new GtkOSXApplication( GObject.ext.g_object_new(getType(),null) )

}

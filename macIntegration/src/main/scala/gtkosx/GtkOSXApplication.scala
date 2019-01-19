package gtkosx

import gobject.{GObject, GType}

import scalanative.native._
import cobj._

@CObj(prefix = "gtkosx_application_")
class GtkOSXApplication {

  /**
   * Inform Cocoa that application initialization is complete.
   */
  def ready(): Unit = extern

}

object GtkOSXApplication {
  /**
   * Returns the singleton application object
   */
  @name("gtkosx_application_get")
  def apply(): GtkOSXApplication = extern //new GtkOSXApplication( GObject.ext.g_object_new(getType(),null) )

}

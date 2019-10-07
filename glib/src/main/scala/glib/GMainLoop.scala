package glib

import scalanative._
import unsafe._
import cobj._

/**
 * The main event loop -- manages all available sources of events.
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-The-Main-Event-Loop.html]]
 */
@CObj
class GMainLoop {
  def ref(): GMainLoop = extern
  def unref(): Unit = extern

  def run(): Unit = extern
  def quit(): Unit = extern
}
object GMainLoop {
  @name("g_main_loop_new")
  def apply(context: GMainContext, isRunning: Boolean): GMainLoop = extern

  def apply(): GMainLoop = apply(null,false)
}

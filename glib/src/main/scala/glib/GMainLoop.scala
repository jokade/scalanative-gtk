package glib

import scalanative._
import unsafe._
import cobj._
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext
import scala.scalanative.interop.RefZone

/**
 * The main event loop -- manages all available sources of events.
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-The-Main-Event-Loop.html]]
 */
@CObj
class GMainLoop extends CObject {
  def ref(): GMainLoop = extern
  def unref(): Unit = extern

  def run(): Unit = extern
  def quit(): Unit = extern
}
object GMainLoop {
  @name("g_main_loop_new")
  def apply(context: GMainContext, isRunning: Boolean): GMainLoop = extern

  def apply(): GMainLoop = apply(null,false)

  class GMainExecutionContext extends ExecutionContext {
    private val _mutex = GRecMutex()
    private val _queue: ListBuffer[Runnable] = new ListBuffer

    override def execute(runnable: Runnable): Unit = try{
      _mutex.lock()
      _queue += runnable
    } finally {
      _mutex.unlock()
    }

    override def reportFailure(cause: Throwable): Unit = {
      println(cause)
    }

    def runQueue(): Boolean = try{
      _mutex.lock()
      if(_queue.nonEmpty) {
        println("running queue on: "+GThread.selfPtr)
        println("size: "+_queue.size)
        val runnable = _queue.remove(0)
        println("  1")
        try{
          runnable.run()
          println("  2")
        } catch {
          case t: Throwable =>
            reportFailure(t)
        }
      }
      true
    } finally {
      _mutex.unlock()
    }
  }

  lazy val executionContext: ExecutionContext = {
    val ec = new GMainExecutionContext
    GIdle.add(ec.runQueue)(RefZone.Global)
    ec
  }
}

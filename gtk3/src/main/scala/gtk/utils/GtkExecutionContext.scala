// Copyright (c) 2019. Distributed under the MIT License (see included LICENSE file).
package gtk.utils

import gtk.Gtk

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext

/**
 * An ExecutionContext that is integrated with the Gtk main event loop
 */
class GtkExecutionContext extends ExecutionContext {
  private var _active = true
  private val _queue: ListBuffer[Runnable] = new ListBuffer

  override def execute(runnable: Runnable): Unit = {
    _queue += runnable
  }

  def queue(f: ()=>Any): Unit = execute(new Task(f))

  override def reportFailure(cause: Throwable): Unit = {
    println(cause)
  }

  def run(): Unit = {
    while(_active) {
//      Gtk.mainIterationDo(false)
      Gtk.mainIteration()
      if(_queue.nonEmpty) {
        val runnable = _queue.remove(0)
        try{
          runnable.run()
        } catch {
          case t: Throwable => reportFailure(t)
        }
      }
    }
  }

  def quit(): Unit = _active = false

  private class Task(f: ()=>Any) extends Runnable {
    override def run(): Unit = f()
  }
}

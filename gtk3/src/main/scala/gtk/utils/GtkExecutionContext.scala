// Copyright (c) 2019. Distributed under the MIT License (see included LICENSE file).
package gtk.utils

import gtk.Gtk

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext
import scala.scalanative.native.{CArray, CString}

/**
 * An ExecutionContext that is integrated with the Gtk main event loop
 */
class GtkExecutionContext extends ExecutionContext {
  private var active = true
  private val queue: ListBuffer[Runnable] = new ListBuffer

  override def execute(runnable: Runnable): Unit = queue += runnable

  override def reportFailure(cause: Throwable): Unit = {}

  def run(): Unit = {
    while(active) {
//      Gtk.mainIterationDo(false)
      Gtk.mainIteration()
      if(queue.nonEmpty) {
        val runnable = queue.remove(0)
        try{
          runnable.run()
        } catch {
          case t: Throwable => reportFailure(t)
        }
      }
    }
  }

  def quit(): Unit = active = false
}

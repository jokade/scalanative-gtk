// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import scala.scalanative.native._

object Gtk {
  import __ext._

  def init(args: Array[String]): Array[String] = {
    val argc = stackalloc[CInt]
    !argc = 0
    gtk_init(argc,null)
    args
  }

  def quit(): Unit = __ext.gtk_main_quit()

  @inline def main(): Unit = gtk_main()

  @extern
  private object __ext {
    def gtk_main(): Unit = extern
    def gtk_main_quit(): Unit = extern
    def gtk_init(argc: Ptr[CInt], argv: Ptr[Ptr[CString]]): Unit = extern
  }
}


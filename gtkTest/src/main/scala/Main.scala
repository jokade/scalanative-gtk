// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import glib.{GError, GKeyFile, gpointer, gulong}
import gobject.{GCallback, GClosureNotify, GConnectFlags, GSignalReceiver}
import gtk.{Gtk, GtkWindow}

import scalanative.native._

object Main {
  def main(args: Array[String]): Unit = {
    Gtk.init(args)

    val win = new GtkWindow
    win.setTitle(c"Hello!")
    win.showAll()

    win.connect(c"destroy",CFunctionPtr.fromFunction0(destroy),null)

    Gtk.main()
//    val keyfile = new GKeyFile
//    implicit val err = GError.NULL
//    keyfile.getValue(c"group",c"key")(err)
//    println( err.__ref == null)

  }

  def destroy(): Unit = {
    Gtk.mainQuit()
  }
}

@extern
object ext {
  def gtk_window_new(t: Int): Ptr[Byte] = extern
}

class Foo(ref: Ptr[Byte]) {
  def this(i: Int) = this(ext.gtk_window_new(i))
}

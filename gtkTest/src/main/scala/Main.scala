// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import glib.{GError, GKeyFile, gpointer, gulong}
import gobject.{GCallback, GClosureNotify, GConnectFlags, GSignalReceiver}
import gtk.{Gtk, GtkWindow}

import scalanative.native._

object Main {
  def main(args: Array[String]): Unit = {

    val i = 1
    scalanative.native.
    stdio.printf(c"%x\n",i)
//    val keyfile = new GKeyFile
//    implicit val err = GError.NULL
//    keyfile.getValue(c"group",c"key")(err)
//    println( err.__ref == null)

  }

}

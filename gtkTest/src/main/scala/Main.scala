// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).

import gtk.{Gtk, GtkWindow}

object Main {
  def main(args: Array[String]): Unit = {

    Gtk.init(args)

    val window = GtkWindow(GtkWindow.GTK_WINDOW_TOPLEVEL)
    window.showAll()

    Gtk.main()
  }
}

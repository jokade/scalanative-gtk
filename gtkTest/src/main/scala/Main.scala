import de.surfice.smacrotools.debug
import glib.gulong
import gobject.{GClosureNotify, GConnectFlags, GSignalReceiver}

import scalanative._
import unsafe._
import unsigned._
import cobj._
import gtk.{SignalHandler, _}

object Main {
  import scala.scalanative.interop.RefZone.Implicits.None

  var entry: GtkEntry = _
  var greeting: GtkLabel = _

  def exit(): Unit = {
    Gtk.mainQuit()
  }

  def greet(): Unit = {
    greeting.setMarkup(s"<span size='large'>Hello ${entry.text}!</span>")
  }

  def main(args: Array[String]): Unit = {
    import scala.scalanative.interop.RefZone.Implicits.None

    Gtk.init(args)

//    val builder = GtkBuilder.fromFile(c"gtkbuilder1.ui")
//    val win = new GtkWindow( builder.getObjectPtr(c"window1") )
//    win.showAll()

    val win = GtkWindow()
    win.title = "Greetings"
    win.setBorderWidth(10.toUInt)

    win.onDestroy(exit)

    val grid = GtkGrid()
    grid.setColumnSpacing(5.toUInt)
    grid.setRowSpacing(10.toUInt)

    val label = GtkLabel(c"Name:")
    grid.attach(label,0,0,1,1)

    entry = GtkEntry()
    grid.attach(entry,1,0,1,1)

    val button = GtkButton.withLabel(c"Greet!")
    grid.attach(button,0,1,2,1)

    greeting = GtkLabel(c"")
    greeting.setSizeRequest(-1,30)
    grid.attach(greeting,0,2,2,1)

    button.onClicked(greet)

    win.add(grid)
    win.showAll()


    Gtk.main()

  }

}

import de.surfice.smacrotools.debug
import glib.gulong
import gobject.{GClosureNotify, GConnectFlags, GSignalReceiver}

import scalanative._
import unsafe._
import unsigned._
import cobj._
import gtk.{SignalHandler, _}

object Main {

  var entry: GtkEntry = _
  var greeting: GtkLabel = _

  def destroy(): Unit = {
    Gtk.mainQuit()
  }

  def greet(): Unit = {
    greeting.setMarkup(s"<span size='large'>Hello ${entry.text}!</span>")
  }

  val gcb = new CFuncPtr0[Unit] {
    override def apply(): Unit = greet()
  }

  val dcb = new CFuncPtr0[Unit] {
    override def apply(): Unit = destroy()
  }

  def main(args: Array[String]): Unit = {

    Gtk.init(args)

//    val builder = GtkBuilder.fromFile(c"gtkbuilder1.ui")
//    val win = new GtkWindow( builder.getObjectPtr(c"window1") )
//    win.showAll()

    val win = GtkWindow()
    win.title = "Greetings"
    win.setBorderWidth(10.toUInt)

    win.connectData(c"destroy",dcb,null,null,0.toUInt)
//    connect(win,c"destroy",destroy)
//    win.connect(c"destroy",destroy)

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

    val cb = new CFuncPtr2[Ptr[Byte],Ptr[Byte],Unit] {
      override def apply(arg1: Ptr[CSignedChar], arg2: Ptr[CSignedChar]): Unit = {
        println("FOO")
      }
    }
    ext.connect(button.__ptr,c"clicked",cb,null,null,0.toUInt)
    //button.connect(c"clicked",gcb,null)

    win.add(grid)
    win.showAll()


    Gtk.main()

  }

  def connect(c: GSignalReceiver, signal: CString, cb: ()=>Unit): gulong = ext.connect(c.__ptr,signal,new CFuncPtr0[Unit] {
    override def apply(): Unit = cb()
  },null,null,0.toUInt)
}

@extern
object ext {
  @name("g_signal_connect_data")
  def connect(self: Ptr[Byte], signal: CString, cb: CFuncPtr0[Unit],data: Ptr[Byte], notify: GClosureNotify, flags: GConnectFlags): gulong = extern
  @name("g_signal_connect_data")
  def connect(self: Ptr[Byte], signal: CString, cb: CFuncPtr2[Ptr[Byte],Ptr[Byte],Unit],data: Ptr[Byte], notify: GClosureNotify, flags: GConnectFlags): gulong = extern
}


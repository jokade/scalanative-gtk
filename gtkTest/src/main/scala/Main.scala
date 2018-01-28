import scalanative.native._
import gtk._

object Main {

  var entry: GtkEntry = _
  var greeting: GtkLabel = _

  def main(args: Array[String]): Unit = {

    Gtk.init(args)

//    val builder = GtkBuilder.fromFile(c"gtkbuilder1.ui")
//    val win = new GtkWindow( builder.getObjectPtr(c"window1") )
//    win.showAll()

    val win = new GtkWindow
    win.setTitle(c"Greetings")
    win.setBorderWidth(10.toUInt)
    win.connect(c"destroy",CFunctionPtr.fromFunction0(destroy),null)

    val grid = new GtkGrid
    grid.setColumnSpacing(5.toUInt)
    grid.setRowSpacing(10.toUInt)

    val label = new GtkLabel(c"Name:")
    grid.attach(label,0,0,1,1)

    entry = new GtkEntry
    grid.attach(entry,1,0,1,1)

    val button = GtkButton.withLabel(c"Greet!")
    grid.attach(button,0,1,2,1)

    greeting = new GtkLabel(c"")
    greeting.setSizeRequest(-1,30)
    grid.attach(greeting,0,2,2,1)

    button.connect(c"clicked",CFunctionPtr.fromFunction0(greet),null)

    win.add(grid)
    win.showAll()


    Gtk.main()

  }

  def destroy(): Unit = {
    Gtk.mainQuit()
  }

  def greet(): Unit = {
    greeting.setMarkup(s"<span size='large'>Hello ${entry.text}!</span>")
  }
}


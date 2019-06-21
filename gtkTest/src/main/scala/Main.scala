import gtk._
import scalanative.unsigned._

object Main {
  import scala.scalanative.interop.RefZone.Implicits.None

  var entry: GtkEntry = _
  var greeting: GtkLabel = _


  def main(args: Array[String]): Unit = {

    Gtk.init(args)

    val win = GtkWindow()
    win.title = "Greetings"
    win.setBorderWidth(10.toUInt)

    win.onDestroy(exit)

    val grid = GtkGrid()
    grid.setColumnSpacing(5.toUInt)
    grid.setRowSpacing(10.toUInt)

    val label = GtkLabel("Name:")
    grid.attach(label,0,0,1,1)

    entry = GtkEntry()
    grid.attach(entry,1,0,1,1)

    val button = GtkButton.withLabel("Greet!")
    grid.attach(button,0,1,2,1)

    greeting = GtkLabel("")
    greeting.setSizeRequest(-1,30)
    grid.attach(greeting,0,2,2,1)

    button.onClicked(greet)

    win.add(grid)
    win.showAll()


    Gtk.main()

  }

  def exit(): Unit = {
    Gtk.mainQuit()
  }

  def greet(): Unit = {
    greeting.setMarkup(s"<span size='large'>Hello ${entry.text}!</span>")
  }

}

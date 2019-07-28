package gtkExample

import gio.GApplicationFlags
import gtk._

object GtkExample {

  def activate(app: GtkApplication): Unit = {
    ExampleAppWindow(app)
  }

  def shutdown(): Unit = {
  }

  def main(args: Array[String]): Unit = {
    import scala.scalanative.interop.RefZone.Implicits.None

    val app = GtkApplication("test.Foo",GApplicationFlags.HANDLES_OPEN)
    app.onActivate(activate)
    app.onShutdown(shutdown)
    app.onOpen(() => println("OPENING!"))
    app.run(args)
  }



}

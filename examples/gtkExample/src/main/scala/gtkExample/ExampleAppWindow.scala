package gtkExample

import gtk.{GtkApplication, GtkApplicationWindow, GtkBuilder, GtkStack}

class ExampleAppWindow private(_win: GtkApplicationWindow, _stack: GtkStack) {
  _win.showAll()
}

object ExampleAppWindow {

  def apply(app: GtkApplication): ExampleAppWindow = {
    val builder = GtkBuilder.fromString(xml)
    val win = builder.getObject[GtkApplicationWindow]("example_app_win")
    val stack = builder.getObject[GtkStack]("stack")
    val appWin = new ExampleAppWindow(win,stack)
    app.addWindow(win)
    builder.free()
    appWin
  }

  private val xml =
    """
      |<?xml version="1.0" encoding="UTF-8"?>
      |<interface>
      |  <!-- interface-requires gtk+ 3.8 -->
      |  <object class="GtkApplicationWindow" id="example_app_win">
      |    <property name="title" translatable="yes">Example Application</property>
      |    <property name="default-width">600</property>
      |    <property name="default-height">400</property>
      |    <child>
      |      <object class="GtkBox" id="content_box">
      |        <property name="visible">True</property>
      |        <property name="orientation">vertical</property>
      |        <child>
      |          <object class="GtkHeaderBar" id="header">
      |            <property name="visible">True</property>
      |            <child type="title">
      |              <object class="GtkStackSwitcher" id="tabs">
      |                <property name="visible">True</property>
      |                <property name="stack">stack</property>
      |              </object>
      |            </child>
      |          </object>
      |        </child>
      |        <child>
      |          <object class="GtkStack" id="stack">
      |            <property name="visible">True</property>
      |          </object>
      |        </child>
      |      </object>
      |    </child>
      |  </object>
      |</interface>
    """.stripMargin

}

package gtk.ui

import gtk.{GtkButton, GtkIconSize}

import scala.scalanative.interop.RefZone
import scala.scalanative.unsafe._

trait UIButton extends GtkButton with UIContainer {
  /**
   * Default handler for signal 'clicked'
   */
  def onClicked(): Unit = {}

  /**
   * Connects all default signal handlers for this widget.
   */
  override def connectDefaultSignalHandlers()(implicit refZone: RefZone): this.type = {
    super.connectDefaultSignalHandlers()
    connect0(c"clicked",onClicked)
    this
  }
}

object UIButton {

  def apply(button: GtkButton)(_onClicked: =>Unit)(implicit refZone: RefZone): UIButton = new UIButtonComponent(button.__ptr) {
    override def onClicked(): Unit = _onClicked
  }.connectDefaultSignalHandlers()

  def withLabel(label: String)(_onClicked: =>Unit)(implicit refZone: RefZone): UIButton = apply(GtkButton.withLabel(label))(_onClicked)

  def fromIconName(name: String, iconSize: GtkIconSize = GtkIconSize.BUTTON, label: String = null, alwaysShowImage: Boolean = true)(_onClicked: =>Unit)(implicit refZone: RefZone): UIButton =
    apply(GtkButton.fromIconName(name,iconSize,label,alwaysShowImage))(_onClicked)

}

abstract class UIButtonComponent(__ptr: GtkPtr) extends GtkButton(__ptr) with UIButton {

}

object UIButtonComponent {
  def create(): GtkPtr = GtkButton.__ext.gtk_button_apply()
}

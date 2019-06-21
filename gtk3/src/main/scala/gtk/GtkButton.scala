package gtk

import de.surfice.smacrotools.debug
import glib.{gboolean, gulong}
import glib.utils.GZone

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.{PoolZone, RefZone}

@CObj
//@debug
class GtkButton extends GtkBin {

  def label: String = getStringProp(c"label")
  def label_=(label: String): Unit = setStringProp(c"label",label)

  def alwaysShowImage: gboolean = getBooleanProp(c"always-show-image")
  def alwaysShowImage_=(setting: gboolean): Unit = setBooleanProp(c"always-show-image",setting)

  /**
   * Sets the relief of the edges of this button.
   *
   * @param relief button edge style (default: [[GtkReliefStyle.NORMAL]]
   */
  @inline def setRelief(relief: GtkReliefStyle): Unit = extern

  /**
   * Returns the current relief style of this button.
   */
  @inline def getRelief(): GtkReliefStyle = extern

  def onClicked(handler: Function0[Unit])(implicit refZone: RefZone): gulong = connect(c"clicked",handler)
}

object GtkButton {

  @name("gtk_button_new")
  def apply(): GtkButton = extern

  /**
   * Creates a GtkButton widget with a GtkLabel child containing the given text.
   *
   * @param label the text for the [[GtkLabel]] of the button
   * @return a new GtkButton
   */
  @name("gtk_button_new_with_label")
  @inline def withLabel(label: CString): GtkButton = extern
  def withLabel(label: String): GtkButton = PoolZone{ implicit z => withLabel(toCString(label)) }

  /**
   * Creates a new GtkButton containing a label.
   * If characters in label are preceded by an underscore, they are underlined.
   * If you need a literal underscore character in a label, use “__” (two underscores).
   * The first underlined character represents a keyboard accelerator called a mnemonic.
   * Pressing Alt and that key activates the button.
   *
   * @param label The text of the button, with an underscore in front of the mnemonic character
   * @return a new GtkButton
   */
  @name("gtk_button_new_with_mnemonic")
  @inline def withMnemonic(label: CString): GtkButton = extern

  /**
   * Creates a new button containing an icon from the current icon theme.
   *
   * @param name icon name, or null
   * @param size an icon size
   */
  @name("gtk_button_new_from_icon_name")
  def fromIconName(name: CString, size: GtkIconSize): GtkButton = extern

  /**
   * Creates a new button containing an icon from the current theme, and optionally a label.
   *
   * @param iconName icon name, or null
   * @param size icon size
   * @param label button label or null
   * @param alwaysShowImage value of the property "always-show-image"
   */
  def fromIconName(iconName: String, size: GtkIconSize, label: String = null, alwaysShowImage: Boolean = true): GtkButton = PoolZone{ implicit z =>
    val button = fromIconName(toCString(iconName),size)
    if(label!=null)
      button.label = label
    button.alwaysShowImage = alwaysShowImage
    button
  }
}


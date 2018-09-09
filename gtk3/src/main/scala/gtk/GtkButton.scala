// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import de.surfice.smacrotools.debug

import scalanative.native._
import cobj._

@CObj
//@debug
class GtkButton extends GtkBin {

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
}

object GtkButton {
  /**
   * Creates a GtkButton widget with a GtkLabel child containing the given text.
   *
   * @param label the text for the [[GtkLabel]] of the button
   * @return a new GtkButton
   */
  @name("gtk_button_new_with_label")
  @inline def withLabel(label: CString): GtkButton = extern

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
}


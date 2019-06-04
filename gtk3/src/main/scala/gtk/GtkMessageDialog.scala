package gtk

import glib.utils.GZone

import scalanative._
import unsafe._
import cobj._

/**
 * A convenient message dialog.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkMessageDialog.html#GtkMessageType]]
 */
@CObj
class GtkMessageDialog extends GtkDialog {

  /**
   * Sets the text of the message dialog.
   */
  def setMarkup(str: CString): Unit = extern

  /**
   * Returns the message area of this dialog.
   */
  def getMessageArea(): GtkWidget = extern
}

object GtkMessageDialog {
  @name("gtk_message_dialog_new")
  def apply(parent: GtkWindow, flags: GtkDialogFlags, messageType: GtkMessageType, buttons: GtkButtonsType, message: CString): GtkMessageDialog = extern

  def apply(parent: GtkWindow, flags: GtkDialogFlags, messageType: GtkMessageType, buttons: GtkButtonsType, message: String): GtkMessageDialog = GZone{ implicit z =>
    apply(parent,flags,messageType,buttons,toCString(message))
  }

  /**
   * Creates a blocking error dialog.
   * @param parent parent window for the dialog
   * @param message error message
   */
  def error(parent: GtkWindow, message: String): GtkMessageDialog = apply(parent,GtkDialogFlags.MODAL,GtkMessageType.ERROR,GtkButtonsType.CLOSE,message)

  /**
   * Displays a blocking error dialog.
   *
   * @param parent parent widnow for the dialog
   * @param message error message
   */
  def showError(parent: GtkWindow, message: String): Unit = {
    val dialog = error(parent,message)
    dialog.run()
    dialog.destroy()
  }
}

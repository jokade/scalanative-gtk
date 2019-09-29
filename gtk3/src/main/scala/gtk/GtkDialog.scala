package gtk

import glib.{gboolean, gint}

import scalanative._
import unsafe._
import cobj._

/**
 * Create popup windows
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkDialog.html]]
 */
@CObj
class GtkDialog extends GtkWindow {

  /**
   * Blocks in a recursive main loop until the dialog either emits the "response" signal, or is destroyed.
   * @return response ID
   */
  def run(): gint = extern

  /**
   * Emits the "response" signal with the given response ID.
   *
   * @param responseId
   */
  def response(responseId: gint): Unit = extern

  /**
   * Adds a button with the given text and sets things up so that clicking the button will emit the "response"
   * signal with the given responseId.
   *
   * @param buttonText text of the button
   * @param responseId response ID for the button
   * @return the added button
   */
  def addButton(buttonText: CString, responseId: gint): GtkButton = extern

  /**
   * Adds an activatable widget to the action are of this dialog, connecting a signal handler that
   * will emit the "response" signal on the dialog when the widget is activated.
   *
   * @param child an activatable widget
   * @param responseId response ID for child
   */
  def addActionWidget(child: GtkWidget, responseId: gint): Unit = extern

  /**
   * Sets the last widget in the dialog's action area with the given responseId as the default widget for the dialog.
   *
   * @param responseId a response ID
   */
  def setDefaultResponse(responseId: gint): Unit = extern

  /**
   * Calls [[GtkWidget.setSensitive()]] for each widget in the dialog'S action area with the given responseID.
   *
   * @param responseId a response ID
   * @param setting true for sensitive
   */
  def setResponseSensitive(responseId: gint, setting: gboolean): Unit = extern

  /**
   * Gets the response id of a widget in the action area of a dialog.
   *
   * @param widget a widget in the action area of this dialog
   */
  def getResponseForWidget(widget: GtkWidget): gint = extern

  /**
   * Gets the widget button that uses the given response ID in the action are of the dialog, or null.
   *
   * @param responseId
   */
  @nullable
  def getWidgetForResponse(responseId: gint): GtkWidget = extern

  /**
   * Returns the content area of this dialog.
   */
  def getContentArea(): GtkWidget = extern

  /**
   * Returns the header bar of this dialog.
   */
  def getHeaderBar(): GtkWidget = extern

}

object GtkDialog {
  @name("gtk_dialog_new")
  def apply(): GtkDialog = extern
}

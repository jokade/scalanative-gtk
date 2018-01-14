// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gobject

import de.surfice.smacrotools.debug
import glib.{gpointer, gulong}

import scala.scalanative.native._

@CObj("g_signal_")
trait GSignalReceiver {

  /**
   * Connects a GCallback function to a signal received by this object.
   *
   * @note This method doesn't automatically unwrap GtkWidgets passed as data!
   *       For example, if you want to pass a GtkButton as value for `data`,
   *       you must use `button.__ref`.
   *
   * @param detailedSignal a string of the form "signal-name::detail"
   * @param cHandler       the GCallback to connect
   * @param data            data to pass to c_handler calls
   * @return the handler ID (always greater than 0 for successful connections)
   *
   * @see [[https://developer.gnome.org/gobject/stable/gobject-Signals.html#g-signal-connect]]
   */
  @inline def connect[T](detailedSignal: CString, cHandler: GCallback, data: T): gulong =
    connectData(detailedSignal,cHandler,data,null,0.toUInt)

  /**
   * Connects a GCallback function to a signal for a particular object.
   *
   * @note This method doesn't automatically unwrap GtkWidgets passed as data!
   *       For example, if you want to pass a GtkButton as value for `data`,
   *       you must use `button.__ref`.
   *
   * This object and the data will be swapped when calling the handler.
   * This is useful when calling pre-existing functions to operate purely on the data, rather than on this instance:
   * swapping the parameters avoids the need to write a wrapper function.
   *
   * @param detailedSignal a string of the form "signal-name::detail"
   * @param cHandler the GCallback to connect
   * @param data
   * @return the handler ID (always greater than 0 for successful connections)
   */
  def connectSwapped[T](detailedSignal: CString, cHandler: GCallback, data: T): gulong =
    connectData(detailedSignal,cHandler,data,null,GConnectFlags.SWAPPED)

  /**
   * Connects a GCallback function to a signal received by this object.
   *
   * @param detailedSignal a string of the form "signal-name::detail"
   * @param cHandler       the GCallback to connect
   * @param data            data to pass to c_handler calls
   * @param destroyData    a GClosureNotify for `data`
   * @param connectFlags   a combination of [[GConnectFlags]]
   * @return the handler ID (always greater than 0 for successful connections)
   *
   * @see [[https://developer.gnome.org/gobject/stable/gobject-Signals.html#g-signal-connect-data]]
   */
  def connectData[T](detailedSignal: CString, cHandler: CFunctionPtr, data: T, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern

}

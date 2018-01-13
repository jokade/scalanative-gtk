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
   * @param detailed_signal a string of the form "signal-name::detail"
   * @param c_handler       the GCallback to connect
   * @param data            data to pass to c_handler calls
   * @return the handler ID (always greater than 0 for successful connections)
   *
   * @see [[https://developer.gnome.org/gobject/stable/gobject-Signals.html#g-signal-connect]]
   */
  @inline def connect[T](detailed_signal: CString, c_handler: GCallback, data: T): gulong =
    connectData(detailed_signal,c_handler,data,null,0.toUInt)

  /**
   * Connects a GCallback function to a signal received by this object.
   *
   * @param detailed_signal a string of the form "signal-name::detail"
   * @param c_handler       the GCallback to connect
   * @param data            data to pass to c_handler calls
   * @param destroy_data    a GClosureNotify for `data`
   * @param connect_flags   a combination of [[GConnectFlags]]
   * @return the handler ID (always greater than 0 for successful connections)
   *
   * @see [[https://developer.gnome.org/gobject/stable/gobject-Signals.html#g-signal-connect-data]]
   */
  def connectData[T](detailed_signal: CString, c_handler: CFunctionPtr, data: T, destroy_data: GClosureNotify, connect_flags: GConnectFlags): gulong = extern

}

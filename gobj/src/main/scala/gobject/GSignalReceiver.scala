package gobject

import de.surfice.smacrotools.debug
import glib.{gpointer, gulong}

import scalanative._
import unsafe._
import unsigned._
import cobj._

@CObj("g_signal_")
@debug
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
//  @inline def connect[T](detailedSignal: CString, cHandler: GCallback, data: T)(implicit wrapper: CObjectWrapper[T]): gulong =
//    connectData(detailedSignal,cHandler,data,null,0.toUInt)


//  def connect[R](signalName: CString, handler: () => R): gulong = connectData(signalName,new CFuncPtr0[R] {
//    override def apply(): R = {
//      handler()
//    }
//  },null,null,0.toUInt)

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
//  def connectSwapped[T](detailedSignal: CString, cHandler: GCallback, data: T)(implicit wrapper: CObjectWrapper[T]): gulong =
//    connectData(detailedSignal,cHandler,data,null,GConnectFlags.SWAPPED)

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
//  def connectData[T](detailedSignal: CString, cHandler: CFuncPtr, data: T, destroyData: GClosureNotify, connectFlags: GConnectFlags)(implicit wrapper: CObjectWrapper[T]): gulong = extern
  def connectData(detailedSignal: CString, cHandler: CFuncPtr, data: Ptr[Byte], destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern

}

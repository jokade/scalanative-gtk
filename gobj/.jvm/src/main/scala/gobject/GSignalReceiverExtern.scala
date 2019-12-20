package gobject

import glib.gulong

import scala.scalanative.runtime.RawPtr
import scala.scalanative.unsafe._

@external
object GSignalReceiverExtern {
  def g_signal_connect_data(obj: Ptr[Byte], signal: CString, handler: CFuncPtr, data: RawPtr, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern
}

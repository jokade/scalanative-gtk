package gobject

import glib.gulong

import scala.scalanative.runtime.RawPtr
import scala.scalanative.unsafe._

@extern
object GSignalReceiverExtern {
  def g_signal_connect_data(obj: Ptr[Byte], signal: CString, handler: CFuncPtr1[RawPtr,Unit], data: RawPtr, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern
  def g_signal_connect_data(obj: Ptr[Byte], signal: CString, handler: CFuncPtr2[RawPtr,RawPtr,Unit], data: RawPtr, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern
  def g_signal_connect_data(obj: Ptr[Byte], signal: CString, handler: CFuncPtr3[RawPtr,RawPtr,RawPtr,Unit], data: RawPtr, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern
  def g_signal_connect_data(obj: Ptr[Byte], signal: CString, handler: CFuncPtr4[RawPtr,RawPtr,RawPtr,RawPtr,Unit], data: RawPtr, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern
  def g_signal_connect_data(obj: Ptr[Byte], signal: CString, handler: CFuncPtr5[RawPtr,RawPtr,RawPtr,RawPtr,RawPtr,Unit], data: RawPtr, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern
}

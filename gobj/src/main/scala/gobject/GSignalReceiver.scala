package gobject

import de.surfice.smacrotools.debug
import glib.gulong

import scala.scalanative._
import scala.scalanative.cobj._
import scala.scalanative.interop.RefZone
import scala.scalanative.runtime.RawPtr
import scala.scalanative.unsafe._

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
//  def connectData[R](detailedSignal: CString, cHandler: CFuncPtr0[Unit], data: RawPtr, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern
//  def connectData[T1,R](detailedSignal: CString, cHandler: CFuncPtr1[T1,R], data: RawPtr, destroyData: GClosureNotify, connectFlags: GConnectFlags): gulong = extern

  final def connect0[R](signal: CString, handler: Function0[R])(implicit refZone: RefZone): gulong = GSignalReceiverExtern.g_signal_connect_data(
    this.__ptr,
    signal,
    new CFuncPtr1[RawPtr,Unit] { override def apply(ctx: RawPtr): Unit = interop.rawPtrToObject(ctx).asInstanceOf[Function0[Unit]].apply() },
    refZone.export(handler),
    null,
    GConnectFlags.SWAPPED
  )


  final def connect1[R](signal: CString, handler: Function1[Ptr[Byte],R])(implicit refZone: RefZone): gulong = GSignalReceiverExtern.g_signal_connect_data(
    this.__ptr,
    signal,
    new CFuncPtr2[RawPtr,RawPtr,Unit] { override def apply(ctx: RawPtr, arg1: RawPtr): Unit =
      interop.rawPtrToObject(ctx).asInstanceOf[Function1[Ptr[Byte],Unit]].apply(interop.ptrFromRawPtr[Byte](arg1))
    },
    refZone.export(handler),
    null,
    GConnectFlags.SWAPPED
  )

  final def connect2[R](signal: CString, handler: Function2[Ptr[Byte],Ptr[Byte],R])(implicit refZone: RefZone): gulong = GSignalReceiverExtern.g_signal_connect_data(
    this.__ptr,
    signal,
    new CFuncPtr3[RawPtr,RawPtr,RawPtr,Unit] { override def apply(ctx: RawPtr, arg1: RawPtr, arg2: RawPtr): Unit =
      interop.rawPtrToObject(ctx).asInstanceOf[Function2[Ptr[Byte],Ptr[Byte],Unit]].apply(interop.ptrFromRawPtr[Byte](arg2), interop.ptrFromRawPtr[Byte](arg1))
    },
    refZone.export(handler),
    null,
    GConnectFlags.SWAPPED
  )

  final def connect3[R](signal: CString, handler: Function3[Ptr[Byte],Ptr[Byte],Ptr[Byte],R])(implicit refZone: RefZone): gulong = GSignalReceiverExtern.g_signal_connect_data(
    this.__ptr,
    signal,
    new CFuncPtr4[RawPtr,RawPtr,RawPtr,RawPtr,Unit] { override def apply(ctx: RawPtr, arg1: RawPtr, arg2: RawPtr, arg3: RawPtr): Unit =
      interop.rawPtrToObject(ctx).asInstanceOf[Function3[Ptr[Byte],Ptr[Byte],Ptr[Byte],Unit]].apply(interop.ptrFromRawPtr[Byte](arg3),interop.ptrFromRawPtr[Byte](arg1), interop.ptrFromRawPtr[Byte](arg2))
    },
    refZone.export(handler),
    null,
    GConnectFlags.SWAPPED
  )

  final def connect4[R](signal: CString, handler: Function4[Ptr[Byte],Ptr[Byte],Ptr[Byte],Ptr[Byte],R])(implicit refZone: RefZone): gulong = GSignalReceiverExtern.g_signal_connect_data(
    this.__ptr,
    signal,
    new CFuncPtr5[RawPtr,RawPtr,RawPtr,RawPtr,RawPtr,Unit] { override def apply(ctx: RawPtr, arg1: RawPtr, arg2: RawPtr, arg3: RawPtr, arg4: RawPtr): Unit =
      interop.rawPtrToObject(ctx).asInstanceOf[Function4[Ptr[Byte],Ptr[Byte],Ptr[Byte],Ptr[Byte],Unit]].apply(interop.ptrFromRawPtr[Byte](arg4),interop.ptrFromRawPtr[Byte](arg1), interop.ptrFromRawPtr[Byte](arg2), interop.ptrFromRawPtr[Byte](arg3))
    },
    refZone.export(handler),
    null,
    GConnectFlags.SWAPPED
  )

  /**
   * Blocks a signal handler for this instance so it will not be called during any signal emissions unless it is unblocked again.
   *
   * @param handlerId handler ID returned by the `connect` call.
   */
  @name("g_signal_handler_block")
  def blockHandler(handlerId: gulong): Unit = extern

  /**
   * Undoes the effect of a previous [[blockHandler()]] call.
   *
   * @param handlerId handler ID returned by the `connect` call.
   */
  @name("g_signal_handler_unblock")
  def unblockHandler(handlerId : gulong): Unit = extern
}


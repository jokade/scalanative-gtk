package libsoup

import de.surfice.smacrotools.debug
import gio.{GAsyncReadyCallback, GAsyncResult, GCancellable, GInputStream}
import glib.{GError, gpointer, guint}
import gobject.GObject

import scalanative.native._
import cobj._
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.Try

/**
 * Soup session state object.
 *
 * @see [[https://developer.gnome.org/libsoup/stable/SoupSession.html]]
 */
@CObj
class SoupSession extends GObject {
//  private val _queued = collection.mutable.Set.empty[Promise[SoupMessage]]

  def addFeature(feature: SoupSessionFeature): Unit = extern

  def sendMessage(msg: SoupMessage): guint = extern
  def send(msg: SoupMessage, cancellable: GCancellable)(implicit error: Out[GError]): GInputStream = extern
  def queueMessage(msg: SoupMessage, callback: CFunctionPtr3[Ptr[Byte],Ptr[Byte],Ptr[Byte],_], data: Ptr[Byte]): Unit = extern
  def sendAsync(msg: SoupMessage, cancellable: GCancellable, callback: GAsyncReadyCallback, data: gpointer): Unit = extern
  def sendFinish(result: GAsyncResult)(implicit error: Out[GError]): GInputStream = extern

  def queueMessage(msg: SoupMessage): Future[SoupMessage] = {
    val promise = Promise[SoupMessage]()
//    _queued += promise
    queueMessage(msg,SoupSession.callbackPtr, promise.cast[Ptr[Byte]])
    promise.future
  }

  def GET[R](uri: String, queryFields: (String,String)*)(f: SoupMessage=>Try[R])(implicit ec: ExecutionContext): Future[R] = {
    val req = SoupMessage.fromQuery(uri,queryFields)
    req.ref()
    queueMessage(req).flatMap{ msg =>
      val result = Future.fromTry(f(msg))
      msg.unref()
      result
    }
  }

  def getStream(uri: String, queryFields: (String,String)*)(implicit ec: ExecutionContext): Future[GInputStream] = {
    val promise = Promise[GInputStream]()
    val req = SoupMessage.fromQuery(uri,queryFields)
    req.ref()
    sendAsync(req,null,SoupSession.asyncCallbackPtr,promise.cast[gpointer])
    promise.future
  }

}

object SoupSession {
  @name("soup_session_new")
  def apply(): SoupSession = extern

  private def callback(pSession: Ptr[Byte], pMsg: Ptr[Byte], pData: Ptr[Byte]): Unit = {
    val msg = new SoupMessage(pMsg)
    val promise = pData.cast[Promise[SoupMessage]]
    promise.success(msg)
  }
  private val callbackPtr = CFunctionPtr.fromFunction3(callback)

  private def asyncCallback(pSession: Ptr[Byte], pResult: Ptr[Byte], pData: Ptr[Byte]): Unit = {
    val promise = pData.cast[Promise[GInputStream]]
    val err = stackalloc[Ptr[Byte]]
    !err = null
    val stream = new GInputStream(__ext.soup_session_send_finish(pSession,pResult,null))
    promise.success(stream)
  }

  private val asyncCallbackPtr = CFunctionPtr.fromFunction3(asyncCallback)
}

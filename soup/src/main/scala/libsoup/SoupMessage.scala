package libsoup

import de.surfice.smacrotools.debug
import glib.utils.GZone
import glib.{GBytes, gint, gsize, guint}
import gobject.GObject

import scalanative.native._
import cobj._

/**
 * An HTTP request and response.
 *
 * @see [[https://developer.gnome.org/libsoup/stable/SoupMessage.html]]
 */
@CObj
class SoupMessage(method: CString, uriString: CString) extends GObject {

  def setRequest(contentType: CString, reqUse: SoupMemoryUse, reqBody: CString, reqLength: gsize): Unit = extern

  @inline def getHttpVersion: SoupHTTPVersion = extern
  @inline def setHttpVersion(version: SoupHTTPVersion): Unit = extern

  @inline def getUri: SoupUri = extern
  @inline def setUri(uri: SoupUri): Unit = extern

  @inline def getAddress: SoupAddress = extern

  @inline def statusCode: guint = getUIntProp(c"status-code")
  @inline def statusCode_=(code: guint): Unit = setUIntProp(c"status-code",code)

  @inline def reasonPhrase: CString = getCStringProp(c"reason-phrase")

  def responseBody: SoupMessageBody = {
    val p = getObjectProp(c"response-body")
    if(p==null)
      null
    else
      new SoupMessageBody(p)
  }

  def responseBodyData: GBytes = {
    val p = getObjectProp(c"response-body-data")
    if(p==null)
      null
    else
      new GBytes(p)
  }

  def responseHeaders: SoupMessageHeaders = {
    val p = getObjectProp(c"response-headers")
    if(p==null)
      null
    else
      new SoupMessageHeaders(p)
  }

  def responseString: CString = responseBody.data
}

object SoupMessage {

  @name("soup_message_new_from_uri")
  def fromURI(method: CString, uri: SoupUri): SoupMessage = extern

  def fromQuery(uri: String, queryFields: (String,String)*): SoupMessage = fromQuery(uri,queryFields)

  /**
   * Creates a GET request to the specified URI, appending the provided key/value pairs as query.
   *
   * @param uri Request URI (including scheme, host, and path)
   * @param queryFields key value pairs to be added as query to the URI
   *
   * @example ```
   * fromQuery("https://foo.com/api","key"->"value")
   * ```
   * will result in a GET request to "https://foo.com/api?key=value"
   */
  def fromQuery(uri: String, queryFields: Iterable[(String,String)]): SoupMessage = GZone{ implicit z => queryFields match {
    case Nil => formRequest0(c"GET",toCString(uri),null)
    case Seq((k1,v1)) => formRequest1(c"GET",toCString(uri),toCString(k1),toCString(v1),null)
    case Seq((k1,v1),(k2,v2)) => formRequest2(c"GET",toCString(uri),toCString(k1),toCString(v1),toCString(k2),toCString(v2),null)
    case Seq((k1,v1),(k2,v2),(k3,v3)) => formRequest3(c"GET",toCString(uri),toCString(k1),toCString(v1),toCString(k2),toCString(v2),toCString(k3),toCString(v3),null)
    case Seq((k1,v1),(k2,v2),(k3,v3),(k4,v4)) => formRequest4(c"GET",toCString(uri),toCString(k1),toCString(v1),toCString(k2),toCString(v2),toCString(k3),toCString(v3),toCString(k4),toCString(v4),null)
    case Seq((k1,v1),(k2,v2),(k3,v3),(k4,v4),(k5,v5)) => formRequest5(c"GET",toCString(uri),toCString(k1),toCString(v1),toCString(k2),toCString(v2),toCString(k3),toCString(v3),toCString(k4),toCString(v4),toCString(k5),toCString(v5),null)
    case Seq((k1,v1),(k2,v2),(k3,v3),(k4,v4),(k5,v5),(k6,v6)) => formRequest6(c"GET",toCString(uri),toCString(k1),toCString(v1),toCString(k2),toCString(v2),toCString(k3),toCString(v3),toCString(k4),toCString(v4),toCString(k5),toCString(v5),toCString(k6),toCString(v6),null)
    case Seq((k1,v1),(k2,v2),(k3,v3),(k4,v4),(k5,v5),(k6,v6),(k7,v7)) => formRequest7(c"GET",toCString(uri),toCString(k1),toCString(v1),toCString(k2),toCString(v2),toCString(k3),toCString(v3),toCString(k4),toCString(v4),toCString(k5),toCString(v5),toCString(k6),toCString(v6),toCString(k7),toCString(v7),null)
    case _ => throw new UnsupportedOperationException("cannot handle more than 7 query fields")
  }}
/*
  def fromQuery(uri: CString): SoupMessage =
    formRequest0(c"GET",uri,null)

  def fromQuery(uri: CString, field1: CString, value1: CString): SoupMessage =
    formRequest1(c"GET",uri,field1,value1,null)

  def fromQuery(uri: CString, field1: CString, value1: CString, field2: CString, value2: CString): SoupMessage =
    formRequest2(c"GET",uri,field1,value1,field2, value2, null)

  def fromQuery(uri: CString, field1: CString, value1: CString, field2: CString, value2: CString, field3: CString, value3: CString): SoupMessage =
    formRequest3(c"GET",uri,field1,value1,field2, value2, field3, value3, null)

  def fromQuery(uri: CString, field1: CString, value1: CString, field2: CString, value2: CString, field3: CString, value3: CString, field4: CString, value4: CString): SoupMessage =
    formRequest4(c"GET",uri,field1,value1,field2, value2, field3, value3, field4, value4, null)

  def fromQuery(uri: CString, field1: CString, value1: CString, field2: CString, value2: CString, field3: CString, value3: CString, field4: CString, value4: CString, field5: CString, value5: CString): SoupMessage =
    formRequest5(c"GET",uri,field1,value1,field2, value2, field3, value3, field4, value4, field5, value5, null)

  def fromQuery(uri: CString, field1: CString, value1: CString, field2: CString, value2: CString, field3: CString, value3: CString, field4: CString, value4: CString, field5: CString, value5: CString, field6: CString, value6: CString): SoupMessage =
    formRequest6(c"GET",uri,field1,value1,field2, value2, field3, value3, field4, value4, field5, value5, field6, value6, null)

  def fromQuery(uri: CString, field1: CString, value1: CString, field2: CString, value2: CString, field3: CString, value3: CString, field4: CString, value4: CString, field5: CString, value5: CString, field6: CString, value6: CString, field7: CString, value7: CString): SoupMessage =
    formRequest7(c"GET",uri,field1,value1,field2, value2, field3, value3, field4, value4, field5, value5, field6, value6, field7, value7, null)
*/
  @name("soup_form_request_new")
  def formRequest0(method: CString, uri: CString, last: Ptr[Byte]): SoupMessage = extern
  @name("soup_form_request_new")
  def formRequest1(method: CString, uri: CString, k1: CString, v1: CString, last: Ptr[Byte]): SoupMessage = extern
  @name("soup_form_request_new")
  def formRequest2(method: CString, uri: CString, k1: CString, v1: CString, k2: CString, v2: CString, last: Ptr[Byte]): SoupMessage = extern
  @name("soup_form_request_new")
  def formRequest3(method: CString, uri: CString, k1: CString, v1: CString, k2: CString, v2: CString, k3: CString, v3: CString, last: Ptr[Byte]): SoupMessage = extern
  @name("soup_form_request_new")
  def formRequest4(method: CString, uri: CString, k1: CString, v1: CString, k2: CString, v2: CString, k3: CString, v3: CString, k4: CString, v4: CString, last: Ptr[Byte]): SoupMessage = extern
  @name("soup_form_request_new")
  def formRequest5(method: CString, uri: CString, k1: CString, v1: CString, k2: CString, v2: CString, k3: CString, v3: CString, k4: CString, v4: CString, k5: CString, v5: CString, last: Ptr[Byte]): SoupMessage = extern
  @name("soup_form_request_new")
  def formRequest6(method: CString, uri: CString, k1: CString, v1: CString, k2: CString, v2: CString, k3: CString, v3: CString, k4: CString, v4: CString, k5: CString, v5: CString, k6: CString, v6: CString, last: Ptr[Byte]): SoupMessage = extern
  @name("soup_form_request_new")
  def formRequest7(method: CString, uri: CString, k1: CString, v1: CString, k2: CString, v2: CString, k3: CString, v3: CString, k4: CString, v4: CString, k5: CString, v5: CString, k6: CString, v6: CString, k7: CString, v7: CString, last: Ptr[Byte]): SoupMessage = extern

}

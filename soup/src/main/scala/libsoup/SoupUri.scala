package libsoup

import de.surfice.smacrotools.debug
import glib.{GAllocated, GLib}
import gobject.GBoxed

import scalanative.native._
import cobj._
import scala.scalanative.native.cobj.runtime.CObjObject

/**
 * @see [[https://developer.gnome.org/libsoup/stable/SoupURI.html]]
 */
@CObj
@debug
class SoupUri extends CObjObject with GBoxed with GAllocated {

  /**
   * Sets the URIs host part.
   *
   * @param host the hostname or IP address, or null
   */
  def setHost(host: CString): Unit = extern

  /**
   * Sets the URIs path part.
   */
  def setPath(path: CString): Unit = extern

  /**
   * Sets the URIs scheme part.
   *
   * @param scheme the URI scheme
   */
  def setScheme(scheme: CString): Unit = extern

//  def setQueryFromFields(fields: (String,String)*): Unit = Zone{ implicit z =>
//    fields match {
//      case Seq((k1,v1)) => SoupUri.ext.soup_uri_set_query_from_fields(__ptr,toCString(k1),toCString(v1),null)
//      case _ => throw new UnsupportedOperationException("cannot handle queries with more than 1 key/value pair")
//    }
//  }

  @name("soup_uri_to_string")
  def cstring(): CString = extern

  override def toString(): String = {
    val cstr = cstring()
    val s = fromCString(cstr)
    GLib.free(cstr)
    s
  }
}

object SoupUri {

  @name("soup_uri_new")
  def apply(uri: CString): SoupUri = extern
 /*
  def apply(host: String, scheme: String = "http"): SoupUri = Zone{ implicit z =>
    val uri = new SoupUri(ext.soup_uri_new(toCString(host)))
    uri.setScheme(toCString(scheme))
    uri
  }
  */

//  @extern
//  private object ext {
//    def soup_uri_new(uri: CString): Ptr[Byte] = extern
//    def soup_uri_set_query_from_fields(self: Ptr[Byte], k1: CString, v1: CString, last: Ptr[Byte]): Unit = extern
//  }
}

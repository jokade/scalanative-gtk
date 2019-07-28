// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.json

import glib.utils.GZone
import glib.{GError, gboolean, gssize}
import gobject.GObject

import scalanative._
import unsafe._
import cobj._
import scala.util.{Failure, Success, Try}

/**
 * Parse JSON data streams.
 *
 * @see [[https://developer.gnome.org/json-glib/stable/JsonParser.html]]
 */
@CObj
class JsonParser extends GObject {

  /**
   * Loads a JSON stream from a buffer and parses it.
   *
   * @param data the buffer to parse
   * @param length the length of the buffer, or -1
   * @param error return location for a GError, or null
   * @return true if the buffer was successfully parsed.
   */
  def loadFromData(data: CString, length: gssize)(implicit error: ResultPtr[GError]): gboolean = extern

  /**
   * Loads a JSON stream from the content of `filename`.
   *
   * @param filename
   * @param error return location for a GError, or null
   * @return true if the buffer was successfully parsed.
   */
  def loadFromFile(filename: CString)(implicit error: ResultPtr[GError]): gboolean = extern

  /**
   * Retrieves the top level node from the parsed JSON stream.
   * @return
   */
  @name("json_parser_get_root")
  def root: JsonNode = extern
}

object JsonParser {
  class ParseError(msg: String) extends scala.Error(msg)
  object ParseError {
    def apply(err: GError): ParseError = ??? //new ParseError(fromCString(err.message))
  }

  @name("json_parser_new")
  def apply(): JsonParser = extern

  /**
   * Parses the provided JSON string and returns the parser object on success, or else a ParseError.
   *
   * @param data the buffer to parse
   * @param length the length of the buffer, or -1
   */
  def fromData(data: CString, length: gssize = -1): Try[JsonParser] = {
    val parser = JsonParser()
    GError[Try[JsonParser]]{ implicit err =>
      parser.loadFromData(data,length)
      Success(parser)
    } { err =>
      parser.free()
      Failure(ParseError(err))
    }
  }

  def fromFile(filename: String): Try[JsonParser] = GZone{ implicit z =>
    val parser = JsonParser()
    GError[Try[JsonParser]]{ implicit err =>
      parser.loadFromFile(toCString(filename))
      Success(parser)
    } { err =>
      parser.free()
      Failure(ParseError(err))
    }
  }
}

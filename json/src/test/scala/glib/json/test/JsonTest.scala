package glib.json.test

import glib.json.JsonParser

import scalanative._
import unsafe._
import utest._

import scala.util.{Failure, Success}

object JsonTest extends JsonTestSuite {
  val json =
   """{
      |'int' : 42,
      |'string' : 'Hello world'
      |}""".stripMargin

  val tests = Tests{
    'fromData-{
      'error-{
        JsonParser.fromData(c"'int':42") match {
          case Failure(exception) => exception.getMessage ==> "<data>:1:6: Parse error: unexpected character `:', expected value"
          case _ => throw new RuntimeException("expected error")
        }
      }
      'success- {
        Zone { implicit z =>
          val root = (JsonParser.fromData(toCString(json)) match {
            case Success(p) => p
            case Failure(ex) => throw ex
          }).root.getObject()
          root.int(c"int").get ==> 42
          root.string(c"string").get ==> "Hello world"
        }
      }
    }
  }
}

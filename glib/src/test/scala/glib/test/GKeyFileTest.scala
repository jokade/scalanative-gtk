// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import scalanative.native._
import glib.{GError, GKeyFile}
import utest._

import scala.scalanative.native.CObj.Out

object GKeyFileTest extends TestSuite {
  val tests = Tests{
    implicit val error: Out[GError] = null
    'basicOps-{
      val keys = new GKeyFile()
      'setGet-{
        keys.setValue(c"foo",c"bar",c"string")
        assert( fromCString(keys.getString(c"foo",c"bar")) == "string" )
        keys.free()
      }
    }
    'wrappers-AutoreleasePool{ implicit pool: AutoreleasePool =>
      val keys = new GKeyFile
      assert( keys.groups.size == 0 )

      keys.setValue(c"foo",c"bar",c"42")
      keys.setValue(c"foo",c"foo",c"string")
    }
  }
}

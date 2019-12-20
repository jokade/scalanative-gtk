// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.{GError, GQuark}
import utest._
import scalanative._
import unsafe._

object GErrorTest extends GlibTestSuite {
  val tests = Tests {
    'new-{
      val err = GError(GQuark("foo"),42,c"error message")
      GQuark.string(err.domain) ==> "foo"
      err.code ==> 42
      err.message ==> "error message"
      err.free()
    }
  }
}

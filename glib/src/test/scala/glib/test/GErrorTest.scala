// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.GError
import scalanative.native._
import utest._

object GErrorTest extends TestSuite {
  val tests = Tests {
    'new-{
      // TODO: add varags when they are supported
      val err = new GError(1.toUInt,2,c"foo")
      assert(
        err.domain == 1.toUInt,
        err.code == 2,
        fromCString(err.message) == "foo"
      )
    }
  }
}

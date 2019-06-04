// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.GBytes
import utest._

object GBytesTest extends TestSuite {
  val tests = Tests {
    'createEmpty-{
      val bytes = new GBytes(null,0)
      assert( bytes.getSize() == 0, bytes.getData(null) == null )
      bytes.free()
    }
  }
}

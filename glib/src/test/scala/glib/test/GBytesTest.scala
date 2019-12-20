// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.GBytes
import utest._
import scalanative.unsafe._

object GBytesTest extends TestSuite {
  val tests = Tests {
    'createEmpty-{
      val bytes = GBytes(null,0)
      bytes.size ==> 0
      bytes.getData(null) ==> null
      bytes.free()
    }
    'fromPtr-{
      val data = stackalloc[CInt](3)
      data(0) = 42
      data(1) = Int.MinValue
      data(2) = Int.MaxValue

      val bytes = GBytes(data,3*4)
      val p = bytes.data
      p(0) ==> 42
      p(1) ==> Int.MinValue
      p(2) ==> Int.MaxValue
    }
  }
}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.GVariantType
import utest._
import scalanative.native._

object GVariantTypeTest extends TestSuite {
  val tests = Tests {
    'equal-{
      assert( GVariantType.equal(GVariantType.UINT32, GVariantType.UINT32) )
      assert( ! GVariantType.equal(GVariantType.UINT32, GVariantType.UINT64) )
    }
    'isSubtypeOf-{
      assert( GVariantType.isSubtypeOf(GVariantType.BOOLEAN, GVariantType.ANY) )
      assert( ! GVariantType.isSubtypeOf(GVariantType.UINT32, GVariantType.UINT64) )
    }
    'new_free-{
      val tpe = GVariantType.newType(c"d")
      assert( GVariantType.equal(GVariantType.DOUBLE, tpe) )
      GVariantType.free(tpe)
    }
    'copy_free-{
      val tpe = GVariantType.copy( GVariantType.BYTE )
      assert( GVariantType.equal(GVariantType.BYTE, tpe) )
      GVariantType.free(tpe)
    }
    'stringIsValid-{
      assert(
        GVariantType.stringIsValid(c"d"),
        ! GVariantType.stringIsValid(c"dd")
      )
    }
  }
}

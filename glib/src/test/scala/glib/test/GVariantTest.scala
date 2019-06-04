// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.{GVariant, GVariantType}

import scalanative.native._
import utest._

object GVariantTest extends TestSuite {
  val tests = Tests {
    /*
    'new_ref_unref-{
      val v = new GVariant(c"u",42).ref()
      assert( GVariantType.equal(v.getType(), GVariantType.UINT32) )
      v.unref()
    }
    */
    'equals-{
      val v1 = GVariant.int32(42)
      val v2 = GVariant.int32(42)
      val v3 = GVariant.int64(42L)
      assert( v1 == v2, v1 != v3 )
      v1.free()
      v2.free()
      v3.free()
    }
  }
}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import scalanative.native._
import glib._
import utest._

object GListTest extends TestSuite {
  val tests = Tests {
    'raw-{
      'emptyList-{
        val list = new GList()
        assert( list.isEmpty, !list.nonEmpty, list.size == 0)
      }
      'prepend_append_nth - {
        val list = new GList()
        list += GVariant(1)
        GVariant(0) +=: list
        list += GVariant(2)
        assert(
          list.size == 3,
          list[GVariant](0).getInt32() == 0,
          list[GVariant](1).getInt32() == 1,
          list[GVariant](2).getInt32() == 2
        )
      }
      'foreach-{
        val list = GList(GVariant(1),GVariant(2),GVariant(3))
        var res = 0
        list.foreach{ elem: GVariant => res += elem.getInt32() }
        assert(res == 6)
      }
    }
  }
}

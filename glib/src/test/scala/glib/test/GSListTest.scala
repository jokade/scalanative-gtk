// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.{GSList, GVariant}
import utest._

import scalanative.native._
import cobj._
import Implicits._

object GSListTest extends TestSuite {
  val tests = Tests {
    'raw- {
      'emptyList - {
        val list = new GSList()
        assert(list.length() == 0.toUInt, list.isEmpty)
      }
      'prepend_append_nth_lastPtr - {
        val list = new GSList()
        val elem = GVariant.int32(42)
        list.append(elem.__ref.toPtr)
        assert(
          list.length() == 1.toUInt,
          GVariant(!list.nth(0.toUInt)._1) == elem,
          GVariant(!list.lastPtr._1) == elem
        )
        val ptr = list.lastPtr
      }
      'fromVararg_appendAll_apply-{
        val list = GSList( GVariant(0), GVariant(1))
        assert(list.size == 2)

        list.appendAll(Seq(GVariant(2),GVariant(3)))
        assert(
          list.size == 4,
          list[GVariant](0).getInt32() == 0,
          list[GVariant](1).getInt32() == 1,
          list[GVariant](2).getInt32() == 2,
          list[GVariant](3).getInt32() == 3
        )
      }
    }
    'wrapped-{
      val list = new GSList().asScala[GVariant]
      'prepend_append-{
        list.prepend(GVariant(1))
        list.append(GVariant(2))
        list.prepend(GVariant(0))
        assert(
          list.length == 3,
          list(0).getInt32() == 0,
          list(1).getInt32() == 1,
          list(2).getInt32() == 2
        )
      }
      'appendAll_map-{
        list ++= Seq(GVariant(1),GVariant(2))
        list.prepend(GVariant(0))
        list ++= Seq( GVariant(3) )
        assert(
          list.length == 4,
          list(0).getInt32() == 0,
          list(1).getInt32() == 1,
          list(2).getInt32() == 2,
          list(3).getInt32() == 3
        )

        val res = list.map(_.getInt32() + 1)
        assert( res == Seq(1,2,3,4))
      }
    }
  }
}

package glib.test

import glib.{GEqualFunc, GHashTable, GVariant}
import utest._

object GHashTableTest extends TestSuite {
  val tests = Tests {
    'basicOps- {
      'noHashFunc_noKeyEqualFunc - {
        val eut = GHashTable[GVariant, GVariant]()
        eut.size ==> 0

        val v1 = GVariant(1)
        eut.insert(v1, GVariant(2)) ==> true
        eut.size ==> 1

        eut.lookup(v1).getInt32() ==> 2
        eut.lookup(GVariant(1)) ==> null

        eut.contains(v1) ==> true
        eut.contains(GVariant(2)) ==> false

        val v3 = GVariant(3)
        eut.insert(v3,GVariant(4)) ==> true
        eut.size ==> 2

        var sum = 0
        val keys = eut.getKeys().foreach( (i:GVariant) => sum += i.getInt32())
        sum ==> 4

        sum = 0
        eut.foreach( (k,v) => {
          sum += k.getInt32() + v.getInt32()
        })
        sum ==> 10
      }
    }
  }
}

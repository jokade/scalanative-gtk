package glib.test

import scalanative._
import unsafe._
import unsigned._
import glib._
import utest._

import scala.scalanative.cobj.CObject

trait GListLikeTest extends TestSuite {
  def emptyList[T <: CObject]: GListLike[T]

  val tests = Tests {
    'basicOps - {
      val empty = emptyList[GVariant]
      empty.size ==> 0
      empty.isEmpty ==> true
      empty.nonEmpty ==> false

      val v1 = GVariant(1)
      val l1 = empty.prepend(v1)

      l1.size ==> 1
      empty.isEmpty ==> true
      empty.nonEmpty ==> false
      l1.nthData(0.toUInt).getInt32() ==> 1

      val v2 = GVariant(2)
      val v3 = GVariant(3)
      val l2 = l1.append(v2).append(v3)
      l2.size ==> 3
      l2(0).getInt32() ==> 1
      l2(1).getInt32() ==> 2
      l2(2).getInt32() ==> 3

      val l3 = l2.reverse()
      l3.size ==> 3
      l3(0).getInt32() ==> 3
      l3(1).getInt32() ==> 2
      l3(2).getInt32() ==> 1

      val l4 = l3.appendAll(Seq(0, -1, -2).map(GVariant.apply))

      l4.size ==> 6

      l4(0).getInt32() ==> 3
      l4(1).getInt32() ==> 2
      l4(2).getInt32() ==> 1
      l4(3).getInt32() ==> 0
      l4(4).getInt32() ==> -1
      l4(5).getInt32() ==> -2
/*
      val l5 = l4.insert(GVariant(42),1)
      l5.size ==> 7
      l5(0).getInt32() ==> 3
      l5(1).getInt32() ==> 42
      l5(2).getInt32() ==> 2
      l5(3).getInt32() ==> 1
      l5(4).getInt32() ==> 0
      l5(5).getInt32() ==> -1
      l5(6).getInt32() ==> -2

      var sum = 0
      l5.foreach((x:GVariant) => sum += x.getInt32())
      sum ==> 45
 */
    }
/*
    'asScala-{
      val eut = emptyList[GVariant].appendAll(Seq(GVariant(1),GVariant(2),GVariant(3)))
      eut.size ==> 3

      val list = eut.asScala
      list.size ==> 3
      list ==> Seq(GVariant(1),GVariant(2),GVariant(3))

    }

 */
  }
}

object GListTest extends GListLikeTest {
  override def emptyList[T<:CObject]: GListLike[T] = GList.empty[T]
}

//object GSListTest extends GListLikeTest {
//  override def emptyList[T <: CObject]: GListLike[T] = GSList.empty[T]
//}

//object GSequenceTest extends GListLikeTest {
//  override def emptyList[T <: CObject]: GListLike[T] = GSequence[T]()
//}
package glib.test

import glib.GQuark
import utest._

object GQuarkTest extends GlibTestSuite {

  val tests = Tests{
    'fromString_toString-{
      val q = GQuark("foo")
      GQuark.string(q) ==> "foo"
    }
  }
}

package glib.test

import utest.TestSuite

import scala.scalanative.interop._

abstract class GlibTestSuite extends TestSuite {
  JNA.nameResolver = JNANameResolver.singleLibrary("glib-2.0")
}

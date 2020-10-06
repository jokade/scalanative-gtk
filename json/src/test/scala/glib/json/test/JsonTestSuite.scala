package glib.json.test

import utest._

import scala.scalanative.interop.{JNA, JNANameResolver}

abstract class JsonTestSuite extends TestSuite {
  JNA.nameResolver = JNANameResolver.singleLibrary("json-glib-1.0")
}

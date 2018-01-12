// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.GLib
import utest._
import scalanative.native._

object gversionTests extends TestSuite {
  val tests = Tests {
    'checkVersion-{
      'succes-{
        assert( GLib.checkVersion(GLib.majorVersion,GLib.minorVersion,GLib.microVersion) == null )
      }
      'fail-{
        assert( GLib.checkVersion(5.toUInt,GLib.minorVersion,GLib.microVersion) != null )
//        GLib.checkVersion(5.toUInt,GLib.minorVersion,GLib.microVersion)
      }
    }
  }
}

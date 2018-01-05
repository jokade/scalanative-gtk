// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.test

import glib.GLib
import utest._
import scalanative.native._

object gversionTests extends TestSuite {
  val tests = Tests {
    'checkVersion-{
      'succes-{
        GLib.checkVersion(GLib.majorVersion,GLib.minorVerison,GLib.microVersion)
      }
      'fail-{
        assert( GLib.checkVersion(5.toUInt,GLib.minorVerison,GLib.microVersion).isFailure )
      }
    }
  }
}

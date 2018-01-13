// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib.convert

import glib.{GAllocated, GLib, GSeq, gsize}

import scala.collection.{AbstractIterable, AbstractSeq}
import scalanative.native._

object Wrappers {
  case class NullTerminatedStringArray(array: Ptr[CString], length: Int)
    extends AbstractSeq[CString] with IndexedSeq[CString] with GSeq[CString] {
    def apply(idx: Int): CString = array(idx)
    override def free(): Unit = {
      GLib.strfreev(array)
    }
  }
}


// Copyright (c) 2019. Distributed under the MIT License (see included LICENSE file).
package gtk

import glib.{GAllocated, gint, gpointer}
import gobject.GBoxed
import gtk.GtkTreeIter.GtkTreeIterStruct

import scalanative.native._
import cobj._
import scala.scalanative.native.cobj.runtime.CObjObject

@CObj
class GtkTreeIter extends CObjObject with GAllocated {
  def __struct: Ptr[GtkTreeIterStruct] = __ptr.cast[Ptr[GtkTreeIterStruct]]

  override def free(): Unit = {
  }
}

object GtkTreeIter {
  type GtkTreeIterStruct = CStruct4[gint,gpointer,gpointer,gpointer]

  /**
   * Allocates a new GtkTreeIter in the current [[Zone]]
   *
   * @param zone
   */
  def alloc(implicit zone: Zone): GtkTreeIter = {
    val iter = scalanative.native.alloc[GtkTreeIterStruct]
    new GtkTreeIter(iter.cast[Ptr[Byte]])
  }

  def apply[R](f: GtkTreeIter=>R): R = Zone{ implicit z =>
    val iter = GtkTreeIter.alloc
    f(iter)
  }
}

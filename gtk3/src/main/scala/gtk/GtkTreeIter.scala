package gtk

import glib.utils.GZone
import glib.{GAllocated, gint, gpointer}
import gobject.GBoxed
import gtk.GtkTreeIter.GtkTreeIterStruct

import scalanative._
import unsafe._
import cobj._

@CObj
class GtkTreeIter extends GAllocated {
  def __struct: Ptr[GtkTreeIterStruct] = __ptr.asInstanceOf[Ptr[GtkTreeIterStruct]]

  override def free(): Unit = {
  }
}

object GtkTreeIter {
  type GtkTreeIterStruct = CStruct4[gint,gpointer,gpointer,gpointer]

  def apply[R](f: GtkTreeIter=>R): R = {
    val iter = new GtkTreeIter( stackalloc[Byte](sizeof[GtkTreeIterStruct]) )
    f(iter)
  }
}

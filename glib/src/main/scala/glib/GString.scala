package glib

import glib.utils.GZone

import scalanative._
import unsafe._
import cobj._

@CObj
class GString extends CObject {
  override def toString: String = fromCString(__ptr)
}

object GString {
  def free(string: Ptr[CString], freeSegment: gboolean): CString = extern
}

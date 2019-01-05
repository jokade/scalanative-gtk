//     Project: scalanative-libsoup
//      Module:
// Description:
package libsoup

import de.surfice.smacrotools.debug
import glib.{GAllocated, GBytes, gsize}
import gobject.GBoxed

import scala.scalanative.native._
import cobj._

@CObj
class SoupBuffer extends GAllocated {
  type SoupBufferStruct = CStruct2[CString,gsize]

  @inline private def __struct: Ptr[SoupBufferStruct] = __ptr.cast[Ptr[SoupBufferStruct]]

  @inline def data: CString = !__struct._1

  def getAsBytes(): GBytes = extern

  @inline def free(): Unit = extern
}

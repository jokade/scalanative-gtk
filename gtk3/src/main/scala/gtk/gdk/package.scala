package gtk

import scalanative._
import unsafe._

package object gdk {
  type GdkEvent = Ptr[Byte]
  type GdkRectangle = CStruct4[CInt,CInt,CInt,CInt]
}

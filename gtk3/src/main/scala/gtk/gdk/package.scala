package gtk

import scalanative._
import unsafe._

package object gdk {

  type GdkRectangle = CStruct4[CInt,CInt,CInt,CInt]
}

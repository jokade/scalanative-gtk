
import scalanative._
import unsafe._

package object gio {
  type GAsyncReadyCallback = CFuncPtr3[Ptr[Byte],Ptr[Byte],Ptr[Byte],Unit]
}

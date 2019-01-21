
import scalanative.native._

package object gio {
  type GAsyncReadyCallback = CFunctionPtr3[Ptr[Byte],Ptr[Byte],Ptr[Byte],Unit]
}


import glib.utils.GZone

import scalanative._
import unsafe._

package object gio {

  def argsToArgv(args: Array[String])(implicit zone: Zone): Ptr[CString] = {
    val argv = alloc[CString](args.length)
    val argvPtr = alloc[Ptr[CString]]
    !argvPtr = argv
    for (i <- args.indices) {
      !(argv + i) = toCString(args(i))
    }
    argv
  }

  type GAsyncReadyCallback = CFuncPtr3[Ptr[Byte],Ptr[Byte],Ptr[Byte],Unit]

  type GApplicationFlags = Int
  object GApplicationFlags {
    val FLAGS_NONE           :GApplicationFlags = 0
    val IS_SERVICE           :GApplicationFlags = 1 << 0
    val IS_LAUNCHER          :GApplicationFlags = 1 << 1
    val HANDLES_OPEN         :GApplicationFlags = 1 << 2
    val HANDLES_COMMAND_LINE :GApplicationFlags = 1 << 3
    val SEND_ENVIRONMENT     :GApplicationFlags = 1 << 4
    val NON_UNIQUE           :GApplicationFlags = 1 << 5
    val CAN_OVERRIDE_APP_ID  :GApplicationFlags = 1 << 6
    val ALLOW_REPLACEMENT    :GApplicationFlags = 1 << 7
    val REPLACE              :GApplicationFlags = 1 << 8
  }
}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import glib.gboolean

import scala.scalanative.native._
import cobj._

@CObj
object Gtk {

  def init(args: Array[String]): Array[String] = Zone{ implicit z: Zone =>
    val argc = alloc[CInt]
    !argc = args.length
    val argv = alloc[CString](args.length)
    val argvPtr = alloc[Ptr[CString]]
    !argvPtr = argv
    for(i <- 0 until args.length) {
      !(argv+i) = toCString(args(i))
    }
    init(argc,argvPtr)
    val newArgs = new Array[String](!argc)
    for(i <- 0 until !argc) {
      newArgs(i) = fromCString(!((!argvPtr)+i))
    }
    // TODO: update returned args
    newArgs
  }

  @inline def main(): Unit = extern
  @inline def mainQuit(): Unit = extern
  @inline def init(argc: Ptr[Int], argv: Ptr[Ptr[CString]]): Unit = extern

  def mainIteration(): gboolean = extern

}


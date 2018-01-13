// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import scala.scalanative.native._

@CObj
object Gtk {

  def init(args: Array[String]): Array[String] = {
    val argc = stackalloc[CInt]
    !argc = 0
    init(argc,null)
    args
  }

  @inline def main(): Unit = extern
  @inline def mainQuit(): Unit = extern
  @inline def init(argc: Ptr[Int], argv: Ptr[Ptr[CString]]): Unit = extern

}


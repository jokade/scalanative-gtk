package glib.utils

import scala.scalanative.unsafe.{CSize, Zone}

trait GZone extends Zone {
  def totalSize: CSize
  def statInfo: String
  def ref(): Unit
  def unref(): Unit
}
object GZone {

  def apply[T](f: Zone => T): T = Zone{ implicit z =>
    f(z)
  }
}

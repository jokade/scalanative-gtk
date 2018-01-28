// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scalanative.native._

trait GWrapper[T] {
  def wrap(ptr: gpointer): T
  def unwrap(value: T): gpointer
}

object GWrapper {
  implicit object GVariantWrapper extends GWrapper[GVariant] {
    @inline def wrap(ptr: gpointer): GVariant = GVariant(ptr)
    @inline def unwrap(value: GVariant): gpointer = value.__ref.cast[gpointer]
  }
}

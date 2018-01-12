// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

/**
 * This trait is not specified explicitly by GLib, but some of its
 * types provide reference count based freeing using `_ref` and `_unref`.
 */
trait GRefCounter {
  /**
   * Increases the reference count on this object.
   */
  // TODO: return self, instead of Unit
  def ref(): Unit

  /**
   * Decreases the reference count on this object. This may result in the object beein freed.
   */
  def unref(): Unit
}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scalanative.native._

/**
 * This trait is not defined by GLib, but relfects the fact that
 * all GLib objects allocated with `_new()` have some sort of `_free()`.
 * All object-oriented bindings should implement this trait.
 */
trait GAllocated extends AutoReleasable

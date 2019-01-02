// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package tepl

import gtk.{GtkBuildable, GtkNotebook}

import scalanative.native._
import cobj._

/**
 * TeplNotebook — Subclass of [[gtk.GtkNotebook]] implementing the TeplTabGroup interface
 */
@CObj
class TeplNotebook extends GtkNotebook with TeplTabGroup {

}

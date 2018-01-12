// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import de.surfice.smacrotools.debug
import gobject.GSignalReceiver

import scalanative.native._

@CObj
abstract class GtkWidget {
  def showAll(): Unit = extern
}


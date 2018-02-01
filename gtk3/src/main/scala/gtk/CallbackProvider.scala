// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

trait CallbackProvider {
  /**
   * Registers callbacks by using [[GtkBuilder.addCallbackSymbol]].
   *
   * @param builder GtkBuilder for which callbacks should be registered
   */
  def registerCallbacks(builder: GtkBuilder): Unit
}

// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package tepl

import glib.GList

import scalanative.native._
import cobj._

/**
 * Interface for a group of [[TeplTab]]s.
 *
 * @see [[https://developer.gnome.org/tepl/stable/TeplTabGroup.html]]
 */
@CObj
trait TeplTabGroup extends CObjWrapper {
  /**
   * Gets the list of tabs contained in this group.
   * If this group contains children that are not [[TeplTab]]s, those will not be present
   * in the returned list, i.e. it is not guaranteed that the index of a TeplTab
   * in the returned GList has the same child index in the group container.
   */
  @inline final def getTabs(): GList = extern

  /**
   * Like [[getTabs]] but returns [[TeplView]]s.
   */
  @inline final def getViews(): GList = extern

  /**
   * Like [[getTabs]] but returns [[TeplBuffer]]s.
   */
  @inline final def getBuffers(): GList = extern

  /**
   * Returns the currently displayed tab in this group.
   */
  @nullable
  @inline final def getActiveTab(): TeplTab = extern

  /**
   * Sets the active tab for this group.
   *
   * @param tab TeplTab to be made active (must be part of this group!)
   */
  @inline final def setActiveTab(tab: TeplTab): Unit = extern

  /**
   * Returns the currently active TeplView, or null
   */
  @nullable
  @inline final def getActiveView(): TeplView = extern

//  @inline final def getActiveBuffer(): Tepl
}

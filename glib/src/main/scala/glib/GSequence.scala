package glib

import de.surfice.smacrotools.debug

import scalanative._
import unsafe._
import unsigned._
import cobj._

/**
 * GSequence has the API of a list, but is implemented internally with a balanced tree.
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-Sequences.html]]
 */
@CObj
class GSequence[T] extends GAllocated {

  /**
   * Appends a new element to the end of the list.
   *
   * @param data
   */
  def append(data: T)(implicit wrapper: CObjectWrapper[T]): GSequence[T] = extern

  /**
   * Adds a new element to the start of the list.
   *
   * @note Returns the new head of the list.
   * @param data the data for the new element
   */
  def prepend(data: T)(implicit wrapper: CObjectWrapper[T]): GSequence[T] = extern

  /**
   * Returns the number of elements in this list.
   *
   * @note This function iterates over the whole list to count its elements. To check whether the list is non-empty,
   *       use [[nonEmpty]].
   */
  def getLength(): gint = extern

  override def free(): Unit = extern
}

object GSequence {
  def apply[T](): GSequence[T] = apply(null)

  @name("g_sequence_new")
  def apply[T](dataDestroy: GDestroyNotify): GSequence[T] = extern
}

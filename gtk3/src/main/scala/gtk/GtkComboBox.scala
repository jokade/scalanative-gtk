package gtk

import glib.{gint, gulong}

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone

/**
 * A widget used to choose from a list of items.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkComboBox.html]]
 */
@CObj
class GtkComboBox extends GtkBin {

  def getModel(): GtkTreeModel = extern
  def setModel(model: GtkTreeModel): Unit = extern

  /**
   * Returns the ID of the active row of this this box.
   */
  def getActiveId(): CString = extern

  /**
   * Changes the active row of this box to the one that has an ID equal to `activeId`, or unsets the active row if `activeId` is null.
   *
   * @param activeId
   */
  def setActiveId(activeId: CString): Unit = extern

  /**
   * The column in the combo box's model that provides string IDs for the values in the model, if != -1.
   */
  def idColumn: Int = getIntProp(c"id-column")
  def idColumn_=(idx: Int): Unit = setIntProp(c"id-column",idx)

  /**
   * The column in the combo box's model to associate with strings from the entry if the combo was created with “has-entry” = TRUE.
   */
  def entryTextColumn: Int = getIntProp(c"entry-text-column")
  def entryTextColumn_=(idx: Int): Unit = setIntProp(c"entry-text-column",idx)

  def hasEntry: Boolean = getBooleanProp(c"has-entry")

  /**
   * The changed signal is emitted when the active item is changed.
   *
   * @param handler
   * @param refZone
   */
  def onChanged(handler: Function0[Unit])(implicit refZone: RefZone): gulong = connect0(c"changed",handler)
}

object GtkComboBox {
  /**
   * Creates an empty GtkComboBox.
   */
  @name("gtk_combo_box_new")
  def apply(): GtkComboBox = extern

  /**
   * Creates a empty GtkComboBox with an entry.
   */
  @name("gtk_combo_box_new_with_entry")
  def withEntry(): GtkComboBox = extern

  /**
   * Creates a new GtkComboBox initialized with the provided model.
   * @param model
   */
  @name("gtk_combo_box_new_with_model")
  def withModel(model: GtkTreeModel): GtkComboBox = extern

  /**
   * Creates a new GtkComboBox with an entry and initialized with the provided model.
   * @param model
   * @return
   */
  @name("gtk_combo_box_new_with_model_and_entry")
  def withModelAndEntry(model: GtkTreeModel): GtkComboBox = extern
}

package gtk

import glib.{gboolean, gint, gulong}

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

  def setActive(id: Int): Unit = extern
//  def getModel(): GtkTreeModel = extern
//  def setModel(model: GtkTreeModel): Unit = extern

  def active: gint = getIntProp(c"active")
  def active_=(v: gint): Unit = setIntProp(c"active",v)

  def activeId: String = getStringProp(c"active-id")
  def activeId_=(v: String): Unit = setStringProp(c"active-id",v)

  def addTearoffs: gboolean = getBooleanProp(c"add-tearoffs")
  def addTearoffs_=(v: gboolean): Unit = setBooleanProp(c"add-tearoffs",v)

  def buttonSensitivity: GtkSensitivityType = getIntProp(c"button-sensitivity")
  def buttonSensitivity_=(v: GtkSensitivityType): Unit = setIntProp(c"button-sensitivity",v)

  def cellArea(implicit wrapper: CObjectWrapper[GtkCellArea]): GtkCellArea = wrapper.wrap( getObjectProp(c"cell-area") )
  def cellArea_=(v: GtkCellArea)(implicit wrapper: CObjectWrapper[GtkCellArea]): Unit = setObjectProp(c"cell-area",wrapper.unwrap(v))

  def columnSpanColumn: gint = getIntProp(c"column-span-column")
  def columnSpanColumn_=(v: gint): Unit = setIntProp(c"column-span-column",v)

  def entryTextColumn: gint = getIntProp(c"entry-text-column")
  def entryTextColumn_=(v: gint): Unit = setIntProp(c"entry-text-column",v)

  def hasEntry: gboolean = getBooleanProp(c"has-entry")

  def hasFrame: gboolean = getBooleanProp(c"has-frame")
  def hasFrame_=(v: gboolean): Unit = setBooleanProp(c"has-frame",v)

  def idColumn: gint = getIntProp(c"id-column")
  def idColumn_=(v: gint): Unit = setIntProp(c"id-column",v)

  def model(implicit wrapper: CObjectWrapper[GtkTreeModel]): GtkTreeModel = wrapper.wrap( getObjectProp(c"model") )
  def model_=(v: GtkTreeModel)(implicit wrapper: CObjectWrapper[GtkTreeModel]): Unit = setObjectProp(c"model",wrapper.unwrap(v))

  def popupFixedWidth: gboolean = getBooleanProp(c"popup-fixed-width")
  def popupFixedWidth_=(v: gboolean): Unit = setBooleanProp(c"popup-fixed-width",v)

  def popupShown: gboolean = getBooleanProp(c"popup-shown")

  def rowSpanColumn: gint = getIntProp(c"row-span-column")
  def rowSpanColumn_=(v: gint): Unit = setIntProp(c"row-span-column",v)

  def tearoffTitle: String = getStringProp(c"tearoff-title")
  def tearoffTitle_=(v: String): Unit = setStringProp(c"tearoff-title",v)

  def wrapWidth: gint = getIntProp(c"wrap-width")
  def wrapWidth_=(v: gint): Unit = setIntProp(c"wrap-width",v)

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

package gtk

import de.surfice.smacrotools.debug
import glib._
import glib.utils.GZone
import gobject.GObject

import scalanative._
import unsafe._
import cobj._

@CObj
trait GtkFileChooser extends CObject {
  self: GObject =>
  def action: GtkFileChooserAction = getIntProp(c"action")
  def action_=(a: GtkFileChooserAction): Unit = setIntProp(c"action",a)

  def createFolders: gboolean = getBooleanProp(c"create-folders")
  def createFolders_=(v: gboolean): Unit = setBooleanProp(c"create-folders",v)

  def doOverwriteConfirmation: gboolean = getBooleanProp(c"do-overwrite-confirmation")
  def doOverwriteConfirmation_=(v: gboolean): Unit = setBooleanProp(c"do-overwrite-confirmation",v)

  def extraWidget(implicit wrapper: CObjectWrapper[GtkWidget]): GtkWidget = wrapper.wrap( getObjectProp(c"extra-widget") )
  def extraWidget_=(v: GtkWidget)(implicit wrapper: CObjectWrapper[GtkWidget]): Unit = setObjectProp(c"extra-widget",wrapper.unwrap(v))

  def filter(implicit wrapper: CObjectWrapper[GtkFileFilter]): GtkFileFilter = wrapper.wrap( getObjectProp(c"filter") )
  def filter_=(v: GtkFileFilter)(implicit wrapper: CObjectWrapper[GtkFileFilter]): Unit = setObjectProp(c"filter",wrapper.unwrap(v))

  def localOnly: gboolean = getBooleanProp(c"local-only")
  def localOnly_=(v: gboolean): Unit = setBooleanProp(c"local-only",v)

  def previewWidget(implicit wrapper: CObjectWrapper[GtkWidget]): GtkWidget = wrapper.wrap( getObjectProp(c"preview-widget") )
  def previewWidget_=(v: GtkWidget)(implicit wrapper: CObjectWrapper[GtkWidget]): Unit = setObjectProp(c"preview-widget",wrapper.unwrap(v))

  def previewWidgetActive: gboolean = getBooleanProp(c"preview-widget-active")
  def previewWidgetActive_=(v: gboolean): Unit = setBooleanProp(c"preview-widget-active",v)

  def selectMultiple: gboolean = getBooleanProp(c"select-multiple")
  def selectMultiple_=(v: gboolean): Unit = setBooleanProp(c"select-multiple",v)

  def showHidden: gboolean = getBooleanProp(c"show-hidden")
  def showHidden_=(v: gboolean): Unit = setBooleanProp(c"show-hidden",v)

  def usePreviewLabel: gboolean = getBooleanProp(c"use-preview-label")
  def usePreviewLabel_=(v: gboolean): Unit = setBooleanProp(c"use-preview-label",v)

  def getFilename(): CString = extern
  def setFilename(fname: CString): Unit = extern

  def filename: String = fromCString(getFilename())
  def filename_=(fname: String): Unit = GZone{ implicit z => setFilename(toCString(filename)) }
}

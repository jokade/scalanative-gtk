package gtk

import de.surfice.smacrotools.debug
import glib.utils.GZone
import glib.{gboolean, gint, gulong}
import gobject.{GObject, GSignalReceiver}

import scalanative._
import unsafe._
import cobj._
import scala.scalanative.interop.RefZone

/**
 * Base class for all widgets.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkWidget.html]]
 */
@CObj
class GtkWidget extends GObject with GtkBuildable {

  //final def isNull: Boolean = __ref == null

  /**
   * Destroys a widget.
   *
   * When a widget is destroyed all references it holds on other objects will be released:
   *
   *   - if the widget is inside a container, it will be removed from its parent
   *   - if the widget is a container, all its children will be destroyed, recursively
   *   - if the widget is a top level, it will be removed from the list of top level widgets that GTK+ maintains internally
   *
   * It's expected that all references held on the widget will also be released; you should connect to the “destroy”
   * signal if you hold a reference to widget and you wish to remove it when this function is called. It is not necessary
   * to do so if you are implementing a [[GtkContainer]], as you'll be able to use the GtkContainerClass.remove() virtual function for that
   *
   * It's important to notice that gtk_widget_destroy() will only cause the widget to be finalized if no additional references,
   * acquired using g_object_ref(), are held on it. In case additional references are in place, the widget will be in an "inert"
   * state after calling this function; widget will still point to valid memory, allowing you to release the references you hold,
   * but you may not query the widget's own state.
   *
   * You should typically call this function on top level widgets, and rarely on child widgets.
   */
  @inline def destroy(): Unit = extern

  /**
   * Flags a widget to be displayed.
   * Any widget that isn’t shown will not appear on the screen. If you want to show all the widgets in a container,
   * it’s easier to call [[showAll]] on the container, instead of individually showing the widgets.
   *
   * Remember that you have to show the containers containing a widget, in addition to the widget itself,
   * before it will appear onscreen.
   *
   * When a toplevel container is shown, it is immediately realized and mapped; other shown widgets are realized
   * and mapped when their toplevel container is realized and mapped.
   */
  @inline def show(): Unit = extern

  /**
   * Shows a widget.
   * If the widget is an unmapped toplevel widget (i.e. a [[GtkWindow]] that has not yet been shown),
   * enter the main loop and wait for the window to actually be mapped.
   *
   * Be careful; because the main loop is running, anything can happen during this function.
   */
  @inline def showNow(): Unit = extern

  /**
   * Reverses the effects of [[show]], causing the widget to be hidden (invisible to the user).
   */
  @inline def hide(): Unit = extern

  /**
   * Recursively shows a widget, and any child widgets (if the widget is a container).
   */
  @inline def showAll(): Unit = extern

  /**
   * For widgets that can be “activated” (buttons, menu items, etc.) this function activates them.
   * Activation is what happens when you press Enter on a widget during key navigation.
   * If widget isn't activatable, the function returns false.
   */
  @inline def activate(): gboolean = extern

  /**
   * Determines if the widget is the focus widget within its toplevel.
   * (This does not mean that the “has-focus” property is necessarily set;
   * “has-focus” will only be set if the toplevel widget additionally has the global input focus.)
   */
  @inline def isFocus(): gboolean = extern

  /**
   * Causes this widget to have the keyboard focus for the [[GtkWindow]] it's inside.
   *
   * The widget must be a focusable widget, such as a GtkEntry; something like GtkFrame won’t work.
   * More precisely, it must have the GTK_CAN_FOCUS flag set. Use [[setCanFocus]] to modify that flag.
   * The widget also needs to be realized and mapped. This is indicated by the related signals.
   * Grabbing the focus immediately after creating the widget will likely fail and cause critical warnings.
   */
  @inline def grabFocus(): Unit = extern

  /**
   * Causes this widget to become the default widget.
   * The widget must be able to be a default widget; typically you would ensure this yourself by calling
   * [[setCanDefault]] with true. The default widget is activated when the user presses Enter in a window.
   * Default widgets must be activatable, that is, [[activate]] should affect them.
   * Note that GtkEntry widgets require the “activates-default” property set to true before they activate
   * the default widget when Enter is pressed and the GtkEntry is focused.
   */
  @inline def grabDefault(): Unit = extern

  /**
   * Widgets can be named, which allows you to refer to them from a CSS file.
   * You can apply a style to widgets with a particular name in the CSS file. See the documentation for the CSS syntax
   * (on the same page as the docs for GtkStyleContext).
   *
   * @param name widget name
   */
  @inline def setName(name: CString): Unit = extern

  /**
   * Returns the name of this widget.
   */
  @inline def getName(): CString = extern

  /**
   * Sets the sensitivity of this widget.
   *
   * A widget is sensitive if the user can interact with it. Insensitive widgets are “grayed out” and the user can’t
   * interact with them. Insensitive widgets are known as “inactive”, “disabled”, or “ghosted” in some other toolkits.
   *
   * @param sensitive
   */
  @inline def setSensitive(sensitive: gboolean): Unit = extern

  /**
   * Sets the minimum size of this widget; that is the widget’s size request will be at least width by height.
   *
   * @param width requested width, or -1 to unset
   * @param height requested height, or -1 to unset
   */
  @inline def setSizeRequest(width: gint, height: gint): Unit = extern

  /**
   * Whether to exapnd in both directions. Setting this sets both [[hexpand]] and [[vexpand]].
   */
  def expand: gboolean = getBooleanProp(c"expand")
  def expand_=(setting: gboolean): Unit = setBooleanProp(c"expand",setting)

  /**
   * Whether to expand horizontally.
   */
  def hexpand: gboolean = getBooleanProp(c"hexpand")
  def hexpand_=(setting: gboolean): Unit = setBooleanProp(c"hexpand",setting)

  /**
   * Whether to expand vertically
   */
  def vexpand: gboolean = getBooleanProp(c"vexpand")
  def vexpand_=(setting: gboolean): Unit = setBooleanProp(c"vexpand",setting)

  /**
   * Returns the max. margin on any side.
   */
  def margin: gint = getIntProp(c"margin")

  /**
   * Sets the margin for all four sides.
   * @param setting
   */
  def margin_=(setting: gint): Unit = setIntProp(c"margin",setting)

  /**
   * Margin on the bottom side of this widget.
   */
  def marginBottom: gint = getIntProp(c"margin-bottom")
  def marginBottom_=(margin: gint): Unit = setIntProp(c"margin-bottom",margin)

  /**
   * Margin on the top side of this widget.
   */
  def marginTop: gint = getIntProp(c"margin-top")
  def marginTop_=(margin: gint): Unit = setIntProp(c"margin-top",margin)

  /**
   * Margin on end of this widget, horizontally.
   */
  def marginEnd: gint = getIntProp(c"margin-end")
  def marginEnd_=(margin: gint): Unit = setIntProp(c"margin-end",margin)

  /**
   * Margin on start of this widget, horizontally.
   */
  def marginStart: gint = getIntProp(c"margin-start")
  def marginStart_=(margin: gint): Unit = setIntProp(c"margin-start",margin)


  /**
   * How to distribute horizontal space if this widget gets extra space.
   */
  def halign: GtkAlign = getIntProp(c"halign")
  def halign_=(align: GtkAlign): Unit = setIntProp(c"halign",align)

  /**
   * Whether the widget responds to input
   */
  def sensitive: gboolean = getBooleanProp(c"sensitive")
  def sensitive_=(setting: gboolean): Unit = setBooleanProp(c"sensitive",setting)

  /**
   * Tooltip text for this widget
   */
  def tooltipText: String = getStringProp(c"tooltip-text")
  def tooltipText_=(tooltip: String): Unit = setStringProp(c"tooltip-text",tooltip)

  def onDestroy(handler: Function0[Unit])(implicit refZone: RefZone): gulong = connect0(c"destroy",handler)
}

object GtkWidget {
//  @inline def destroy: CFunctionPtr1[CObj.Ref[Byte],Unit] = CFunctionPtr.fromFunction1(__ext.gtk_widget_destroy)

//  /**
//   * Fetches the widget with the specified ID from the provided GtkBuilder.
//   *
//   * @param gtkBuilder GtkBuilder that holds the widget.
//   * @param id ID of the widget to get
//   * @param wrapper
//   * @tparam T Type of the widget to load
//   */
//  def fromBuilder[T<:GtkWidget](gtkBuilder: GtkBuilder, id: CString)(implicit wrapper: CObjectWrapper[T]): T =
//    wrapper.wrap(gtkBuilder.getObjectPtr(id))

//  def fromXmlString[T<:GtkWidget](xml: CString, id: CString)(implicit wrapper: CObjectWrapper[T]): T = {
//    val b = GtkBuilder.fromString(xml,-1)
//    val w = wrapper.wrap(b.getObjectPtr(id))
//    b.free()
//    w
//  }

//  def fromXmlString[T<:GtkWidget](xml: String, id: String)(implicit wrapper: CObjectWrapper[T]): T = GZone{ implicit z =>
//    fromXmlString(toCString(xml),toCString(id))(wrapper)
//  }

//  def fromXmlFile[T<:GtkWidget](filename: CString, id: CString)(implicit wrapper: CObjectWrapper[T]): T = {
//    val b = GtkBuilder.fromFile(filename)
//    val w = wrapper.wrap(b.getObjectPtr(id))
//    b.free()
//    w
//  }
}


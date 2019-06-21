package gtk

import glib.gboolean
import glib.utils.GZone

import scala.scalanative._
import unsafe._
import cobj._

/**
 * A stacking container.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkStack.html]]
 */
@CObj
class GtkStack extends GtkContainer {
  /**
   * Adds a child to the stack.
   *
   * @param child The child to add.
   * @param name Name for the child.
   */
  def addNamed(child: GtkWidget, name: CString): Unit = extern

  /**
   * Adds a child to the stack, using the specified `title` in the stack switcher.
   *
   * @param child The child to add
   * @param name Name for the child
   * @param title Title used in the stack switcher
   */
  def addTitled(child: GtkWidget, name: CString, title: CString): Unit = extern

  /**
   * Adds a child to the stack, using the specified `title` in the stack switcher.
   *
   * @param child The child to add
   * @param name Name for the child
   * @param title Title used in the stack switcher
   */
  def addTitled(child: GtkWidget, name: String, title: String): Unit = GZone{ implicit z =>
    addTitled(child,toCString(name),toCString(title))
  }

  def getVisibleChild(): GtkWidget = extern

  def setVisibleChild(child: GtkWidget): Unit = extern

  def hhomogeneous: gboolean = getBooleanProp(c"hhomogeneous")
  def hhomogeneous_=(f: gboolean) : Unit = setBooleanProp(c"hhomogeneous",f)

  def vhomogeneous: gboolean = getBooleanProp(c"vhomogeneous")
  def vhomogeneous_=(f: gboolean) : Unit = setBooleanProp(c"vhomogeneous",f)

  def homogeneous: gboolean = getBooleanProp(c"homogeneous")
  def homogeneous_=(f: gboolean) : Unit = setBooleanProp(c"homogeneous",f)
}

object GtkStack {
  @name("gtk_stack_new")
  def apply(): GtkStack = extern
}

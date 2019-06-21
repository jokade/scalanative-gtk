package gtk

import glib.{GSList, gpointer, gssize}
import glib.utils.GZone
import gobject.{GCallback, GObject}

import scalanative._
import unsafe._
import cobj._

/**
 * Build an interface from an XML UI definition.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkBuilder.html]]
 */
@CObj
class GtkBuilder extends GObject {

  /**
   * Gets the object named `name`. Note that this function does not increment the reference count of the returned object.
   *
   * @note Due to the wrapping mechanism of used by scalanative-gtk, the returned object will actually
   *       always be an [[GObject]]! For example, if you request an object you know to be a Gtk window,
   *       it will still be returned as GObject, not as GtkWindow. Hence, the following will fail:
   * {{{
   * val win = builder.getObject(c"window") match {
   *   case win: GtkWindow => win
   * }
   * }}}
   *       Instead you must wrap the raw Gtk pointer returned by [[getObjectPtr]] explicitly in the type you expect it to be:
   * {{{
   *   val win = new GtkWindow( builder.getObjectPtr(c"window") )
   * }}}
   *
   * @param name name of the object to get
   * @return The requested object, or null
   */
//  @inline def getObject(name: CString): GObject = extern

  /**
   * Returns the raw Gtk pointer to the named object, or null, if the object is not defined in this GtkBuilder.
   * Note that this function does not increment the reference count of the returned object.
   *
   * @param name name of the object to get
   * @return The requested object pointer, or null.
   */
  @name("gtk_builder_get_object")
  @inline def getObjectPtr(name: CString): Ptr[Byte] = extern

  def getObjects[T<:GObject]()(implicit wrapper: CObjectWrapper[T]): GSList[T] = extern

  /**
   * Returns the GObject with the specified ID, or null.
   *
   * @param id ID of the object to be retrieved.
   * @param wrapper
   * @tparam T Type of the object to be retrieved
   */
  def getObject[T<:GObject](id: CString)(implicit wrapper: CObjectWrapper[T]): T = wrapper.wrap(getObjectPtr(id))

  def getObject[T<:GObject](id: String)(implicit wrapper: CObjectWrapper[T]): T = GZone { implicit z =>
    getObject[T](toCString(id))
  }

  /**
   * Adds the `callbackSymbol` to the scope of this builder under the given `callbackName`.
   *
   * Using this method overrides the behaviour of [[connectSignals]] for any callback symbols that are added.
   *
   * @param callbackName The name of the callback, as expected in the XML
   * @param callbackSymbol The callback pointer
   */
  @inline def addCallbackSymbol(callbackName: CString, callbackSymbol: GCallback): Unit = extern

  /**
   * This method is a simpler varaition of [[connectSignalsFull]].
   * It uses symbols explicitly added to this builder with prior calls to [[addCallbackSymbol]].
   * In the case that symbols are not explicitly added, it uses GModule's introspective features to
   * look at the application's symbol table.
   *
   * @param userData user data to pass back with all signals
   */
  @inline def connectSignals(userData: gpointer): Unit = extern

}

object GtkBuilder {
  /**
   * Builds the GtkBuilder UI definition in the file filename.
   * If there is an error opening the file or parsing the description then the program will be aborted.
   * You should only ever attempt to parse user interface descriptions that are shipped as part of your program.
   *
   * @param filename filename of user interface description file
   * @return a GtkBuilder containing the described interface
   */
  @name("gtk_builder_new_from_file")
  def fromFile(filename: CString): GtkBuilder = extern

  def fromFile(filename: String): GtkBuilder = GZone{ implicit z => fromFile(toCString(filename)) }

  @name("gtk_builder_new_from_string")
  def fromString(string: CString, length: gssize): GtkBuilder = extern

  def fromString(string: String): GtkBuilder = GZone{ implicit z => fromString(toCString(string),-1) }
}

package gtk

import glib.gint
import glib.utils.GZone

import scalanative._
import unsafe._
import cobj._

/**
 * A simple variant of [[GtkComboBox]] that hides the model-view complexity for simple text-only use cases.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html]]
 */
@CObj
class GtkComboBoxText extends GtkComboBox {

  @inline def append(id: CString, text: CString): Unit = extern
  @inline def append(id: String, text: String): Unit = GZone{ implicit z =>
    append(if(id==null) null else toCString(id), toCString(text))
  }

  @inline def prepend(id: CString, text: CString): Unit = extern
  @inline def prepend(id: String, text: String): Unit = GZone{ implicit z =>
    prepend(if(id==null) null else toCString(id), toCString(text))
  }

  @inline def insert(position: gint, id: CString, text: CString): Unit = extern
  @inline def insert(position: gint, id: String, text: String): Unit = GZone{ implicit z =>
    insert(position, if(id==null) null else toCString(id), toCString(id))
  }

  @inline def appendText(text: String): Unit = append(null,text)
  @inline def prependText(text: String): Unit = prepend(null,text)
  @inline def insertText(position: gint, text: String): Unit = insert(position,null,text)

  @inline def remove(position: gint): Unit = extern
  @inline def removeAll(): Unit = extern
}

object GtkComboBoxText {
  @name("gtk_combo_box_text_new")
  def apply(): GtkComboBoxText = extern
}

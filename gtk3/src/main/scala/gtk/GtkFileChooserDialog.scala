package gtk

import de.surfice.smacrotools.debug
import glib.utils.GZone

import scalanative._
import unsafe._
import cobj._

/**
 * A dialog box suitable for use with “File/Open” or “File/Save as” commands.
 *
 * @see [[https://developer.gnome.org/gtk3/stable/GtkFileChooserDialog.html]]
 */
@CObj
class GtkFileChooserDialog extends GtkDialog with GtkFileChooser {
}

object GtkFileChooserDialog {
  @name("gtk_file_chooser_dialog_new")
  def apply(title: CString, parent: GtkWindow, action: GtkFileChooserAction,
            fstBtnText: CString, firstBtnId: CString,
            sndBtnText: CString, sndBtnId: CString,
            thdBtnText: CString, thdBtnId: CString,
            end: Ptr[Byte]): GtkFileChooserDialog = extern


  def openFile(title: String, parent: GtkWindow,
               okBtn: String = "_Open", cancelBtn: String = "_Cancel",
               filePatterns: Seq[String] = Nil): GtkFileChooserDialog = Zone{ implicit z =>
    val dialog = apply(toCString(title), parent, GtkFileChooserAction.OPEN,
          toCString(cancelBtn),GtkResponse.CANCEL.toPtr[Byte],
          toCString(okBtn),GtkResponse.ACCEPT.toPtr[Byte],
          null,null,
          null)
    val filter = GtkFileFilter()
    filePatterns.foreach(p => filter.addPattern(p))
    dialog.filter = filter
    dialog
  }
//  @name("gtk_file_chooser_dialog_new")
//  def apply(title: CString, parent: GtkWindow, action: GtkFileChooserAction,
//            buttons: CVarArgList): GtkFileChooserDialog = extern
//
//  def apply(title: CString, parent: GtkWindow, action: GtkFileChooserAction,
//            buttons: CVarArg*): GtkFileChooserDialog = GZone{ implicit z =>
//    apply(title,parent,action, toCVarArgList(buttons.toSeq))
//  }
}
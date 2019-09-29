package gtk.ui

import gtk.{GtkAlign, GtkEntry, GtkGrid, GtkLabel}

trait UIGrid extends GtkGrid with UIContainer {

}

trait UIGridTools extends UIGrid {

  private def makeLabel(label: String): GtkLabel = {
    val lbl = GtkLabel()
    lbl.halign = GtkAlign.END
    lbl.xalign = 1.0f
    lbl.yalign = 0f
    lbl.setMarkup(s"""<span weight="semibold">$label:</span>""")
    lbl
  }

  def addLabelEntry(label: String, left: Int, top: Int, width: Int = 1): GtkEntry = {
    attach(makeLabel(label),left,top,1,1)
    val entry = GtkEntry()
    attach(entry,left+1,top,width,1)
    entry
  }

  def addLabelValue(label: String, left: Int, top: Int, width: Int = 1, wrap: Boolean = false): GtkLabel = {
    attach(makeLabel(label),left,top,1,1)
    val entry = GtkLabel()
    entry.wrap = wrap
    entry.xalign = 0.0f
    entry.setSelectable(true)
    attach(entry,left+1,top,width,1)
    entry
  }
}

object UIGridTools {
  class Impl(__ptr: GtkPtr) extends UIGridToolsComponent(__ptr)
  def apply(): UIGridTools = new Impl(GtkGrid.__ext.gtk_grid_apply())
}

/**
 * Base class for components that have a [[GtkGrid]] layout.
 * @param __ptr
 */
abstract class UIGridComponent(__ptr: GtkPtr) extends GtkGrid(__ptr) with UIGrid {
}

//object UIGridComponent {
//  def create(): GtkPtr = GtkGrid.__ext.gtk_grid_apply()
//}

/**
 * Base class for components that extend [[GtkGrid]] with [[UIGridTools]]
 * @param __ptr
 */
abstract class UIGridToolsComponent(__ptr: GtkPtr) extends UIGridComponent(__ptr) with UIGridTools {
  def this(grid: GtkGrid) = this(grid.__ptr)
  def this() = this(GtkGrid.__ext.gtk_grid_apply())
}

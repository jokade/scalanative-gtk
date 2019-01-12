---
title: Status of Gtk+ bindings
keywords:
summary:
sidebar: sngtk_sidebar
permalink: sngtk_status_gtk.html
folder: sngtk
datatable: true
missing: <i class="fa fa-close" style="color:red"></i>
ok: <i class="fa fa-check" style="color:green"></i>
partial: <i class="fa fa-adjust" style="color:orange"></i>
---

<div class="datatable-begin"></div>

| Header                      | Status           | Scala modules | Notes |
|-----------------------------|------------------|---------------|-------|
| gtk-a11y.h                  | {{page.missing}} |               |       |
| gtk-autocleanups.h          | {{page.missing}} |               |       |
| gtkaboutdialog.h            | {{page.missing}} |               |       |
| gtkaccelgroup.h             | {{page.missing}} |               |       |
| gtkaccellabel.h             | {{page.missing}} |               |       |
| gtkaccelmap.h               | {{page.missing}} |               |       |
| gtkaccessible.h             | {{page.missing}} |               |       |
| gtkactionable.h             | {{page.missing}} |               |       |
| gtkactionbar.h              | {{page.missing}} |               |       |
| gtkadjustment.h             | {{page.missing}} |               |       |
| gtkappchooser.h             | {{page.missing}} |               |       |
| gtkappchooserbutton.h       | {{page.missing}} |               |       |
| gtkappchooserdialog.h       | {{page.missing}} |               |       |
| gtkappchooserwidget.h       | {{page.missing}} |               |       |
| gtkapplication.h            | {{page.missing}} |               |       |
| gtkapplicationwindow.h      | {{page.missing}} |               |       |
| gtkaspectframe.h            | {{page.missing}} |               |       |
| gtkassistant.h              | {{page.missing}} |               |       |
| gtkbbox.h                   | {{page.missing}} |               |       |
| gtkbin.h                    | {{page.missing}} |               |       |
| gtkbindings.h               | {{page.missing}} |               |       |
| gtkborder.h                 | {{page.missing}} |               |       |
| gtkbox.h                    | {{page.missing}} |               |       |
| gtkbuildable.h              | {{page.missing}} |               |       |
| gtkbuilder.h                | {{page.missing}} |               |       |
| gtkbutton.h                 | {{page.missing}} |               |       |
| gtkcalendar.h               | {{page.missing}} |               |       |
| gtkcellarea.h               | {{page.missing}} |               |       |
| gtkcellareabox.h            | {{page.missing}} |               |       |
| gtkcellareacontext.h        | {{page.missing}} |               |       |
| gtkcelleditable.h           | {{page.missing}} |               |       |
| gtkcelllayout.h             | {{page.missing}} |               |       |
| gtkcellrenderer.h           | {{page.missing}} |               |       |
| gtkcellrendereraccel.h      | {{page.missing}} |               |       |
| gtkcellrenderercombo.h      | {{page.missing}} |               |       |
| gtkcellrendererpixbuf.h     | {{page.missing}} |               |       |
| gtkcellrendererprogress.h   | {{page.missing}} |               |       |
| gtkcellrendererspin.h       | {{page.missing}} |               |       |
| gtkcellrendererspinner.h    | {{page.missing}} |               |       |
| gtkcellrenderertext.h       | {{page.missing}} |               |       |
| gtkcellrenderertoggle.h     | {{page.missing}} |               |       |
| gtkcellview.h               | {{page.missing}} |               |       |
| gtkcheckbutton.h            | {{page.missing}} |               |       |
| gtkcheckmenuitem.h          | {{page.missing}} |               |       |
| gtkclipboard.h              | {{page.missing}} |               |       |
| gtkcolorbutton.h            | {{page.missing}} |               |       |
| gtkcolorchooser.h           | {{page.missing}} |               |       |
| gtkcolorchooserdialog.h     | {{page.missing}} |               |       |
| gtkcolorchooserwidget.h     | {{page.missing}} |               |       |
| gtkcolorutils.h             | {{page.missing}} |               |       |
| gtkcombobox.h               | {{page.missing}} |               |       |
| gtkcomboboxtext.h           | {{page.missing}} |               |       |
| gtkcontainer.h              | {{page.missing}} |               |       |
| gtkcssprovider.h            | {{page.missing}} |               |       |
| gtkcsssection.h             | {{page.missing}} |               |       |
| gtkdebug.h                  | {{page.missing}} |               |       |
| gtkdialog.h                 | {{page.partial}} | `GtkDialog`   |       |
| gtkdnd.h                    | {{page.missing}} |               |       |
| gtkdragdest.h               | {{page.missing}} |               |       |
| gtkdragsource.h             | {{page.missing}} |               |       |
| gtkdrawingarea.h            | {{page.missing}} |               |       |
| gtkeditable.h               | {{page.missing}} |               |       |
| gtkentry.h                  | {{page.missing}} |               |       |
| gtkentrybuffer.h            | {{page.missing}} |               |       |
| gtkentrycompletion.h        | {{page.missing}} |               |       |
| gtkenums.h                  | {{page.missing}} |               |       |
| gtkeventbox.h               | {{page.missing}} |               |       |
| gtkeventcontroller.h        | {{page.missing}} |               |       |
| gtkexpander.h               | {{page.missing}} |               |       |
| gtkfilechooser.h            | {{page.missing}} |               |       |
| gtkfilechooserbutton.h      | {{page.missing}} |               |       |
| gtkfilechooserdialog.h      | {{page.missing}} |               |       |
| gtkfilechoosernative.h      | {{page.missing}} |               |       |
| gtkfilechooserwidget.h      | {{page.missing}} |               |       |
| gtkfilefilter.h             | {{page.missing}} |               |       |
| gtkfixed.h                  | {{page.missing}} |               |       |
| gtkflowbox.h                | {{page.missing}} |               |       |
| gtkfontbutton.h             | {{page.missing}} |               |       |
| gtkfontchooser.h            | {{page.missing}} |               |       |
| gtkfontchooserdialog.h      | {{page.missing}} |               |       |
| gtkfontchooserwidget.h      | {{page.missing}} |               |       |
| gtkframe.h                  | {{page.ok}}      | `GtkFrame`    |       |
| gtkgesture.h                | {{page.missing}} |               |       |
| gtkgesturedrag.h            | {{page.missing}} |               |       |
| gtkgesturelongpress.h       | {{page.missing}} |               |       |
| gtkgesturemultipress.h      | {{page.missing}} |               |       |
| gtkgesturepan.h             | {{page.missing}} |               |       |
| gtkgesturerotate.h          | {{page.missing}} |               |       |
| gtkgesturesingle.h          | {{page.missing}} |               |       |
| gtkgestureswipe.h           | {{page.missing}} |               |       |
| gtkgesturezoom.h            | {{page.missing}} |               |       |
| gtkglarea.h                 | {{page.missing}} |               |       |
| gtkgrid.h                   | {{page.missing}} |               |       |
| gtkheaderbar.h              | {{page.missing}} |               |       |
| gtkicontheme.h              | {{page.missing}} |               |       |
| gtkiconview.h               | {{page.missing}} |               |       |
| gtkimage.h                  | {{page.partial}} | `GtkImage`    |       |
| gtkimcontext.h              | {{page.missing}} |               |       |
| gtkimcontextinfo.h          | {{page.missing}} |               |       |
| gtkimcontextsimple.h        | {{page.missing}} |               |       |
| gtkimmodule.h               | {{page.missing}} |               |       |
| gtkimmulticontext.h         | {{page.missing}} |               |       |
| gtkinfobar.h                | {{page.missing}} |               |       |
| gtkinvisible.h              | {{page.missing}} |               |       |
| gtklabel.h                  | {{page.missing}} |               |       |
| gtklayout.h                 | {{page.missing}} |               |       |
| gtklevelbar.h               | {{page.missing}} |               |       |
| gtklinkbutton.h             | {{page.missing}} |               |       |
| gtklistbox.h                | {{page.missing}} |               |       |
| gtkliststore.h              | {{page.partial}} | `GtkListStore`  |       |
| gtklockbutton.h             | {{page.missing}} |               |       |
| gtkmain.h                   | {{page.missing}} |               |       |
| gtkmenu.h                   | {{page.missing}} |               |       |
| gtkmenubar.h                | {{page.missing}} |               |       |
| gtkmenubutton.h             | {{page.missing}} |               |       |
| gtkmenuitem.h               | {{page.missing}} |               |       |
| gtkmenushell.h              | {{page.missing}} |               |       |
| gtkmenutoolbutton.h         | {{page.missing}} |               |       |
| gtkmessagedialog.h          | {{page.partial}} | `GtkMessageDialog`|       |
| gtkmodelbutton.h            | {{page.missing}} |               |       |
| gtkmodules.h                | {{page.missing}} |               |       |
| gtkmountoperation.h         | {{page.missing}} |               |       |
| gtknativedialog.h           | {{page.missing}} |               |       |
| gtknotebook.h               | {{page.ok}}      | `GtkNotebook` |       |
| gtkoffscreenwindow.h        | {{page.missing}} |               |       |
| gtkorientable.h             | {{page.missing}} |               |       |
| gtkoverlay.h                | {{page.ok}}      | `GtkOverlay`  |       |
| gtkpadcontroller.h          | {{page.missing}} |               |       |
| gtkpagesetup.h              | {{page.missing}} |               |       |
| gtkpaned.h                  | {{page.partial}} | `GtkPaned`    |       |
| gtkpapersize.h              | {{page.missing}} |               |       |
| gtkplacessidebar.h          | {{page.missing}} |               |       |
| gtkplug.h                   | {{page.missing}} |               |       |
| gtkpopover.h                | {{page.missing}} |               |       |
| gtkpopovermenu.h            | {{page.missing}} |               |       |
| gtkprintcontext.h           | {{page.missing}} |               |       |
| gtkprintoperation.h         | {{page.missing}} |               |       |
| gtkprintoperationpreview.h  | {{page.missing}} |               |       |
| gtkprintsettings.h          | {{page.missing}} |               |       |
| gtkprogressbar.h            | {{page.missing}} |               |       |
| gtkradiobutton.h            | {{page.missing}} |               |       |
| gtkradiomenuitem.h          | {{page.missing}} |               |       |
| gtkradiotoolbutton.h        | {{page.missing}} |               |       |
| gtkrange.h                  | {{page.missing}} |               |       |
| gtkrecentchooser.h          | {{page.missing}} |               |       |
| gtkrecentchooserdialog.h    | {{page.missing}} |               |       |
| gtkrecentchoosermenu.h      | {{page.missing}} |               |       |
| gtkrecentchooserwidget.h    | {{page.missing}} |               |       |
| gtkrecentfilter.h           | {{page.missing}} |               |       |
| gtkrecentmanager.h          | {{page.missing}} |               |       |
| gtkrender.h                 | {{page.missing}} |               |       |
| gtkrevealer.h               | {{page.missing}} |               |       |
| gtkscale.h                  | {{page.missing}} |               |       |
| gtkscalebutton.h            | {{page.missing}} |               |       |
| gtkscrollable.h             | {{page.missing}} |               |       |
| gtkscrollbar.h              | {{page.missing}} |               |       |
| gtkscrolledwindow.h         | {{page.missing}} |               |       |
| gtksearchbar.h              | {{page.missing}} |               |       |
| gtksearchentry.h            | {{page.ok}}      | `GtkSearchEntry` |       |
| gtkselection.h              | {{page.missing}} |               |       |
| gtkseparator.h              | {{page.missing}} |               |       |
| gtkseparatormenuitem.h      | {{page.missing}} |               |       |
| gtkseparatortoolitem.h      | {{page.missing}} |               |       |
| gtksettings.h               | {{page.missing}} |               |       |
| gtkshortcutlabel.h          | {{page.missing}} |               |       |
| gtkshortcutsgroup.h         | {{page.missing}} |               |       |
| gtkshortcutssection.h       | {{page.missing}} |               |       |
| gtkshortcutsshortcut.h      | {{page.missing}} |               |       |
| gtkshortcutswindow.h        | {{page.missing}} |               |       |
| gtkshow.h                   | {{page.missing}} |               |       |
| gtksizegroup.h              | {{page.missing}} |               |       |
| gtksizerequest.h            | {{page.missing}} |               |       |
| gtksocket.h                 | {{page.missing}} |               |       |
| gtkspinbutton.h             | {{page.missing}} |               |       |
| gtkspinner.h                | {{page.ok}}      |`GtkSpinner`   |       |
| gtkstack.h                  | {{page.missing}} |               |       |
| gtkstacksidebar.h           | {{page.missing}} |               |       |
| gtkstackswitcher.h          | {{page.missing}} |               |       |
| gtkstatusbar.h              | {{page.missing}} |               |       |
| gtkstylecontext.h           | {{page.missing}} |               |       |
| gtkstyleprovider.h          | {{page.missing}} |               |       |
| gtkswitch.h                 | {{page.missing}} |               |       |
| gtktestutils.h              | {{page.missing}} |               |       |
| gtktextattributes.h         | {{page.missing}} |               |       |
| gtktextbuffer.h             | {{page.partial}} | `GtkTextBuffer`  |       |
| gtktextbufferrichtext.h     | {{page.missing}} |               |       |
| gtktextchild.h              | {{page.missing}} |               |       |
| gtktextdisplay.h            | {{page.missing}} |               |       |
| gtktextiter.h               | {{page.missing}} |               |       |
| gtktextlayout.h             | {{page.missing}} |               |       |
| gtktextmark.h               | {{page.ok}}      | `GtkTextMark` |       |
| gtktexttag.h                | {{page.missing}} |               |       |
| gtktexttagtable.h           | {{page.missing}} |               |       |
| gtktextview.h               | {{page.partial}} | `GtkTextView` |       |
| gtktogglebutton.h           | {{page.missing}} |               |       |
| gtktoggletoolbutton.h       | {{page.missing}} |               |       |
| gtktoolbar.h                | {{page.missing}} |               |       |
| gtktoolbutton.h             | {{page.missing}} |               |       |
| gtktoolitem.h               | {{page.missing}} |               |       |
| gtktoolitemgroup.h          | {{page.missing}} |               |       |
| gtktoolpalette.h            | {{page.missing}} |               |       |
| gtktoolshell.h              | {{page.missing}} |               |       |
| gtktooltip.h                | {{page.missing}} |               |       |
| gtktreednd.h                | {{page.missing}} |               |       |
| gtktreemodel.h              | {{page.partial}} | `GtkTreeModel`|       |
| gtktreemodelfilter.h        | {{page.missing}} |               |       |
| gtktreemodelsort.h          | {{page.missing}} |               |       |
| gtktreeselection.h          | {{page.partial}} | `GtkTreeSelection` |       |
| gtktreesortable.h           | {{page.missing}} |               |       |
| gtktreestore.h              | {{page.missing}} |               |       |
| gtktreeview.h               | {{page.partial}} | `GtkTreeView` |       |
| gtktreeviewcolumn.h         | {{page.missing}} |               |       |
| gtktypebuiltins.h           | {{page.missing}} |               |       |
| gtktypes.h                  | {{page.missing}} |               |       |
| gtkversion.h                | {{page.missing}} |               |       |
| gtkviewport.h               | {{page.missing}} |               |       |
| gtkvolumebutton.h           | {{page.missing}} |               |       |
| gtkwidget.h                 | {{page.missing}} |               |       |
| gtkwidgetpath.h             | {{page.missing}} |               |       |
| gtkwindow.h                 | {{page.partial}} |               |       |
| gtkwindowgroup.h            | {{page.missing}} |               |       |
| gtkx-autocleanups.h         | {{page.missing}} |               |       |
| gtkx.h                      | {{page.missing}} |               |       |
          
<div class="datatable-end"></div>

{% include links.html %}

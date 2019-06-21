---
title: "SNGtk+: scala-native bindings for Glib, Gtk+, ..."
keywords: scala-native gtk glib
tags: 
sidebar: sngtk_sidebar
permalink: index.html
summary:
toc: false
---
Experimental [scala-native](https://github.com/scala-native/scala-native) bindings for [Glib / Gtk+](http://www.gtk.org).

{% include warning.html content="This project is in an early experimental stage. Most of the bindings
are still missing. The API and especially the semantics for GObject access in Scala may change any time!" %}

## Introduction
SNGtk+ provides object-oriented Scala Native bindings for Gtk+ (and GLib, ...).
Here's a simple example:

<div class="container-fluid">
  <div class="row">
    <div class="col col-md-4 col-md-offset-4 text-center">
    {% include image.html file="index/hello.png" %}
    </div>
  </div>
</div>

{% highlight scala %}
import gtk._
import scalanative.unsigned._

object Main {
  import scala.scalanative.interop.RefZone.Implicits.None

  var entry: GtkEntry = _
  var greeting: GtkLabel = _


  def main(args: Array[String]): Unit = {

    Gtk.init(args)

    val win = GtkWindow()
    win.title = "Greetings"
    win.setBorderWidth(10.toUInt)

    win.onDestroy(exit)

    val grid = GtkGrid()
    grid.setColumnSpacing(5.toUInt)
    grid.setRowSpacing(10.toUInt)

    val label = GtkLabel("Name:")
    grid.attach(label,0,0,1,1)

    entry = GtkEntry()
    grid.attach(entry,1,0,1,1)

    val button = GtkButton.withLabel("Greet!")
    grid.attach(button,0,1,2,1)

    greeting = GtkLabel("")
    greeting.setSizeRequest(-1,30)
    grid.attach(greeting,0,2,2,1)

    button.onClicked(greet)

    win.add(grid)
    win.showAll()


    Gtk.main()

  }

  def exit(): Unit = {
    Gtk.mainQuit()
  }

  def greet(): Unit = {
    greeting.setMarkup(s"<span size='large'>Hello ${entry.text}!</span>")
  }

}

{% endhighlight %}

{% include links.html %}

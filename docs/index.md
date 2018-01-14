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
import scalanative.native._
import gtk._

object Main {

  var entry: GtkEntry = _
  var greeting: GtkLabel = _

  def main(args: Array[String]): Unit = {

    Gtk.init(args)

    val win = new GtkWindow
    win.setTitle(c"Greetings")
    win.setBorderWidth(10.toUInt)
    win.connect(c"destroy",CFunctionPtr.fromFunction0(destroy),null)

    val grid = new GtkGrid
    grid.setColumnSpacing(5.toUInt)
    grid.setRowSpacing(10.toUInt)

    val label = new GtkLabel(c"Name:")
    grid.attach(label,0,0,1,1)

    entry = new GtkEntry
    grid.attach(entry,1,0,1,1)

    val button = GtkButton.withLabel(c"Greet!")
    grid.attach(button,0,1,2,1)

    greeting = new GtkLabel(c"")
    greeting.setSizeRequest(-1,30)
    grid.attach(greeting,0,2,2,1)

    button.connect(c"clicked",CFunctionPtr.fromFunction0(greet),null)

    win.add(grid)
    win.showAll()


    Gtk.main()

  }

  def destroy(): Unit = {
    Gtk.mainQuit()
  }

  def greet(): Unit = {
    greeting.setMarkup(s"<span size='large'>Hello ${entry.text}!</span>")
  }
}

{% endhighlight %}
{::comment}
Here's a comparison of Objective-C syntax with the corresponding SNCocoa idiom:

<div class="container-fluid comparison">
  <div class="row">
    <div class="col col-md-6">
      <h4 style="text-align:center">Objective-C</h4>
    </div>
    <div class="col col-md-6">
      <h4 style="text-align:center">Scala</h4>
    </div>
  </div>
  
  <div class="row">
    <div class="col col-md-12"><h5>Method Calls</h5></div>
    <div class="col col-md-6">
    {% highlight objc %}
    [[NSNumber alloc] initWithInt:42]; {% endhighlight %}
    </div>
    
    <div class="col col-md-6">
    {% highlight scala %}
    NSNumber.alloc().initWithInt(42){% endhighlight %}
    </div>
  </div>
  
  <div class="row">
    <div class="col col-md-12"><h5>Numbers and Strings</h5></div>
    <div class="col col-md-6">
    {% highlight objc %}
    NSNumber *theNumber = @123;
    
    NSString *greeting = @"Hello Cocoa!"; {% endhighlight %}
    </div>
    
    <div class="col col-md-6">
    {% highlight scala %}
    val theNumber = @@(123)
    
    val greeting = ns"Hello Cocoa!" {% endhighlight %}
    </div>
  </div>
  
  <div class="row">
    <div class="col col-md-12"><h5>Arrays</h5></div>
    <div class="col col-md-6">
    {% highlight objc %}
    NSArray *theArray = @[@"one", @"two"];
    
    NSString *oneString = theArray[0]; {% endhighlight %}
    </div>
    
    <div class="col col-md-6">
    {% highlight scala %}
    val theArray = NSArray(ns"one", ns"two")
    
    val oneString = theArray(0) {% endhighlight %}
    </div>
  </div>
  
  <div class="row">
    <div class="col col-md-12"><h5>Dictionaries</h5></div>
    <div class="col col-md-6">
    {% highlight objc %}
    NSDictionary *theDict = @{
      @"greeting": @"Hello",
      @"farewell": @"Goodbye"
    };
    
    NSString *greeting = theDict[@"greeting"]; {% endhighlight %}
    </div>
    
    <div class="col col-md-6">
    {% highlight scala %}
    val theDict = NSDictionary(
      ns"greeting" -> ns"Hello",
      ns"farewell" -> ns"Goodbye"
    )
    
    val greeting = theDict(ns"greeting") {% endhighlight %}
    </div>
  </div>
   
  <div class="row">
    <div class="col col-md-12"><h5>Defining an Objective-C Class in Scala</h5></div>
    <div class="col col-md-12">
    {% highlight scala %}
    // The constructor argument is the Objective-C proxy for the created Scala instance
    @ScalaObjC
    class AppDelegate(self: NSObject) extends NSApplicationDelegate {
      //protected and private fields are not accessible from Objective-C
      private var _clickCount = 0
      
      // all public vars, vals, and Scala getter/setter are exposed as Objective-C properties
      var window: NSObject = _             // IBOutlet connected in the xib to the application window
      var clickCountView: NSTextField = _  // another IBOutlet
        
      // all public methods are exposed to Objective-C using the normal selector semantics
      def takeClick(id: NSObject): Unit = {  // IBAction connected in xib to a button
        _clickCount += 1
        updateView()
      }
      
      override def applicationDidFinishLaunching(notification: NSNotification): Unit = {
         updateView()
      }
        
      // protected and private methods are not accessible from Objective-C
      private def updateView(): Unit = {
        clickCountView.setIntegerValue(_clickCount)
      } 
    }
    {% endhighlight %}
    </div>
  </div>
  
</div>
{:/comment}
{% include links.html %}

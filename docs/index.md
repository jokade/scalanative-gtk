---
title: "SNGtk+: scala-native bindings for Glib, Gtk+, ..."
keywords: scala-native gtk glib
tags: 
sidebar: sngtk_sidebar
permalink: index.html
summary:
toc: false
---
Experimental [scala-native](https://github.com/scala-native/scala-native) bindings for Glib / Gtk+.

{% include warning.html content="This project is in an early experimental stage. Most of the bindings
are still missing. The API and especially the semantics for GObject access in Scala may change any time!" %}

## Introduction
SNCocoa is a bridge for writing Cocoa (and possibly Cocoa Touch) applications with Scala Native.
To this end it provides:
  * a set of macros that transform the message-passing style of Objective-C in idiomatic Scala (method calls on objects)
    and support defining Objective-C classes in Scala,
  * bindings to Cocoa (Foundation, AppKit) and other core frameworks,
  * wrappers and decorators for fundamental Cocoa classes (strings, collections, ...) for seamless integration between Scala and Cocoa. 


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

{% include links.html %}

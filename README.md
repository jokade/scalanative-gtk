# scalanative-gtk
Experimental [scala-native](https://github.com/scala-native/scala-native) bindings for [Gtk+, GLib, ...](https://www.gtk.org).

**WARNING**: This project is in an early experimental stage. Most of the bindings are still missing. The API and especially the semantics for GObject access in Scala may change any time!

**[Documentation](https://jokade.github.io/scalanative-gtk/)**

### sbt Settings
Add this to your `build.sbt`:
```scala
libraryDependencies += "de.surfice" %%% "scalanative-gtk3" % "0.0.2-SNAPSHOT"

resolvers += Resolver.sonatypeRepo("snapshots")
```

### Implementation
The bindings are generated using an [annotation macro](https://github.com/jokade/scalanative-obj-interop).

More details to follow.

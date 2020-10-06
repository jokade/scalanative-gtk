organization in ThisBuild := "de.surfice"

version in ThisBuild := "0.0.2-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.12"

val Version = new {
  val swog        = "0.1.0-SNAPSHOT"
  val smacrotools = "0.0.8"
  val utest       = "0.6.8-SNAPSHOT"
}


lazy val commonSettings = Seq(
  scalacOptions ++= Seq("-deprecation","-unchecked","-feature","-language:implicitConversions","-Xlint"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  libraryDependencies ++= Seq(
    "de.surfice" %%% "swog-cobj" % Version.swog
    //"com.lihaoyi" %%% "utest" % Version.utest % "test"
    ),
  resolvers += Opts.resolver.sonatypeSnapshots
  //testFrameworks += new TestFramework("utest.runner.Framework")
  )

lazy val nativeSettings = Seq(
  nativeLinkingOptions ++= Seq("-lglib-2.0","-lgtk-3.0","-lgobject-2.0","-ljson-glib-1.0"),
  nativeLinkStubs := true
)

lazy val scalanativeGtk = project.in(file("."))
  .aggregate(glibNative //, glibJVM
             ,gobjNative //, gobjJVM
             ,gioNative //, gioJVM
             ,gtk3Native //, gtk3JVM
             ,jsonNative //, jsonJVM
             )
  .settings(commonSettings ++ dontPublish:_*)
  .settings(
    name := "scalanative-gtk-bindings"
    )

lazy val glib = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-glib"
  )
  .nativeSettings(nativeSettings:_*)
//lazy val glibJVM = glib.jvm
lazy val glibNative = glib.native


lazy val gobj = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .dependsOn(glib)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-gobj",
    libraryDependencies ++= Seq(
//      "de.surfice" %% "smacrotools" % Version.smacrotools
    )
  )
//lazy val gobjJVM = gobj.jvm
lazy val gobjNative = gobj.native


lazy val gio = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .dependsOn(gobj)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-gio"
  )
//lazy val gioJVM = gio.jvm
lazy val gioNative = gio.native


lazy val gtk3 = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .dependsOn(gio)
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-gtk3"
  )
//lazy val gtk3JVM = gtk3.jvm
lazy val gtk3Native = gtk3.native

/*
lazy val ui = project
  .dependsOn(gtk3)
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-gtk3-ui"
  )

lazy val sourceview = project
  .dependsOn(gtk3)
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-gtksourceview"
  )

lazy val tepl = project
  .dependsOn(sourceview)
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-tepl"
  )
*/
lazy val json = crossProject(JVMPlatform, NativePlatform)
  .crossType(CrossType.Pure)
  .dependsOn(glib,gobj)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-json-glib"
  )
  .nativeSettings(nativeSettings:_*)
//lazy val jsonJVM = json.jvm
lazy val jsonNative = json.native

/*
lazy val soup = project
  .dependsOn(gio)
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-libsoup"
  )

lazy val macIntegration = project
  .dependsOn(gtk3)
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings ++ publishingSettings:_*)
  .settings(
    name := "scalanative-gtk-mac-integration"
  )

lazy val gtkTest = project
  .dependsOn(gtk3)
  .enablePlugins(ScalaNativePlugin)
  .settings(commonSettings ++ dontPublish ++ nativeSettings:_*)
  .settings(
    //nativeLinkingOptions ++= nbhNativeLinkingOptions.value
    //nativeLinkingOptions ++= Seq("-lgtk-3.0","-lglib-2.0")
  )
*/  

lazy val dontPublish = Seq(
  publish := {},
  publishLocal := {},
  //com.typesafe.sbt.pgp.PgpKeys.publishSigned := {},
  //com.typesafe.sbt.pgp.PgpKeys.publishLocalSigned := {},
  publishArtifact := false,
  publishTo := Some(Resolver.file("Unused transient repository",file("target/unusedrepo")))
)

lazy val publishingSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  pomExtra := (
    <url>https://github.com/jokade/scalanative-gtk</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:jokade/scalanative-gtk</url>
      <connection>scm:git:git@github.com:jokade/scalanative-gtk.git</connection>
    </scm>
    <developers>
      <developer>
        <id>jokade</id>
        <name>Johannes Kastner</name>
        <email>jokade@karchedon.de</email>
      </developer>
    </developers>
  )
)
 

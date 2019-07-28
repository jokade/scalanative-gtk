name in ThisBuild := "Gtk Examples"

version in ThisBuild := "0.0.1-SNAPSHOT"

scalaVersion in ThisBuild := "2.11.12"

val Version = new {
  val gtk         = "0.0.2-SNAPSHOT"
}

lazy val bundlePath = settingKey[File]("Path to the macOS bundle")
lazy val bundleInfo = settingKey[File]("Path to the Info.plist")
lazy val bundlePrepare = taskKey[File]("Prepares the macOS bundle")
lazy val bundle     = taskKey[File]("Create the macOS application bundle")

lazy val commonSettings = Seq(
  scalacOptions ++= Seq("-deprecation","-unchecked","-feature","-language:implicitConversions","-Xlint"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  libraryDependencies ++= Seq(
    "de.surfice" %%% "scalanative-gtk3"      % Version.gtk
  )
)

lazy val gtkExample = project
  .enablePlugins(ScalaNativePlugin,NBHAutoPlugin)
  .settings(commonSettings ++ dontPublish: _*)
  .settings(
    nativeLinkStubs := true,
    nativeLinkingOptions ++= nbhNativeLinkingOptions.value,
    libraryDependencies ++= Seq(
    )
  )
  

lazy val dontPublish = Seq(
  publish := {},
  publishLocal := {},
  publishArtifact := false,
  publishTo := Some(Resolver.file("Unused transient repository",file("target/unusedrepo")))
)

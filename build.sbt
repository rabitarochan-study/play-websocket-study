name := """play-websocket-study"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws
)

// webjars
libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3.0-2",
  "org.webjars" % "bootstrap"     % "3.1.1-2",
  "org.webjars" % "angularjs"     % "1.3.15"
)
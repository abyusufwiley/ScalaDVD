name := """ScalaDVD"""
organization := "com.dvdlibrary"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.13"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
libraryDependencies ++= Seq(
  "com.mysql" % "mysql-connector-j" % "8.0.33"
)



// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.dvdlibrary.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.dvdlibrary.binders._"

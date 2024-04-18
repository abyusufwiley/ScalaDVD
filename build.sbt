ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.13"
lazy val root = (project in file("."))
  .settings(
    name := "ScalaDVD"
  )

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-devtools" % "2.5.0" % "runtime",
  "org.springframework.boot" % "spring-boot-starter-web" % "2.5.0",
  "org.springframework.boot" % "spring-boot-starter-test" % "2.5.0" % "test",
  "org.springframework.boot" % "spring-boot-starter-data-jpa" % "2.5.0",
  "com.h2database" % "h2" % "1.4.200" % "runtime",
  "com.softwaremill.macwire" %% "macros" % "2.3.7" % Provided,
  "com.softwaremill.macwire" %% "util" % "2.3.7"
)

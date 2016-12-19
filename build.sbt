name := "scim"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies++=Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.2"  withSources() withJavadoc(),
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2"  withSources() withJavadoc(),
  "com.h2database" % "h2" % "1.4.192"  withSources() withJavadoc(),
  "org.postgresql" % "postgresql" % "9.4.1212" withSources() withJavadoc(),
  "org.specs2" %% "specs2-core" % "3.8.5" % "test" withSources() withJavadoc(),
  "com.typesafe.play" % "play-test_2.11" % "2.5.10" % "test" withSources() withJavadoc(),
  "com.typesafe.scala-logging" %% "scala-logging-api" % "2.1.2" withSources() withJavadoc()
)



fork in run := true
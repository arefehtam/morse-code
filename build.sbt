name := "morse-service"
version := "0.0.1"
organization := "ir.pr.saman"

scalaVersion := "2.12.10"

val akkaVersions = new {
  val core = "2.6.8"
  val http = "10.2.0"
}

mainClass in(Compile, packageBin) := Some("ir.pr.saman.service.morse.Application")

libraryDependencies ++= Seq(
  //AKKA
  "com.typesafe.akka" %% "akka-actor" % akkaVersions.core,
  "com.typesafe.akka" %% "akka-remote" % akkaVersions.core,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaVersions.http,
  //Config
  "com.typesafe" % "config" % "1.3.2",
  // Logging
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "org.slf4j" % "jcl-over-slf4j" % "1.7.26",
  //Spray
  "io.spray" %% "spray-json" % "1.3.5",
  //Test
  "org.scalatest" %% "scalatest" % "3.2.0" % "test",
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersions.core,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaVersions.http
)



libraryDependencies ~= {
  _.map(_.exclude("org.slf4j", "slf4j-simple"))
}

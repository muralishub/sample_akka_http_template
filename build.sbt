name := "Product"

version := "0.1"

scalaVersion := "2.12.4"

val akkaHttpVersion = "10.0.11"
val circeVersion = "0.9.1"


libraryDependencies ++= Seq(
"com.typesafe.akka" %% "akka-http"   % akkaHttpVersion,
"io.circe" %% "circe-core" % circeVersion,
"io.circe" %% "circe-generic" % circeVersion,
"io.circe" %% "circe-parser" % circeVersion,
  "de.heikoseeberger" %% "akka-http-circe" % "1.19.0",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)
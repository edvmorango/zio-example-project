name := "workshop"

version := "0.1"

scalaVersion := "2.12.10"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.10")


scalacOptions ++= Seq(
  "-Ypartial-unification",
  "-language:higherKinds",
  "-deprecation",
  "-encoding",
  "utf-8",
  "-explaintypes",
  "-feature",
  "-language:existentials",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xlint:infer-any",
  "-Xlint:type-parameter-shadow",
  "-Xlint:unsound-match",
  "-Ywarn-dead-code",
  "-Ywarn-extra-implicit",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:params",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-Ywarn-value-discard"
)

libraryDependencies ++= Seq(
  "dev.zio"                    %% "zio"                           % zioVersion,
  "dev.zio"                    %% "zio-streams"                   % zioVersion,
  "dev.zio"                    %% "zio-interop-cats"              % zioInteropCats,
  "dev.zio"                    %% "zio-kafka"                     % zioKafkaVersion,
  "io.circe"                   %% "circe-core"                    % circeVersion,
  "io.circe"                   %% "circe-generic"                 % circeVersion,
  "io.circe"                   %% "circe-generic-extras"          % circeVersion,
  "io.circe"                   %% "circe-parser"                  % circeVersion,
  "io.circe"                   %% "circe-optics"                  % circeOptics,
  "org.http4s"                 %% "http4s-dsl"                    % http4sVersion,
  "org.http4s"                 %% "http4s-circe"                  % http4sVersion,
  "org.http4s"                 %% "http4s-blaze-server"           % http4sVersion,
  "io.chrisdavenport"          %% "fuuid"                         % fuuidVersion,
  "io.chrisdavenport"          %% "fuuid-circe"                   % fuuidVersion,
  "io.chrisdavenport"          %% "fuuid-http4s"                  % fuuidVersion
)

//Core
val zioVersion        = "1.0.0-RC16"
val zioInteropCats    = "2.0.0.0-RC7"
val zioKafkaVersion   = "0.3.2"
val zioScanamo = "1.0.0-M11"
val pureConfigVersion = "0.12.1"

//JSON
val circeVersion      = "0.12.2"
val circeOptics       = "0.12.0"

//Http-Server
val http4sVersion = "0.20.11"

//Http-Client
val sttpVersion = "1.7.2"

//Database
val doobieVersion = "0.8.4"
val mysqlVersion  = "8.0.18"
val flywayVersion = "6.0.6"
val cassandraVersion = "2.9.2"
val redisVersion = "0.9.1"
val scanamoVersion = "1.0.0-M11"
//Util
val fuuidVersion   = "0.2.0"
val monocleVersion = "2.0.0"

//Test
val scalaTestVersion  = "3.2.0-SNAP10"
val scalaCheckVersion = "1.14.2"

// Logging
val logbackVersion = "1.2.3"

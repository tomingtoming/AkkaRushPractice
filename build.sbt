name := "project"

version := "0.0.0"

organization := "organization"

scalaVersion := "2.11.7"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                  "staging"       at "http://oss.sonatype.org/content/repositories/staging",
                  "releases"      at "http://oss.sonatype.org/content/repositories/releases"
                 )

scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")

javaOptions += "-server"

libraryDependencies ++= {
  val liftVersion = "2.6-M4"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.4.1",
    "ch.qos.logback" % "logback-classic" % "1.1.3"
  )
}

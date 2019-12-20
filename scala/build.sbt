name := "hackerrank"

version := "1.0.0"

scalaVersion := "2.12.7"

scalacOptions ++= Seq("-deprecation")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += "junit" % "junit" % "4.12" % "test"

fork in Test := true

javaOptions += "-Xmx2G"

cancelable in Global := true

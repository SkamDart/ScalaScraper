name := "scala_graphy_scraper"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "net.ruippeixotog" %% "scala-scraper" % "2.0.0"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.7" % "test"

libraryDependencies += "net.liftweb" %% "lift-json" % "2.6"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.10"

libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "10.0.10" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.10"
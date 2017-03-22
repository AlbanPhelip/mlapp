name := "mlapp2"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0" % "provided"

libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.1.0" % "provided"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.1.0" % "provided"

libraryDependencies += "org.apache.bahir" % "spark-streaming-twitter_2.11" % "2.1.0"

libraryDependencies += "joda-time" % "joda-time" % "2.9.7"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "provided"


mainClass in assembly := Some("fr.xebia.data.mlapp.Main")

// See http://stackoverflow.com/questions/37152902/sbt-assemblymergestrategy-not-working
assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class") => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
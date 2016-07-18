name := """play-elasticsearch-autocomplate"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.elasticsearch" % "elasticsearch" % "2.3.3",

  // WebJars (i.e. client-side) dependencies
  "org.webjars" %% "webjars-play" % "2.5.0",
  "org.webjars" % "jquery" % "1.11.3",
  "org.webjars" % "bootstrap" % "3.3.6" exclude("org.webjars", "jquery"),
  "org.webjars" % "jquery-ui" % "1.9.2" exclude("org.webjars", "jquery"),
  "net.liftweb"                	%%      "lift-json"         	%       "2.6",
    specs2 % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

javaOptions in Test += "-Dconfig.file=conf/test.conf"


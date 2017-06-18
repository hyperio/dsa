import sbt._
import sbt.Keys._
import Dependencies._

object DsaBuild extends Build {
  object B {
    val name = "dsa"
    val version = "1.0"
  }

  val defaultSettings = Seq(
    version := B.version,
    scalaVersion := "2.12.1",
    javacOptions in compile ++= Seq("-source", "1.8", "-target", "1.8", "-g"),
    scalacOptions ++= Seq("-feature", "-language:implicitConversions", "-deprecation")
  )

  lazy val root = Project(id = B.name, base = file("."), aggregate = Seq())
    .settings(defaultSettings: _*)
    .settings(
      libraryDependencies ++=
        D.test(testLibs.specs2, testLibs.junit, testLibs.sbtJjunitInterface) ++
        D.compile(utils.guava)
    )

//  lazy val cci = Project(id = "cci", base = file("cci"))
//    .settings(defaultSettings: _*)
//    .settings(
//      libraryDependencies ++= D.test(testLibs.specs2, testLibs.junit) ++
//        D.compile(utils.guava)
//    )
}

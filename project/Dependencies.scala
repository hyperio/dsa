import sbt._

object Dependencies {
  object D {
    def compile(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")
    def test(deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
  }

  object testLibs {
    val specs2 = "org.specs2" %% "specs2-core" % "3.8.8"
    val junit = "junit" % "junit" % "4.12"
    val sbtJjunitInterface = "com.novocode" % "junit-interface" % "0.11"
  }

  object utils {
    val guava = "com.google.guava" % "guava" % "21.0"
  }
}

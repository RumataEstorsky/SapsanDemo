import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "example"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore, javaJdbc, javaEbean,
	"postgresql" % "postgresql" % "9.1-901.jdbc4",
    "ru.myscala" % "sapsan_2.10" % "0.1"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
      resolvers += Resolver.url("SapsanAdmin GitHub Repository", url("http://rumataestorsky.github.com/releases/"))(Resolver.ivyStylePatterns)
  )

}

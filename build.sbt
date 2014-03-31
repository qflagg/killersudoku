name := "KillerSudokuSolver"

version := "0.1"

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test",
	"com.typesafe.akka" %% "akka-actor" % "2.1.1",
	"org.scalaj" %% "scalaj-http" % "0.3.14"
)

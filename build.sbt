name := "KillerSudokuSolver"

version := "0.1"

scalaVersion := "2.10.3"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % "2.1.1",
	"com.assembla.scala-incubator" %% "graph-core" % "1.8.0",
	"org.scalaj" %% "scalaj-http" % "0.3.14"
)

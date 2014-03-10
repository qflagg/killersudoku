package KillerSudokuSolver

import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.Direction

object KillerSudokuSolver {

  def main(args: Array[String]): Unit = {
    val puzzleURL = "http://www.calcudoku.org/killersudoku/en/2014-01-14/9/1"
    val neo4j = "/home/doccali/Remote/Downloads/temp/neo4j-community-2.0.0/data/graph.db/"

    val puzzleDifficulty = puzzleURL.split("/").last
    val dateRegex = """\d\d\d\d-\d\d-\d\d""".r
    val puzzleDate = dateRegex.findFirstIn(puzzleURL).get
    //val puzzle = DownloadPuzzle.getPuzzle(puzzleURL)

    val graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(neo4j)
    registerShutdownHook(graphDb);

    try {
      val tx = graphDb.beginTx()
      val firstNode = graphDb.createNode()
      firstNode.setProperty("message", "Hello, ")
      val secondNode = graphDb.createNode()
      secondNode.setProperty("message", "World!")
      val relationship = firstNode.createRelationshipTo(secondNode, KnowsRelationship)
      relationship.setProperty("message", "brave Neo4j")
      print(firstNode.getProperty("message"))
      print(relationship.getProperty("message"))
      print(secondNode.getProperty("message"))
      firstNode.getSingleRelationship( KnowsRelationship, Direction.OUTGOING ).delete()
      firstNode.delete()
      secondNode.delete()
      tx.success()
    }
  }

  private def registerShutdownHook(graphDb: GraphDatabaseService) {
    // Registers a shutdown hook for the Neo4j instance so that it
    // shuts down nicely when the VM exits (even if you "Ctrl-C" the
    // running application).
    //from neo4j.org
    Runtime.getRuntime().addShutdownHook(new Thread() {
      override def run() {
        graphDb.shutdown();
      }
    });
  }

}
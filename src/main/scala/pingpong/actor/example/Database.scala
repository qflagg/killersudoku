package pingpong.actor.example

import org.neo4j.graphdb.Direction
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import pingpong.actor.example.db.relationships.KnowsRelationship
import org.neo4j.graphdb.RelationshipType
import org.neo4j.graphdb.Relationship

object Database {
  val neo4j = "/Users/qflagg/Downloads/neo4j-community-2.0.1/data/graph.db/"
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
    firstNode.getSingleRelationship(KnowsRelationship, Direction.OUTGOING).delete()
    //firstNode.delete()
    //secondNode.delete()
    tx.success()
  }

  def writeNode(message: String): Option[org.neo4j.graphdb.Node] = {
    val tx = graphDb.beginTx()
    try {
      val node = graphDb.createNode()
      node.setProperty("message", message)
      tx.success()
      Some(node)
    } catch {
      case _ => {
        tx.failure()
        None
      }

    }

  }

  def writeRelationship(outNode: org.neo4j.graphdb.Node, inNode: org.neo4j.graphdb.Node, relationship:RelationshipType): Option[Relationship]= {
	  val tx = graphDb.beginTx()
	  try{
	    val createRelationship = outNode.createRelationshipTo(inNode, relationship)
	    createRelationship.setProperty("message", "brave Neo4j")
	    tx.success()
	    Some(createRelationship)
	  } catch {
	    case _ => {
        tx.failure()
        None
      }
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
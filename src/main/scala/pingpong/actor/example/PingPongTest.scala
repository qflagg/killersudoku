package pingpong.actor.example

import akka.actor._

object PingPongTest {
  def main(args: Array[String]) {
    val system = ActorSystem("PingPongSystem")
    val pong = system.actorOf(Props[Pong], name = "pong")
    val ping = system.actorOf(Props(new Ping(pong)), name = "ping")
    
    // start them going
    ping ! StartMessage
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

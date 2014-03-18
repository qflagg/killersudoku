package pingpong.actor.example

import akka.actor._
import org.neo4j.graphdb.Direction
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseFactory

case object PingMessage
case object PongMessage
case object StartMessage
case object StopMessage

/**
 * An Akka Actor example written by Alvin Alexander of
 * http://devdaily.com
 *
 * Shared here under the terms of the Creative Commons
 * Attribution Share-Alike License: http://creativecommons.org/licenses/by-sa/2.5/
 * 
 * more akka info: http://doc.akka.io/docs/akka/snapshot/scala/actors.html
 */
class Ping(pong: ActorRef) extends Actor {
  var count = 0
  def incrementAndPrint { count += 1; println("ping") }
  def receive = {
    case StartMessage =>
        incrementAndPrint
        pong ! PingMessage
    case PongMessage => 
        incrementAndPrint
        if (count > 99) {
          sender ! StopMessage
          println("ping stopped")
          context.stop(self)
        } else {
          sender ! PingMessage
        }
  }
}
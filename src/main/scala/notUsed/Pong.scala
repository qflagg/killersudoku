//package notUsed
//
//import akka.actor._ {
//    case PingMessage =>
//        println("  pong")
//        sender ! PongMessage
//    case StopMessage =>
//        println("pong stopped")
//        context.stop(self)
//  }
//}
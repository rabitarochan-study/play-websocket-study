package controllers

import akka.actor.{Actor, Props, ActorRef}
import play.api.mvc._
import play.api.Play.current

object WebSocketWithActors extends Controller {

  def actorWs = WebSocket.acceptWithActor[String, String] { request => out =>
    out ! "[Actor] Connected"
    PingActor.props(out)
  }

}

class PingActor(param: PingActor.Param) extends Actor {

  override def receive = {
    case _ => param.out ! "[Actors] Pong!"
  }

}

object PingActor {

  final case class Param(out: ActorRef)

  def props(out: ActorRef) = Props(classOf[PingActor], Param(out))

}

package controllers

import play.api.libs.iteratee.{Concurrent, Iteratee}
import play.api.mvc._

import scala.concurrent.Promise
import scala.concurrent.ExecutionContext.Implicits.global

object WebSocketWithIteratee extends Controller {

  def iterateeWs = WebSocket.using[String] { request =>
    val in = Promise[Iteratee[String, Unit]]()

    val out = Concurrent.unicast[String](
      onStart = ch => {
        ch.push("[Iteratee] Connect")
        in.success(
          Iteratee.foreach[String](_ => ch.push("[Iteratee] Pong!"))
        )
      },
      onComplete = ()
    )

    (Iteratee.flatten(in.future), out)
  }

}

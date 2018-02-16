package com

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.controllers.SampleController

import scala.concurrent.ExecutionContextExecutor



object Main extends App {
  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()
  implicit val executor: ExecutionContextExecutor = actorSystem.dispatcher

  implicit val config: Config = ConfigEnv

  val log = Logging(actorSystem, getClass)

  val sampleController = new SampleController

  val routes: Route = {
    logRequestResult(config.appName) {
      sampleController.routes
    }
  }

  Http().bindAndHandle(routes, config.host, config.port)
    .foreach(sb => log.info(s"${config.appName} app listening to ${sb.localAddress}"))
}

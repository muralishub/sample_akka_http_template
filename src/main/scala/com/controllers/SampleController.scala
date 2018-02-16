package com.controllers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.Config
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport


case class InternalStatus(status: String)
case class InternalConfig(appName: String, appVersion: String)
case class InternalVersion(revision: String)


class SampleController(implicit config: Config) extends Controller with FailFastCirceSupport{
  import io.circe.generic.auto._
  import io.circe.syntax._

  def routes: Route = {
    (get & pathPrefix("sample")) {
      path("status") {
        complete(StatusCodes.OK -> InternalStatus("OK"))
      } ~
        path("version") {
          complete(StatusCodes.OK -> InternalVersion(config.appVersion))
        } ~
        path("config") {
          complete(StatusCodes.OK -> InternalConfig(config.appName, config.appVersion).asJson)
        }
    }
  }
}

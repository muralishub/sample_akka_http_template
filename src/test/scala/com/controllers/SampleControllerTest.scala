package com.controllers

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.{Config, ConfigEnv, HttpResponseExtension}
import org.scalatest.FunSpec
import org.scalatest.Matchers._

class SampleControllerTest extends FunSpec with ScalatestRouteTest with FutureSupport with HttpResponseExtension{

  import io.circe.generic.auto._

  implicit val config: Config = ConfigEnv

  val controller = new SampleController

  it("/sample/status works") {
    Get("/sample/status") ~> controller.routes ~> check {
      response.status shouldBe StatusCodes.OK
      response.jsonEntityAs[InternalStatus].eventually shouldBe Right(InternalStatus("OK"))
    }
  }

  it("/sample/version works") {
    Get("/sample/version") ~> controller.routes ~> check {
      response.status shouldBe StatusCodes.OK
      response.jsonEntityAs[InternalVersion].eventually shouldBe Right(InternalVersion(config.appVersion))
    }
  }

  it("/sample/config works") {
    Get("/sample/config") ~> controller.routes ~> check {
      response.status shouldBe StatusCodes.OK
      response.jsonEntityAs[InternalConfig].eventually shouldBe Right(InternalConfig(config.appName, config.appVersion))
    }
  }

}

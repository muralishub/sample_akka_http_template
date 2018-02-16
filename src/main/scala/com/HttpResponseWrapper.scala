package com

import akka.http.scaladsl.model.HttpResponse
import akka.stream.ActorMaterializer
import akka.util.ByteString
import io.circe.Decoder

import scala.concurrent.{ExecutionContext, Future}
import scala.language.implicitConversions

class HttpResponseWrapper(response: HttpResponse) {
  def entityAsString(implicit executionContext: ExecutionContext, actorMaterializer: ActorMaterializer): Future[String] =
    response.entity.dataBytes.runFold(ByteString.empty)(_ ++ _).map(_.utf8String)

  def jsonEntityAs[T](implicit executionContext: ExecutionContext, actorMaterializer: ActorMaterializer, decoder: Decoder[T]): Future[Either[io.circe.Error, T]] = {
    import io.circe.parser._
    entityAsString.map(decode[T])
  }
}

trait HttpResponseExtension {
  implicit def httpResponseWrapper(response: HttpResponse): HttpResponseWrapper = new HttpResponseWrapper(response)
}

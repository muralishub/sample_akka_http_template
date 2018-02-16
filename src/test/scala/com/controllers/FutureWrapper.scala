package com.controllers

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

import scala.language.implicitConversions

class FutureWrapper[T](future: Future[T]) {
  def eventually: T = Await.result(future, 1.second)
}

trait FutureSupport {
  implicit def futureWrapper[T](future: Future[T]): FutureWrapper[T] = new FutureWrapper[T](future)
}

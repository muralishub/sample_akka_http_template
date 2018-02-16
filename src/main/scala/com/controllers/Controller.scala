package com.controllers

import akka.http.scaladsl.server.Route

trait Controller {
  def routes: Route
}

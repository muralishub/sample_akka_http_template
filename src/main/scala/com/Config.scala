package com

trait Config {
  def host: String
  def port: Int
  def appName: String
  def appVersion: String
}

object ConfigEnv extends Config {
  val host: String = "localhost"
  val port: Int = sys.env.get("PORT").map(_.toInt).getOrElse(9000)
  val appName: String = sys.env.getOrElse("APP_NAME", "product")
  val appVersion: String = sys.env.getOrElse("VERSION", "LOCAL")
}
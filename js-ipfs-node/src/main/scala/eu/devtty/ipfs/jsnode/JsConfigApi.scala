package eu.devtty.ipfs.jsnode

import eu.devtty.ipfs.ConfigApi

import scala.concurrent.Future
import scala.scalajs.js

class JsConfigApi(implicit val node: raw.JsIpfs) extends ConfigApi {
  @inline
  private def config = node.config

  def get(key: String): Future[js.Dynamic] = config.get(key).toFuture
  def set(key: String, value: String): Future[_] = config.set(key, value).toFuture
  def replace(cfg: js.Object): Future[_] = config.replace(cfg).toFuture
}

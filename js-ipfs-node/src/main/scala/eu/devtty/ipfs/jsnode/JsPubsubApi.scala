package eu.devtty.ipfs.jsnode

import eu.devtty.ipfs.{PubsubApi, PubsubMsg, PubsubSubOpts}
import io.scalajs.nodejs.buffer.Buffer

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class JsPubsubApi(implicit val node: raw.JsIpfs) extends PubsubApi {
  @inline
  private def pubsub = node.pubsub

  def subscribe(topic: String, options: PubsubSubOpts, handler: (PubsubMsg) => Unit): Future[_] = pubsub.subscribe(topic, options, handler).toFuture
  def subscribe(topic: String, handler: (PubsubMsg) => Unit): Future[_] = pubsub.subscribe(topic, handler).toFuture

  def unsubscribe(topic: String, handler: (PubsubMsg) => Unit): Unit = pubsub.unsubscribe(topic, handler)
  def publish(topic: String, data: Buffer): Future[_] = pubsub.publish(topic, data).toFuture
  def ls(topic: String): Future[Array[String]] = pubsub.ls(topic).toFuture.map(_.toArray)
  def ls(): Future[Array[String]] = pubsub.ls().toFuture.map(_.toArray)
  def peers(topic: String): Future[Array[String]] = pubsub.peers(topic).toFuture.map(_.toArray)
}

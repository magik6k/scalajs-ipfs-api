package eu.devtty.ipfs

import io.scalajs.nodejs.buffer.Buffer

import scala.concurrent.Future
import scala.scalajs.js

trait PubsubApi {
  /**
    * Subscribe to a pubsub topic
    * @param topic
    * @param options
    * @param handler
    * @return
    */
  def subscribe(topic: String, options: PubsubSubOpts, handler: (PubsubMsg)=>Unit): Future[_]

  /**
    * Subscribe to a pubsub topic
    * @param topic
    * @param handler
    * @return
    */
  def subscribe(topic: String, handler: (PubsubMsg)=>Unit): Future[_]

  /**
    * This works like EventEmitter.removeListener, as that only the handler passed
    * to a subscribe call before is removed from listening. The underlying subscription
    * will only be canceled once all listeners for a topic have been removed.
    * @param topic
    * @param handler
    */
  def unsubscribe(topic: String, handler: (PubsubMsg)=>Unit): Unit

  /**
    * Publish a data message to a pubsub topic.
    * @param topic
    * @param data
    * @return
    */
  def publish(topic: String, data: Buffer): Future[_]

  /**
    * Returns the list of subscriptions the peer is subscribed to.
    * @param topic
    * @return List of topicCIDs that this peer is subscribed to
    */
  def ls(topic: String): Future[Array[String]]

  /**
    * Returns the list of subscriptions the peer is subscribed to.
    * @return List of topicCIDs that this peer is subscribed to
    */
  def ls(): Future[Array[String]]

  /**
    * Returns the peers that are subscribed to one topic.
    * @param topic
    * @return List of peer ids subscribed to the topic.
    */
  def peers(topic: String): Future[Array[String]]

}

@js.native
trait PubsubMsg extends js.Object {
  val from: String = js.native
  val seqno: Buffer = js.native
  val data: Buffer = js.native
  val topicCIDs: Array[String] = js.native
}

@js.native
trait PubsubSubOpts extends js.Object {
  val discover: Boolean = js.native
}
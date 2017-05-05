package eu.devtty.ipfs.jsinterface.raw

import eu.devtty.ipfs.{PubsubMsg, PubsubSubOpts}
import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js

@js.native
trait JsPubsubApi extends js.Object {
  def subscribe(topic: String, options: PubsubSubOpts, handler: js.Function1[PubsubMsg, Unit]): js.Promise[_] = js.native
  def subscribe(topic: String, handler: js.Function1[PubsubMsg, Unit]): js.Promise[_] = js.native

  def unsubscribe(topic: String, handler: js.Function1[PubsubMsg, Unit]): Unit = js.native

  def publish(topic: String, data: Buffer): js.Promise[_] = js.native
  def ls(topic: String): js.Promise[js.Array[String]] = js.native
  def ls(): js.Promise[js.Array[String]] = js.native
  def peers(topic: String): js.Promise[js.Array[String]] = js.native
}

package eu.devtty.ipfs.jsinterface.raw

import eu.devtty.ipfs.{PeerID, Version}

import scala.scalajs.js
import scala.scalajs.js.Promise

@js.native
trait JsIpfsInterface extends js.Object{
  val dag: JsDagApi = js.native
  val block: JsBlockApi = js.native
  val config: JsConfigApi = js.native
  val files: JsFilesApi = js.native
  val pubsub: JsPubsubApi = js.native
  val swarm: JsSwarmApi = js.native

  def id(): Promise[PeerID] = js.native
  def version(): Promise[Version] = js.native
}

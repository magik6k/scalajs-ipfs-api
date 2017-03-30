package eu.devtty.ipfs

import scala.scalajs.js

@js.native
trait PeerID extends js.Object {
  val id: String = js.native
  val publicKey: String = js.native
  val addresses: Array[String] = js.native
  val agentVersion: String = js.native
  val protocolVersion: String = js.native
}

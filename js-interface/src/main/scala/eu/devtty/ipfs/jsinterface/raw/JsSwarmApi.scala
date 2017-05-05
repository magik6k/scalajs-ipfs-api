package eu.devtty.ipfs.jsinterface.raw

import eu.devtty.ipfs.{ConnectedPeerInfo, SwarmPeersOpts}
import eu.devtty.multiaddr.Multiaddr
import eu.devtty.peerinfo.PeerInfo

import scala.scalajs.js

@js.native
trait JsSwarmApi extends js.Object {
  def addrs(callback: js.Function2[String, js.Array[PeerInfo], _]): Unit = js.native
  def connect(addr: Multiaddr, callback: js.Function2[String, _, _]): Unit = js.native
  def disconnect(addr: Multiaddr, callback: js.Function2[String, _, _]): Unit = js.native
  def peers(opts: SwarmPeersOpts, callback: js.Function2[String, js.Array[ConnectedPeerInfo], _]): Unit = js.native
  def peers(callback: js.Function2[String, js.Array[ConnectedPeerInfo], _]): Unit = js.native
}

package eu.devtty.ipfs.jsinterface

import eu.devtty.ipfs.jsinterface.util.FutureCallErr1
import eu.devtty.ipfs.{ConnectedPeerInfo, SwarmApi, SwarmPeersOpts}
import eu.devtty.multiaddr.Multiaddr
import eu.devtty.peerinfo.PeerInfo

import scala.concurrent.Future
import scala.scalajs.js
import scala.concurrent.ExecutionContext.Implicits.global

class JsSwarmApi(implicit val node: raw.JsIpfsInterface) extends SwarmApi {
  @inline
  private def swarm = node.swarm

  def addrs: Future[Array[PeerInfo]] = FutureCallErr1[js.Array[PeerInfo]](cb => swarm.addrs(cb)).map(_.toArray)
  def connect(addr: Multiaddr): Future[_] = FutureCallErr1(cb => swarm.connect(addr, cb))
  def disconnect(addr: Multiaddr): Future[_] = FutureCallErr1(cb => swarm.disconnect(addr, cb))
  def peers(opts: SwarmPeersOpts): Future[Array[ConnectedPeerInfo]] = opts match {
    case null => FutureCallErr1[js.Array[ConnectedPeerInfo]](cb => swarm.peers(cb)).map(_.toArray)
    case _ => FutureCallErr1[js.Array[ConnectedPeerInfo]](cb => swarm.peers(opts, cb)).map(_.toArray)
  }
}

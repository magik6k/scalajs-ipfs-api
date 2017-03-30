package eu.devtty.ipfs

import eu.devtty.multiaddr.Multiaddr
import eu.devtty.peerinfo.PeerInfo

import scala.concurrent.Future
import scala.scalajs.js

trait SwarmApi {
  def addrs: Future[Array[PeerInfo]] = js.native
  def connect(addr: Multiaddr): Future[_] = js.native //TODO: ensure future type
  def disconnect(addr: Multiaddr): Future[_] = js.native //TODO: ensure future type
  def peers(opts: SwarmPeersOpts = null): Future[Array[ConnectedPeerInfo]]
}

@js.native
object SwarmPeersOpts extends js.Object {
  def apply(verbose: Boolean): SwarmPeersOpts =
    js.Dynamic.literal(verbose = verbose).asInstanceOf[SwarmPeersOpts]
}

@js.native
trait SwarmPeersOpts extends js.Object {
  val verbose: Boolean = js.native
}

@js.native
trait ConnectedPeerInfo extends js.Object {
  val addr: Multiaddr = js.native
  val peer: PeerInfo = js.native //TODO check type

  /**
    * Only if verbose: true was passed
    */
  val latency: String = js.native

  /**
    * The type of stream muxer the peer is usng
    */
  val muxer: String = js.native

  /**
    * Only if verbose: true, a list of currently open streams
    */
  val streams: Array[String] = js.native
}

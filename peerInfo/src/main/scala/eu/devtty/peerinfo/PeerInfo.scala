package eu.devtty.peerinfo

import eu.devtty.multiaddr.Multiaddr
import eu.devtty.peerid.PeerId

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("peer-info", JSImport.Namespace)
object PeerInfo {
  def create(id: PeerId, callBack: js.Function1[PeerInfo, _]): Unit = js.native
  def create(callBack: js.Function1[PeerInfo, _]): Unit = js.native
}

@js.native
@JSImport("peer-info", JSImport.Namespace)
class PeerInfo(id: PeerId) {
  val multiaddrs: MultiaddrSet = js.native

  def connect(ma: Multiaddr): Unit = js.native
  def disconnect(): Unit = js.native
  def isConnected: Boolean = js.native
}

@js.native
trait MultiaddrSet extends js.Object {
  /**
    * Adds a new multiaddress that peer can be reached at.
    * @param addr
    */
  def add(addr: Multiaddr): Unit = js.native

  /**
    * The addSafe call, in comparison to add, will only add the multiaddr to
    * multiaddrs if the same multiaddr tries to be added twice.
    *
    * This is a simple mechanism to prevent multiaddrs from becoming bloated with
    * unusable addresses, which happens when we exchange observed multiaddrs with
    * peers which will not provide a useful multiaddr to be shared to the rest
    * of the network (e.g. a multiaddr referring to a peer inside a LAN being shared to the outside world).
    * @param addr
    */
  def addSafe(addr: Multiaddr): Unit = js.native

  /**
    * Removes a multiaddress instance addr from peer
    * @param addr
    */
  def delete(addr: Multiaddr): Unit = js.native

  /**
    * Removes the array of multiaddresses existing from peer, and adds the array of multiaddresses fresh.
    * @param existing
    * @param fresh
    */
  def replace(existing: Multiaddr, fresh: Multiaddr): Unit = js.native
}
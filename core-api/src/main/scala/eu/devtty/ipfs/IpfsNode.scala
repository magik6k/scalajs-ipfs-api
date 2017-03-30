package eu.devtty.ipfs

import scala.concurrent.Future

trait IpfsNode {
  def id(): Future[PeerID]
  def block: BlockApi
}

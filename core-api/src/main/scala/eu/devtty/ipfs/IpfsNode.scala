package eu.devtty.ipfs

import scala.concurrent.Future

trait IpfsNode {
  def id(): Future[PeerID]
  def version: Future[String]

  def block: BlockApi
  def config: ConfigApi
  def files: FilesApi
  def swarm: SwarmApi
}

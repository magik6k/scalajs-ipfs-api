package eu.devtty.ipfs.api

import eu.devtty.ipfs.jsinterface._
import eu.devtty.ipfs._

import scala.concurrent.Future
import scala.scalajs.js

class IpfsApi(multiaddr: js.UndefOr[String] = js.undefined) extends IpfsNode {
  private implicit val node = new raw.IpfsApi(multiaddr)

  def id: Future[PeerID] = node.id().toFuture
  def version: Future[Version] = node.version().toFuture

  def block: BlockApi = new JsBlockApi
  def config: ConfigApi = new JsConfigApi
  def dag: DagApi = new JsDagApi
  def files: FilesApi = new JsFilesApi
  def pubsub: PubsubApi = new JsPubsubApi
  def swarm: SwarmApi = new JsSwarmApi
}

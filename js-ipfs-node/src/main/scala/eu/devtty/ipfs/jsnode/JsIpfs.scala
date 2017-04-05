package eu.devtty.ipfs.jsnode

import eu.devtty.ipfs._
import eu.devtty.ipfs.jsnode.util.FutureCall0

import scala.concurrent.Future
import scala.scalajs.js

class JsIpfs(nodeConfig: js.Any) extends IpfsNode {
  private implicit val node = new raw.JsIpfs(nodeConfig)

  def id: Future[PeerID] = node.id().toFuture
  def version: Future[Version] = node.version().toFuture

  def stop(): Future[_] = FutureCall0(node.stop)
  def start(): Future[_] = FutureCall0(node.start)

  def isOnline: Boolean = node.isOnline()

  def on(event: String): Future[_] = FutureCall0(c => on(event, c))

  def on[T](event: String, callback: () => T): Unit = {
    node.on(event, callback)
  }

  def block: BlockApi = new JsBlockApi
  def config: ConfigApi = new JsConfigApi
  def files: FilesApi = new JsFilesApi
  def pubsub: PubsubApi = ???
  def swarm: SwarmApi = new JsSwarmApi
}

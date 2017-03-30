package eu.devtty.ipfs.jsnode.raw

import eu.devtty.ipfs.{PeerID, Version}

import scala.scalajs.js
import scala.scalajs.js.Promise
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("ipfs", JSImport.Namespace)
class JsIpfs(config: js.Any) extends js.Object {
  val block: JsBlockApi = js.native

  // Generic
  def id(): Promise[PeerID] = js.native
  def version(): Promise[Version] = js.native
  //ping
  //log

  def on(event: String, callback: js.Function0[_]): Unit = js.native
  def start(callback: js.Function0[_]): Unit = js.native
  def stop(callback: js.Function0[_]): Unit = js.native

  def isOnline(): Boolean = js.native
}

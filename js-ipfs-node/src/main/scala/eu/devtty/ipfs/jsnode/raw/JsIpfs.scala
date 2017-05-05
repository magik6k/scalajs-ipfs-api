package eu.devtty.ipfs.jsnode.raw

import eu.devtty.ipfs.jsinterface.raw._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("ipfs", JSImport.Namespace)
class JsIpfs(_config: js.Any) extends JsIpfsInterface {

  //ping
  //log

  def on(event: String, callback: js.Function0[_]): Unit = js.native
  def start(callback: js.Function0[_]): Unit = js.native
  def stop(callback: js.Function0[_]): Unit = js.native

  def isOnline(): Boolean = js.native
}

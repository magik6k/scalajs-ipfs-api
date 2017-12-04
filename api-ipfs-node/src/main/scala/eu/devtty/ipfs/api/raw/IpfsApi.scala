package eu.devtty.ipfs.api.raw

import eu.devtty.ipfs.jsinterface.raw.JsIpfsInterface

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("ipfs-api", JSImport.Namespace)
class IpfsApi() extends JsIpfsInterface {
  @inline
  def this(multiaddr: String) = this()

  @inline
  def this(host: String, port: String) = this()

  @inline
  def this(opts: js.Dictionary[String]) = this()
}

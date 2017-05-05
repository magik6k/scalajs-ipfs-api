package eu.devtty.ipfs.api.raw

import eu.devtty.ipfs.jsinterface.raw.JsIpfsInterface

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("ipfs-api", JSImport.Namespace)
class IpfsApi(multiaddr: js.UndefOr[String]) extends JsIpfsInterface {

}

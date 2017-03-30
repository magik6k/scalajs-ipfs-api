package eu.devtty.peerid

import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("peer-id", JSImport.Namespace)
class PeerId extends js.native {
  def toHexString: String = js.native
  def toBytes: Buffer = js.native
  def toB58String: String = js.native

  def toJSON: PeerIdObject = js.native
  def toPrint: PeerIdObject = js.native

  def inEqual(id: PeerId): Boolean = js.native
  def inEqual(id: Buffer): Boolean = js.native
}

@js.native
trait PeerIdObject extends js.Object {
  /**
    * The multihash encoded in base58
    */
  val id: String = js.native

  /**
    * The public key in protobuf format, encoded in 'base64'
    */
  val pubKey: String = js.native

  /**
    * The private key in protobuf format, encoded in 'base 64'
    */
  val privKey: String = js.native
}
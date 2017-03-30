package eu.devtty.cid

import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("cids", JSImport.Namespace)
object CID extends js.Object {
  def isCID(other: js.Any): Boolean = js.native
  val codecs: js.Dictionary[Buffer] = js.native
}

@js.native
@JSImport("cids", JSImport.Namespace)
class CID() extends js.Object {

  /**
    * Construct new CID using CID String or bs58 encoded multihash
    *
    * @param string
    * @return
    */
  @inline
  def this(string: String) = this()

  /**
    * Construct new CID using CID/multihash buffer
    *
    * @param buffer
    * @return
    */
  @inline
  def this(buffer: Buffer) = this()

  /**
    * Construct new CID
    *
    * @param version CID version
    * @param codec multicodec codec name
    * @param multihash
    * @return
    */
  @inline
  def this(version: Int, codec: String, multihash: String) = this()

  /**
    * @return CID Serialized into buffer
    */
  def buffer: Buffer = js.native

  def prefix: Buffer = js.native

  def toV0: CID = js.native

  def toV1: CID = js.native

  def toBaseEncodedString(base: String = null): String = js.native

  def toJSON: CIDJson = js.native

  override def equals(other: Any): Boolean = js.native
}

@js.native
trait CIDJson extends js.Object {
  val codec: String = js.native
  val version: Int = js.native
  val hash: Buffer = js.native
}

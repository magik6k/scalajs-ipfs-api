package eu.devtty.multihash

import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("multihashes", JSImport.Namespace)
object MultiHash extends js.Object {
  /**
    * Convert the given multihash to a hex encoded string.
    * @param hash
    * @return
    */
  def toHexString(hash: Buffer): String = js.native

  /**
    * Convert the given hex encoded string to a multihash.
    * @param hash
    * @return
    */
  def fromHexString(hash: String): Buffer = js.native

  /**
    * Convert the given multihash to a base58 encoded string.
    * @param hash
    * @return
    */
  def toB58String(hash: Buffer): String = js.native

  /**
    * Convert the given base58 encoded string to a multihash.
    * @param in
    * @return
    */
  def fromB58String(in: String): Buffer = js.native


  /**
    * Decode a hash from the given multihash.
    * @param buf
    * @return
    */
  def decode(buf: Buffer): DecodedMultiHash = js.native

  /**
    * Encode a hash digest along with the specified function code.
    * @note the length is derived from the length of the digest itself.
    * @param digest
    * @param code
    * @param length
    * @return
    */
  def encode(digest: Buffer, code: String, length: Int): Buffer = js.native

  /**
    * Encode a hash digest along with the specified function code.
    * @note the length is derived from the length of the digest itself.
    * @param digest
    * @param code
    * @param length
    * @return
    */
  def encode(digest: Buffer, code: Int, length: Int): Buffer = js.native


  /**
    * Converts a hash function name into the matching code. If passed a number it will return the number if it's a valid code.
    * @param name
    * @return
    */
  def coerceCode(name: String): Int = js.native

  /**
    * Converts a hash function name into the matching code. If passed a number it will return the number if it's a valid code.
    * @param name
    * @return
    */
  def coerceCode(name: Int): Int = js.native


  /**
    * Checks wether a code is part of the app range
    * @param code
    * @return
    */
  def isAppCode(code: Int): Boolean = js.native

  /**
    * Checks whether a multihash code is valid.
    * @param code
    * @return
    */
  def isValidCode(code: Int): Boolean = js.native

  /**
    * Check if the given buffer is a valid multihash. Throws an error if it is not valid.
    * @param multihash
    */
  def validate(multihash: Buffer): Unit = js.native

}

@js.native
trait DecodedMultiHash extends js.Object {
  val code: Int = js.native
  val name: String = js.native
  val length: Int = js.native
  val digest: Buffer = js.native
}

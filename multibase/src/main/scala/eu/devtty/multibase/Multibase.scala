package eu.devtty.multibase

import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("multibase", JSImport.Namespace)
class Multibase extends js.Object {

  /**
    * Encode data with the specified base and add the multibase prefix.
    *
    * @param name - The multibase name.
    * @param buf - The data to be encoded.
    * @return {Buffer}
    */
  def encode(name: String, buf: Buffer): Buffer = js.native

  /**
    * Encode data with the specified base and add the multibase prefix.
    *
    * @param code - The multibase code number.
    * @param buf - The data to be encoded.
    * @return {Buffer}
    */
  def encode(code: Int, buf: Buffer): Buffer = js.native

  /**
    * Takes a buffer encoded with multibase header
    * decodes it and returns an object with the decoded buffer
    * and the encoded type
    *
    * @param buf
    * @return
    */
  def decode(buf: Buffer): DecodedMultibase = js.native

  /**
    * Takes a string encoded with multibase header
    * decodes it and returns an object with the decoded buffer
    * and the encoded type
    *
    * @param string
    * @return
    */
  def decode(string: String): DecodedMultibase = js.native

  /**
    * Is the given data multibase encoded?
    *
    * @param buf
    * @return
    */
  def isEncoded(buf: Buffer): Boolean = js.native

  /**
    * Is the given data multibase encoded?
    *
    * @param string
    * @return
    */
  def isEncoded(string: String): Boolean = js.native
}

@js.native
trait DecodedMultibase extends js.Object {
  val base: String = js.native
  val data: Buffer = js.native
}
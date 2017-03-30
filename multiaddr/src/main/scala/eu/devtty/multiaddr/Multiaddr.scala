package eu.devtty.multiaddr

import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("multiaddr", JSImport.Namespace)
object Multiaddr extends js.Object {
  //def fromNodeAddress - doc seems broken TODO: ask about things
  val protocols: MultiaddrProtocols = js.native
  def isMultiaddr(addr: Multiaddr): Boolean = js.native
}

@js.native
@JSImport("multiaddr", JSImport.Namespace)
class Multiaddr() extends js.Object {
  /**
    * Creates a multiaddr from a Buffer, String or another Multiaddr instance public key
    *
    * @param addr Needs to adhere to the address format of a multiaddr
    * @return
    */
  @inline
  def this(addr: String) = this()

  /**
    * Creates a multiaddr from a Buffer, String or another Multiaddr instance public key
    *
    * @param addr Needs to adhere to the address format of a multiaddr
    * @return
    */
  @inline
  def this(addr: Buffer) = this()

  /**
    * Creates a multiaddr from a Buffer, String or another Multiaddr instance public key
    *
    * @param addr
    * @return
    */
  @inline
  def this(addr: Multiaddr) = this()

  def buffer: Buffer = js.native

  override def toString: String = js.native

  /**
    * Returns Multiaddr as a convinient options object to be used with net.createConnection
    * @return
    */
  def toOptions: MultiaddrOptions = js.native

  /**
    * Returns Multiaddr as a human-readable string
    * @return
    */
  def inspect: String = js.native

  /**
    * Returns the protocols the Multiaddr is defined with, as an array of objects,
    * in left-to-right order. Each object contains the protocol code, protocol name,
    * and the size of its address space in bits.
    * @return
    */
  def protos: Array[MultiaddrProto] = js.native

  /**
    * @return The codes of the protocols in left-to-right order.
    */
  def protoCodes: Array[Int] = js.native

  /**
    * @return The names of the protocols in left-to-right order.
    */
  def protoNames: Array[String] = js.native

  /**
    * Returns a tuple of parts
    * @return array of codes of protocol and contents of address
    */
  def tuples: Array[(Int, Buffer)] = js.native

  /**
    * Returns a tuple of parts
    * @return array of names of protocol and contents of address
    */
  def stringTuples: Array[(String, Buffer)] = js.native

  /**
    * Encapsulates a Multiaddr in another Multiaddr
    * @param addr Multiaddr to add into this Multiaddr
    * @return
    */
  def encapsulate(addr: Multiaddr): Multiaddr = js.native

  /**
    * Decapsulates a Multiaddr from another Multiaddr
    * @param addr Multiaddr to remove from this Multiaddr
    * @return
    */
  def decapsulate(addr: Multiaddr): Multiaddr = js.native

  override def equals(obj: scala.Any): Boolean = js.native

  /**
    * Gets a Multiaddrs node-friendly address object. Note that protocol information is left out:
    * in Node (and most network systems) the protocol is unknowable given only the address.
    *
    * Has to be a ThinWaist Address, otherwise throws error
    *
    * @throws Error error if Multiaddr is not a Thin Waist address
    * @return
    */
  def nodeAddress: MultiaddrNodeAddress = js.native

  /**
    * Returns if a Multiaddr is a Thin Waist address or not.
    *
    * Thin Waist is if a Multiaddr adheres to the standard combination of:
    *
    * `{IPv4, IPv6}/{TCP, UDP}`
    * @return
    */
  def isThinWaistAddress: Boolean = js.native

  /**
    * Converts a "stupid string" into a Multiaddr.
    *
    * Stupid string format:
    *
    * * &lt;proto&gt;&lt;IPv&gt;://&lt;IP Addr&gt;[:&lt;proto port&gt;]
    * * udp4://1.2.3.4:5678
    * @param str
    * @return
    */
  def fromStupidString(str: String): Unit = js.native

}

@js.native
trait MultiaddrProtocols extends js.Object {
  val table: js.Array[js.Array[js.Any]] = js.native
  val names: js.Dictionary[MultiaddrType] = js.native
  val codes: js.Array[MultiaddrType] = js.native
}

@js.native
trait MultiaddrType extends js.Object {
  val code: Int = js.native
  val size: Int = js.native
  val name: String = js.native
  val resolvable: Boolean = js.native
}

@js.native
trait MultiaddrProto extends js.Object {
  val code: Int = js.native
  val size: Int = js.native
  val name: String = js.native
}

@js.native
trait MultiaddrOptions extends js.Object {
  val family: String = js.native
  val host: String = js.native
  val transport: String = js.native
  val port: String = js.native
}

@js.native
trait MultiaddrNodeAddress extends js.Object {
  val family: String = js.native
  val address: String = js.native
  val port: String = js.native
}

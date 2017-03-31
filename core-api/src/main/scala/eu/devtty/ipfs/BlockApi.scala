package eu.devtty.ipfs

import eu.devtty.cid.CID
import io.scalajs.nodejs.buffer.Buffer

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

trait BlockApi {
  /**
    * Get a raw IPFS block.
    * @param cid
    * @return
    */
  def get(cid: CID): Future[Block]

  /**
    * Get a raw IPFS block.
    * @param cid The base58 encoded version of the multihash
    * @return
    */
  def get(cid: String): Future[Block]

  /**
    * Get a raw IPFS block.
    * @param cid the raw Buffer of the cid
    * @return
    */
  def get(cid: Buffer): Future[Block]

  /**
    * Stores input as an IPFS block.
    * @param block
    * @return
    */
  def put(block: Block): Future[Block]

  /**
    * Stores input as an IPFS block.
    * @param block
    * @return
    */
  def put(block: Buffer): Future[Block]

  /**
    * Print information of a raw IPFS block
    * @param cid
    * @return
    */
  def stat(cid: CID): Future[BlockStat]

  /**
    * Print information of a raw IPFS block
    * @param cid the toString version of the multihash (or of an encoded version)
    * @return
    */
  def stat(cid: String): Future[BlockStat]

  /**
    * Print information of a raw IPFS block
    * @param cid the raw Buffer of the multihash (or of and encoded version)
    * @return
    */
  def stat(cid: Buffer): Future[BlockStat]
}

@js.native
trait BlockStat extends js.Object {
  val key: String = js.native
  val size: Int = js.native //Long appears to be broken by scalajs, may need some deeper lok
}

@js.native
@JSImport("ipfs-block", JSImport.Namespace)
class Block(val data: Buffer, val cid: CID) extends js.Object

@js.native
@JSImport("ipfs-block", JSImport.Namespace)
object Block extends js.Object {
  def isBlock(other: js.Any): Boolean = js.native
}

package eu.devtty.ipfs

import eu.devtty.cid.CID
import io.scalajs.nodejs.buffer.Buffer

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

trait BlockApi {
  def get(cid: CID): Future[Block]
  def get(cid: String): Future[Block]
  def get(cid: Buffer): Future[Block]

  def put(block: Block, cid: CID): Future[Block]
  def put(block: Buffer, cid: CID): Future[Block]
  def put(block: Block, cid: String): Future[Block]
  def put(block: Buffer, cid: String): Future[Block]
  def put(block: Block, cid: Buffer): Future[Block]
  def put(block: Buffer, cid: Buffer): Future[Block]

  def stat(cid: CID): Future[BlockStat]
  def stat(cid: String): Future[BlockStat]
  def stat(cid: Buffer): Future[BlockStat]
}

@js.native
trait BlockStat extends js.Object {
  val key: String = js.native
  val size: Int = js.native //Long appears to be broken by scalajs, may need some deeper lok
}

@js.native
@JSImport("ipfs-block", JSImport.Namespace)
class Block(data: Buffer, cid: CID) extends js.Object

@js.native
@JSImport("ipfs-block", JSImport.Namespace)
object Block extends js.Object {
  def isBlock(other: js.Any): Boolean = js.native
}

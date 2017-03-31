package eu.devtty.ipfs.jsnode

import eu.devtty.cid.CID
import eu.devtty.ipfs.jsnode.util.FutureCallErr1
import eu.devtty.ipfs.{Block, BlockApi, BlockStat}
import io.scalajs.nodejs.buffer.Buffer

import scala.concurrent.Future

class JsBlockApi(implicit val node: raw.JsIpfs) extends BlockApi {
  @inline
  private def block = node.block

  def get(cid: CID): Future[Block] = FutureCallErr1(cb => block.get(cid, cb))
  def get(cid: String): Future[Block] = FutureCallErr1(cb => block.get(cid, cb))
  def get(cid: Buffer): Future[Block] = FutureCallErr1(cb => block.get(cid, cb))

  def put(data: Block): Future[Block] = FutureCallErr1(cb => block.put(data, cb))
  def put(data: Buffer): Future[Block] = FutureCallErr1(cb => block.put(data, cb))

  def stat(cid: CID): Future[BlockStat] = FutureCallErr1(cb => block.stat(cid, cb))
  def stat(cid: String): Future[BlockStat] = FutureCallErr1(cb => block.stat(cid, cb))
  def stat(cid: Buffer): Future[BlockStat] = FutureCallErr1(cb => block.stat(cid, cb))
}

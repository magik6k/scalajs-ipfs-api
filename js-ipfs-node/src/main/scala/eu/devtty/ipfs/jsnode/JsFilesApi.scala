package eu.devtty.ipfs.jsnode

import eu.devtty.ipfs._
import eu.devtty.ipfs.jsnode.util.FutureCallErr1
import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.nodejs.stream

import scala.concurrent.Future

class JsFilesApi(implicit val node: raw.JsIpfs) extends FilesApi {
  @inline
  private def files = node.files

  def add(data: Array[NamedBuffer], options: DagImporterOptions): Future[Array[AddResult]] = FutureCallErr1(cb => files.add(data, options, { (e,d) => cb(e, d.toArray)}))
  def add(data: Array[NamedReadable], options: DagImporterOptions): Future[Array[AddResult]] = FutureCallErr1(cb => files.add(data, options, { (e,d) => cb(e, d.toArray)}))
  def add(data: Buffer, options: DagImporterOptions): Future[Array[AddResult]] = FutureCallErr1(cb => files.add(data, options, { (e,d) => cb(e, d.toArray)}))
  def add(data: stream.Readable, options: DagImporterOptions): Future[Array[AddResult]] = FutureCallErr1(cb => files.add(data, options, { (e,d) => cb(e, d.toArray)}))

  def add(data: Array[NamedBuffer]): Future[Array[AddResult]] = FutureCallErr1(cb => files.add(data, { (e,d) => cb(e, d.toArray)}))
  def add(data: Array[NamedReadable]): Future[Array[AddResult]] = FutureCallErr1(cb => files.add(data, { (e,d) => cb(e, d.toArray)}))
  def add(data: Buffer): Future[Array[AddResult]] = FutureCallErr1(cb => files.add(data, { (e,d) => cb(e, d.toArray)}))
  def add(data: stream.Readable): Future[Array[AddResult]] = FutureCallErr1(cb => files.add(data, { (e,d) => cb(e, d.toArray)}))


  def createAddStream(options: DagImporterOptions): Future[AddStream] = FutureCallErr1(cb => files.createAddStream(options, cb))

  def cat(multihash: Buffer): Future[stream.Readable] = files.cat(multihash).toFuture
  def cat(multihash: String): Future[stream.Readable] = files.cat(multihash).toFuture
  def get(multihash: Buffer): Future[stream.Readable] = files.get(multihash).toFuture
  def get(multihash: String): Future[stream.Readable] = files.get(multihash).toFuture
}

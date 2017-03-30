package eu.devtty.ipfs

import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.nodejs.stream.{Readable, Transform}

import scala.concurrent.Future
import scala.scalajs.js


trait FilesApi {
  /**
    * Add files and data to IPFS
    * @param data
    * @param options
    * @return
    */
  def add(data: Array[NamedBuffer], options: DagImporterOptions = null): Future[Array[AddResult]]

  /**
    * Add files and data to IPFS
    * @param data
    * @param options
    * @return
    */
  def add(data: Array[NamedReadable], options: DagImporterOptions = null): Future[Array[AddResult]]

  /**
    * Add files and data to IPFS
    * @param data
    * @param options
    * @return
    */
  def add(data: Buffer, options: DagImporterOptions = null): Future[Array[AddResult]]

  /**
    * Add files and data to IPFS
    * @param data
    * @param options
    * @return
    */
  def add(data: Readable, options: DagImporterOptions = null): Future[Array[AddResult]]


  /**
    * Add files and data to IPFS using a transform stream.
    * @param options
    * @return
    */
  def createAddStream(options: DagImporterOptions = null): Future[AddStream]

  /**
    * Streams the file at the given IPFS multihash
    * @param multihash raw Buffer of the multihash
    * @return
    */
  def cat(multihash: Buffer): Future[Readable]

  /**
    * Streams the file at the given IPFS multihash
    * @param multihash base58 encoded version of the multihash
    * @return
    */
  def cat(multihash: String): Future[Readable]

  /**
    * Get stream of [[FilesApi.NamedReadable]] containing files from IPFS
    * @param multihash raw Buffer of the multihash
    * @return
    */
  def get(multihash: Buffer): Future[Readable]

  /**
    * Get stream of [[FilesApi.NamedReadable]] containing files from IPFS
    * @param multihash base58 encoded version of the multihash
    * @return
    */
  def get(multihash: String): Future[Readable]

}

object NamedBuffer {
  //TODO: maybe: implicit converter from Seq[(String, Buffer)]
  def apply(path: String, content: Buffer): NamedBuffer =
    js.Dynamic.literal(path = path, content = content).asInstanceOf[NamedBuffer]
}

@js.native
trait NamedBuffer extends js.Object {
  val path: String = js.native
  val content: Buffer = js.native
}

object NamedReadable {
  //TODO: maybe: implicit converter from Seq[(String, Readable)]
  def apply(path: String, content: Readable): NamedReadable =
    js.Dynamic.literal(path = path, content = content).asInstanceOf[NamedReadable]
}

@js.native
trait NamedReadable extends js.Object {
  val path: String = js.native
  val content: Readable = js.native
}

@js.native
trait AddResult extends js.Object {
  val path: String = js.native
  val hash: String = js.native
  val size: Int = js.native //TODO: Long? caused problems somewhere else, need testing
}

@js.native
trait AddStream extends Transform {
  def write(file: NamedBuffer) = js.native
  def write(file: NamedReadable) = js.native
}


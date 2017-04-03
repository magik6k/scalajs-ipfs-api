package eu.devtty.ipfs.jsnode.raw

import eu.devtty.ipfs._
import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.nodejs.stream

import scala.scalajs.js
import scala.scalajs.js.Promise

@js.native
trait JsFilesApi extends js.Object {
  def add(data: Array[NamedBuffer], options: DagImporterOptions, callback: js.Function2[String, js.Array[AddResult], _]): Unit = js.native
  def add(data: Array[NamedReadable], options: DagImporterOptions, callback: js.Function2[String, js.Array[AddResult], _]): Unit = js.native
  def add(data: Buffer, options: DagImporterOptions, callback: js.Function2[String, js.Array[AddResult], _]): Unit = js.native
  def add(data: stream.Readable, options: DagImporterOptions, callback: js.Function2[String, js.Array[AddResult], _]): Unit = js.native
  def add(data: Array[NamedBuffer], callback: js.Function2[String, js.Array[AddResult], _]): Unit = js.native
  def add(data: Array[NamedReadable], callback: js.Function2[String, js.Array[AddResult], _]): Unit = js.native
  def add(data: Buffer, callback: js.Function2[String, js.Array[AddResult], _]): Unit = js.native
  def add(data: stream.Readable, callback: js.Function2[String, js.Array[AddResult], _]): Unit = js.native

  def createAddStream(options: DagImporterOptions, callback: js.Function2[String, AddStream, _]): Unit = js.native

  def cat(multihash: Buffer): Promise[stream.Readable] = js.native
  def cat(multihash: String): Promise[stream.Readable] = js.native
  def get(multihash: Buffer): Promise[stream.Readable] = js.native
  def get(multihash: String): Promise[stream.Readable] = js.native
}

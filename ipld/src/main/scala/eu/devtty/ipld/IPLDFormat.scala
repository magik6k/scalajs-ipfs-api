package eu.devtty.ipld

import eu.devtty.cid.CID
import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js

@js.native
trait IPLDFormat extends js.Object {
  val util: IPLDFormatUtils = js.native
  val resolver: IPLDFormatResolver = js.native
}

@js.native
trait DagNode extends js.Object {

}

@js.native
trait IPLDFormatUtils extends js.Object {
  /**
    * Serializes a dagNode of an IPLD format into its binary format
    * @param dagNode
    * @param callback
    */
  def serialize(dagNode: DagNode, callback: js.Function2[js.Error, Buffer, _]): Unit = js.native

  /**
    * Deserializes a binary blob into the instance
    * @param binaryBlob
    * @param callback
    */
  def deserialize(binaryBlob: Buffer, callback: js.Function2[js.Error, CID, _]): Unit = js.native

  /**
    * get the CID of the dagNode
    * @param dagNode
    * @param callback
    */
  def cid(dagNode: DagNode, callback: js.Function2[js.Error, CID, _])
}

@js.native
trait IPLDFormatResolver extends js.Object {
  /**
    * resolves a path in block, returns the value and or a link and the partial missing path.
    * This way the IPLD Resolver can fetch the link and continue to resolve.
    * @param binaryBlob
    * @param path
    * @param callback
    */
  def resolve(binaryBlob: Buffer, path: String, callback: js.Function2[js.Error, IPLDResolveResult, _]): Unit = js.native //TODO: check if path is a string

  /**
    * Returns all the paths available in this block.
    * @param binaryBlob
    * @param options
    * @param callback
    */
  def tree(binaryBlob: Buffer, options: IPLDTreeOptions, callback: js.Function2[js.Error, js.Array[js.Dictionary[String]], _]): Unit = js.native

  /**
    * returns an IPLD Link of a given path, if it is a valid link, false otherwise
    * @param binaryBlob
    * @param path
    * @param callback
    */
  def isLink(binaryBlob: Buffer, path: String, callback: js.Function2[js.Error, js.Dictionary[CID], _]): Unit = js.native
}

@js.native
trait IPLDResolveResult extends js.Object {
  //TODO: https://github.com/ipld/interface-ipld-format#resolverresolvebinaryblob-path-callback
}

object IPLDTreeOptions {
  def apply(level: js.UndefOr[Int] = js.undefined, values: js.UndefOr[Boolean]): IPLDTreeOptions =
    js.Dynamic.literal(level = level, values = values).asInstanceOf[IPLDTreeOptions]
}

@js.native
trait IPLDTreeOptions extends js.Object {
  /**
    * 0 to n - how many levels deep should the traversal go.
    */
  val level: js.UndefOr[Int] = js.native

  /**
    * resolve the values (defaults to false)
    */
  val values: js.UndefOr[Boolean] = js.native
}


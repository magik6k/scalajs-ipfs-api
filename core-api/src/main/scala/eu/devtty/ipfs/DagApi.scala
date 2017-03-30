package eu.devtty.ipfs

import scala.scalajs.js

class DagApi {
  //TODO
}

object DagImporterOptions {
  def apply(wrap: Boolean = false,
            shardSplitThreshold: Int = 1000,
            chunker: String = "fixed",
            chunkerOptions: ChunkerOptions = ChunkerOptions(),
            strategy: String = "balanced",
            maxChildrenPerNode: Int = 174,
            layerRepeat: Int = 4,
            reduceSingleLeafToSelf: Boolean = false,
            dirBuilder: DirBuilderOptions = DirBuilderOptions()): DagImporterOptions = js.Dynamic.literal(
    wrap = wrap,
    shardSplitThreshold = shardSplitThreshold,
    chunker = chunker,
    chunkerOptions = chunkerOptions,
    strategy = strategy,
    maxChildrenPerNode = maxChildrenPerNode,
    layerRepeat = layerRepeat,
    reduceSingleLeafToSelf = reduceSingleLeafToSelf,
    dirBuilder = dirBuilder
  ).asInstanceOf[DagImporterOptions]
}

@js.native
trait DagImporterOptions extends js.Object {
  /**
    * If true, a wrapping node will be created (defaults to false)
    */
  val wrap: Boolean = js.native

  /**
    * The number of directory entries above which we decide to use a
    * sharding directory builder (instead of the default flat one)
    * (positive integer, defaults to 1000)
    */
  val shardSplitThreshold: Int = js.native

  /**
    * The chunking strategy. Now only supports "fixed"
    */
  val chunker: String = js.native

  /**
    * The options for the chunker
    */
  val chunkerOptions: ChunkerOptions = js.native

  /**
    * The DAG builder strategy name. Supports:
    * * flat: flat list of chunks
    * * balanced: builds a balanced tree
    * * trickle: builds a trickle tree
    */
  val strategy: String = js.native

  /**
    * (positive integer, defaults to 174) The maximum children per node for the balanced and trickle DAG builder strategies
    */
  val maxChildrenPerNode: Int = js.native

  /**
    * (positive integer, defaults to 4): (only applicable to the trickle DAG builder strategy).
    * The maximum repetition of parent nodes for each layer of the tree.
    */
  val layerRepeat: Int = js.native

  /**
    * (boolean, defaults to false): optimization for, when reducing a set of nodes with one node, reduce it to that node;
    */
  val reduceSingleLeafToSelf: Boolean = js.native

  /**
    * The options for the directory builder
    */
  val dirBuilder: DirBuilderOptions = js.native
}

object ChunkerOptions {
  def apply(maxChunkSize: Int = 262144): ChunkerOptions =
    js.Dynamic.literal(maxChunkSize = maxChunkSize).asInstanceOf[ChunkerOptions]
}

@js.native
trait ChunkerOptions extends js.Object {
  /**
    * (positive integer, defaults to 262144): the maximum chunk size for the fixed chunker.
    */
  val maxChunkSize: Int = js.native
}

object DirBuilderOptions {
  def apply(hamt: HAMTDirBuilderOptions = HAMTDirBuilderOptions()): DirBuilderOptions =
    js.Dynamic.literal(hamt = hamt).asInstanceOf[DirBuilderOptions]
}

@js.native
trait DirBuilderOptions extends js.Object {
  /**
    * The options for the HAMT sharded directory builder
    */
  val hamt: HAMTDirBuilderOptions = js.native
}

object HAMTDirBuilderOptions {
  def apply(bits: Int = 5): HAMTDirBuilderOptions =
    js.Dynamic.literal(bits = bits).asInstanceOf[HAMTDirBuilderOptions]
}

@js.native
trait HAMTDirBuilderOptions extends js.Object {
  /**
    * (positive integer, defaults to 5): the number of bits at each bucket of the HAMT
    */
  val bits: Int = js.native
}

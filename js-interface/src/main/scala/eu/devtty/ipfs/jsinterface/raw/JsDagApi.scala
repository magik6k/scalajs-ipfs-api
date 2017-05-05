package eu.devtty.ipfs.jsinterface.raw

import eu.devtty.cid.CID
import eu.devtty.ipfs.{DagGetOptions, DagGetResult, DagPutOptions, DagTreeOptions}
import eu.devtty.ipld.DagNode

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait JsDagApi extends js.Object {
  def put(dagNode: DagNode | js.Any, options: DagPutOptions): js.Promise[CID] = js.native

  def get(cid: CID | String, path: String, options: DagGetOptions): js.Promise[DagGetResult] = js.native
  def get(cid: CID | String, options: DagGetOptions): js.Promise[DagGetResult] = js.native
  def get(cid: CID | String, path: String): js.Promise[DagGetResult] = js.native
  def get(cid: CID | String): js.Promise[DagGetResult] = js.native

  def tree(cid: CID | String, path: String, options: DagTreeOptions): js.Promise[js.Array[String]] = js.native
}

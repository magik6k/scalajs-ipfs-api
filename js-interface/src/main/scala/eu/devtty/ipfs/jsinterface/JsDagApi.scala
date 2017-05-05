package eu.devtty.ipfs.jsinterface

import eu.devtty.cid.CID
import eu.devtty.ipfs._
import eu.devtty.ipld.DagNode

import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.|
import scala.concurrent.ExecutionContext.Implicits.global

class JsDagApi(implicit val node: raw.JsIpfsInterface) extends DagApi {
  @inline
  private def dag = node.dag

  def put(dagNode: |[DagNode, js.Any], options: DagPutOptions): Future[CID] = dag.put(dagNode, options).toFuture

  def get(cid: |[CID, String], path: String, options: DagGetOptions): Future[DagGetResult] = dag.get(cid, path, options).toFuture
  def get(cid: |[CID, String], options: DagGetOptions): Future[DagGetResult] = dag.get(cid, options).toFuture
  def get(cid: |[CID, String], path: String): Future[DagGetResult] = dag.get(cid, path).toFuture
  def get(cid: |[CID, String]): Future[DagGetResult] = dag.get(cid).toFuture

  def tree(cid: |[CID, String], path: String, options: DagTreeOptions): Future[Array[String]] = dag.tree(cid, path, options).toFuture.map(_.toArray)
}

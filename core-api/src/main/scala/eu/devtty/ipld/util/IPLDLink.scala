package eu.devtty.ipld.util

import eu.devtty.ipfs.{DagGetResult, IpfsNode}

import scala.concurrent.Future
import scala.scalajs.js

@js.native
trait IPLDLink extends js.Object {
  val / : String = js.native

  @inline
  def get(implicit ipfs: IpfsNode): Future[DagGetResult] = ipfs.dag.get(/)
}


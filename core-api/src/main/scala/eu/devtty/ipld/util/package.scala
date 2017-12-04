package eu.devtty.ipld

import eu.devtty.ipfs.{DagGetResult, IpfsNode}

import scala.concurrent.Future

package object util {
  implicit class IPLDLinkUtils(val link: IPLDLink) extends AnyVal {
    @inline
    def get(implicit ipfs: IpfsNode): Future[DagGetResult] = {
        ipfs.dag.get(link./)
    }
  }
}

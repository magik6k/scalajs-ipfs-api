package eu.devtty.ipfs.jsnode

import eu.devtty.cid.CID
import eu.devtty.multihash.MultiHash
import io.scalajs.nodejs.process
import utest._
import utest.framework.{Test, Tree}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js
import scala.scalajs.js.timers._

object JsIpfsNodeTest extends TestSuite {
  lazy val node: Future[JsIpfs] = {
    val n = new JsIpfs({})
    n.on("stop").foreach { _ => process.exit(0) }
    n.on("start").map { _ => n }
  }

  override val tests: Tree[Test] = this {
    'nodeOnline{
      node.map{ n =>
        assert(n.isOnline)
      }
    }

    'nodeVersion{
      node.flatMap { n =>
        n.version.map { version =>
          assert(version != null)
          println(s"Node version: ${version.version}")
        }
      }
    }

    'nodeId{
      node.flatMap { n =>
        n.id.map { pid =>
          println(s"Node ID: ${pid.id}")
          val decoded = MultiHash.decode(MultiHash.fromB58String(pid.id))
          decoded.digest.length ==> decoded.length
        }
      }
    }

    'blockStat{
      node.flatMap { n =>
        n.block.stat(new CID("QmZULkCELmmk5XNfCgTnCyFgAVxBRBXyDHGGMVoLFLiXEN"))
      } map { stat =>
        js.Dynamic.global.console.log(js.Dynamic.global.JSON.stringify(stat))
        stat.key ==> "QmZULkCELmmk5XNfCgTnCyFgAVxBRBXyDHGGMVoLFLiXEN"
        stat.size ==> 14
      }
    }

    'cleanupAndStop{
      node.foreach { n =>
        setTimeout(250) { //HACK!!
          n.stop()
        }
      }
    }

  }
}

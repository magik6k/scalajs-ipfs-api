package eu.devtty.ipfs.jsnode

import eu.devtty.cid.CID
import eu.devtty.ipfs.Block
import eu.devtty.multihash.MultiHash
import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.nodejs.process
import utest._
import utest.framework.{Test, Tree}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.scalajs.js.timers._

object JsIpfsNodeTest extends TestSuite {
  lazy val node: Future[JsIpfs] = {
    val n = new JsIpfs({})
    n.on("stop").foreach { _ => process.exit(0) }
    n.on("start").map { _ => n }
  }

  override val tests: Tree[Test] = this {
    'node {
      'online {
        node.map { n =>
          assert(n.isOnline)
        }
      }

      'version {
        node.flatMap { n =>
          n.version.map { version =>
            assert(version != null)
            println(s"Node version: ${version.version}")
          }
        }
      }

      'id {
        node.flatMap { n =>
          n.id.map { pid =>
            println(s"Node ID: ${pid.id}")
            val decoded = MultiHash.decode(MultiHash.fromB58String(pid.id))
            decoded.digest.length ==> decoded.length
          }
        }
      }
    }

    'block{
      'put{
        val expectedHash = "QmPv52ekjS75L4JmHpXVeuJ5uX2ecSfSZo88NSyxwA3rAQ"
        val cid = new CID(expectedHash)
        val blob = new Block(Buffer.from("blorb"), cid)

        node.flatMap { n =>
          n.block.put(blob).map{ b =>
            MultiHash.toB58String(b.cid.buffer) ==> expectedHash
            assert(b.cid.equals(cid))
          }
        }
      }

      'stat{
        node.flatMap { n =>
          n.block.stat(new CID("QmPv52ekjS75L4JmHpXVeuJ5uX2ecSfSZo88NSyxwA3rAQ"))
        } map { stat =>
          stat.key ==> "QmPv52ekjS75L4JmHpXVeuJ5uX2ecSfSZo88NSyxwA3rAQ"
          stat.size ==> 5
        }
      }

      'get{
        node.flatMap { n =>
          n.block.get(new CID("QmPv52ekjS75L4JmHpXVeuJ5uX2ecSfSZo88NSyxwA3rAQ"))
        }.map { b =>
          assert(b.data.equals(Buffer.from("blorb")))
        }
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

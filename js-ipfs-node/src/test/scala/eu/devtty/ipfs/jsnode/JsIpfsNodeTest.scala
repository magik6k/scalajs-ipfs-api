package eu.devtty.ipfs.jsnode

import eu.devtty.cid.CID
import eu.devtty.ipfs.{AddResult, Block, DagImporterOptions}
import eu.devtty.multihash.MultiHash
import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.nodejs.process
import utest._
import utest.framework.{Test, Tree}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs.js.JavaScriptException
import scala.scalajs.js.timers._
import scala.util.{Failure, Success}

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
            println(s"IPFS Node version: ${version.version}")
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

    'files{
      'add{
        node.flatMap { n =>
          n.files.add(Buffer.from("blorb"))
        }.map { res =>
          res.head.hash ==> "QmPpojvJhVQNREZF1WcYre1rUDZX2mN81WUkHne6QwNuoR"
          res.head.size ==> 13
        }
      }

      'addStream{
        node.flatMap { n =>
          n.files.createAddStream(DagImporterOptions())
        }.flatMap { stream =>
          val p = Promise[String]
          stream.on("data", { file: AddResult => p.success(file.hash) })

          stream.writeAsync(Buffer.from("Hello!\n")).future flatMap(_ => p.future)
        }.map { hash =>
          hash ==> "QmenmPhbFCbn2BGkGbNCjxFp5qdXnuhWL9Lqt6GjFq1NUK"
        }
      }

      'cat{
        node.flatMap { n =>
          n.files.cat("QmenmPhbFCbn2BGkGbNCjxFp5qdXnuhWL9Lqt6GjFq1NUK")
        }.flatMap { stream =>
          val p = Promise[String]
          val data = new StringBuilder
          stream.on("data", { chunk: Buffer =>
            data ++= chunk.toString()
          })

          stream.on("end", { () =>
            p.success(data.toString)
          })

          p.future
        }.map { data =>
          data ==> "Hello!\n"
        }
      }
    }

    'config{
      'set{
        node.flatMap { n =>
          n.config.set("test", "test value")
        }.andThen {
          case Success(_) => assert(true)
          case Failure(f) => throw f
        }
      }

      'get{
        node.flatMap { n =>
          n.config.get("test")
        }.andThen {
          case Success(o) => o ==> "test value"
          case Failure(f) => throw f
        }
      }
    }

    'swarm {
      'addrs{
        node.flatMap { n =>
          n.swarm.addrs
        } map { addrs =>
          assert(addrs.length > 0)
        }
      }

      'peers{
        node.flatMap { n =>
          n.swarm.peers()
        } map { peers =>
          assert(peers.length > 0)
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

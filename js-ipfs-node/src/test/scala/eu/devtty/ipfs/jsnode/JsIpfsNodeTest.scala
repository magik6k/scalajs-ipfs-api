package eu.devtty.ipfs.jsnode

import eu.devtty.cid.CID
import eu.devtty.ipfs.{AddResult, Block, DagImporterOptions}
import eu.devtty.multiaddr.Multiaddr
import eu.devtty.multihash.MultiHash
import io.scalajs.nodejs.buffer.Buffer
import io.scalajs.nodejs.process
import utest._
import utest.framework.{Test, Tree}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import scala.scalajs.js.timers._
import scala.util.{Failure, Success}

object JsIpfsNodeTest extends TestSuite {
  lazy val node1: Future[JsIpfs] = {
    val n = new JsIpfs(js.Dynamic.literal(
      repo = "/tmp/scalajs-ipfs-test1",
      config = js.Dynamic.literal(
        Addresses = js.Dynamic.literal(
          Swarm = js.Array(
            "/ip4/127.0.0.1/tcp/4101"
          )
        )
      )
    ))
    n.on("stop").foreach { _ => process.exit(0) }
    n.on("start").map { _ => n }
  }

  lazy val node2: Future[JsIpfs] = {
    val n = new JsIpfs(js.Dynamic.literal(
      repo = "/tmp/scalajs-ipfs-test2",
      config = js.Dynamic.literal(
        Addresses = js.Dynamic.literal(
          Swarm = js.Array(
            "/ip4/127.0.0.1/tcp/4102"
          )
        )
      )
    ))
    n.on("stop").foreach { _ => process.exit(0) }
    n.on("start").map { _ => n }
  }

  lazy val nodes = Future.sequence(Seq(node1, node2))

  override val tests: Tree[Test] = this {
    'node {
      'online {
        nodes.map { n =>
          assert(n.forall(_.isOnline))
        }
      }

      'version {
        nodes.flatMap { n =>
          Future.sequence(n.map(_.version)) map { version =>
            assert(!version.contains(null))
          }
        }
      }

      'id {
        nodes.flatMap { n =>
          Future.sequence(n.map(_.id)).map { pid =>
            pid.zipWithIndex.foreach {
              case (id, idx) => println(s"Node $idx ID: ${id.id}")
            }

            assert(pid.map(i => MultiHash.decode(MultiHash.fromB58String(i.id)))
              .forall(d => d.digest.length == d.length))
          }
        }
      }
    }

    'block{
      'put{
        val expectedHash = "QmPv52ekjS75L4JmHpXVeuJ5uX2ecSfSZo88NSyxwA3rAQ"
        val cid = new CID(expectedHash)
        val blob = new Block(Buffer.from("blorb"), cid)

        node1.flatMap { n =>
          n.block.put(blob).map{ b =>
            MultiHash.toB58String(b.cid.buffer) ==> expectedHash
            assert(b.cid.equals(cid))
          }
        }
      }

      'stat{
        node1.flatMap { n =>
          n.block.stat(new CID("QmPv52ekjS75L4JmHpXVeuJ5uX2ecSfSZo88NSyxwA3rAQ"))
        } map { stat =>
          stat.key ==> "QmPv52ekjS75L4JmHpXVeuJ5uX2ecSfSZo88NSyxwA3rAQ"
          stat.size ==> 5
        }
      }

      'get{
        node1.flatMap { n =>
          n.block.get(new CID("QmPv52ekjS75L4JmHpXVeuJ5uX2ecSfSZo88NSyxwA3rAQ"))
        }.map { b =>
          assert(b.data.equals(Buffer.from("blorb")))
        }
      }
    }

    'files{
      'add{
        node1.flatMap { n =>
          n.files.add(Buffer.from("blorb"))
        }.map { res =>
          res.head.hash ==> "QmPpojvJhVQNREZF1WcYre1rUDZX2mN81WUkHne6QwNuoR"
          res.head.size ==> 13
        }
      }

      'addStream{
        node1.flatMap { n =>
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
        node1.flatMap { n =>
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
        node1.flatMap { n =>
          n.config.set("test", "test value")
        }.andThen {
          case Success(_) => assert(true)
          case Failure(f) => throw f
        }
      }

      'get{
        node1.flatMap { n =>
          n.config.get("test")
        }.andThen {
          case Success(o) => o ==> "test value"
          case Failure(f) => throw f
        }
      }
    }

    'swarm {
      'addrs{
        node1.flatMap { n =>
          n.swarm.addrs
        } map { addrs =>
          assert(addrs.length > 0)
        }
      }

      'peers{
        node1.flatMap { n =>
          n.swarm.peers()
        } map { peers =>
          assert(peers.length > 0)
        }
      }

      'verifyNoConnection{
        node2.zip(node1.flatMap(_.id.map(_.addresses(0)))).flatMap { case (n2, addr) =>
          n2.swarm.peers().map(connected => connected.exists(_.addr.equals(new Multiaddr(addr))))
        }.map(res => assert(!res))
      }

      'connect{
        node1.zip(node2.flatMap(_.id.map(_.addresses(0)))).flatMap { case (n1, addr) =>
          n1.swarm.connect(new Multiaddr(addr))
        } andThen {
          case Success(_) => assert(true)
          case Failure(e) => throw e
        }
      }

      'waitConnect{ //HACK!!
        val p = Promise[Unit]
        setTimeout(300) { p.success() }
        p.future
      }

      'verifyConnection2to1{
        node2.zip(node1.flatMap(_.id.map(_.addresses(0)))).flatMap { case (n2, addr) =>
          n2.swarm.peers().map(peers => peers.exists(_.addr.equals(new Multiaddr(addr))))
        }.map(res => assert(res))
      }

      'verifyConnection1to2{
        node1.zip(node2.flatMap(_.id.map(_.addresses(0)))).flatMap { case (n1, addr) =>
          n1.swarm.peers().map(peers => peers.exists(_.addr.equals(new Multiaddr(addr))))
        }.map(res => assert(res))
      }
    }

    'cleanupAndStop{
      node1.foreach { n =>
        setTimeout(250) { //HACK!!
          n.stop()
        }
      }
    }

  }
}

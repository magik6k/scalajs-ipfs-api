package eu.devtty.multihash

import io.scalajs.nodejs.buffer.Buffer
import utest._
import utest.framework.{Test, Tree}

object MultihashTest extends TestSuite {
  override val tests: Tree[Test] = this {
    'MultihashB58Deserialize{
      val buf = MultiHash.fromB58String("QmejvEPop4D7YUadeGqYWmZxHhLc4JBUCzJJHWMzdcMe2y")
      buf.toHexString ==> "1220f3b0e682d79b8b7a2c216d62ace28c5746a548218c77b556ec932f3a64b914b6"
    }

    'decode{
      val buf = Buffer.from("1220f3b0e682d79b8b7a2c216d62ace28c5746a548218c77b556ec932f3a64b914b6", "hex")
      val decoded = MultiHash.decode(buf)

      decoded.code ==> 18
      decoded.length ==> 32
      decoded.name ==> "sha2-256"
    }
  }
}

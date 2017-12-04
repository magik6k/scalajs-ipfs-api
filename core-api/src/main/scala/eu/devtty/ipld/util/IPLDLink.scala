package eu.devtty.ipld.util

import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
trait IPLDLink extends js.Object {
  val `/` : String = js.native
}

object IPLDLink {
  def apply(cid: String): IPLDLink = js.Dynamic.literal(("/", cid)).asInstanceOf[IPLDLink]
}

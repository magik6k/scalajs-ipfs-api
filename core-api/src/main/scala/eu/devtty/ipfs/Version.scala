package eu.devtty.ipfs

import scala.scalajs.js

@js.native
trait Version extends js.Object {
  val version: String = js.native
  val repo: String = js.native
  val commit: String = js.native
}

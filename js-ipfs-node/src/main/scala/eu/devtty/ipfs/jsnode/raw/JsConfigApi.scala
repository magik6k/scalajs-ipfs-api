package eu.devtty.ipfs.jsnode.raw

import scala.scalajs.js

@js.native
trait JsConfigApi extends js.Object {
  def get(key: String): js.Promise[js.Dynamic] = js.native
  def set(key: String, value: String): js.Promise[_] = js.native
  def replace(config: js.Object): js.Promise[_] = js.native
}

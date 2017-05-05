package eu.devtty.ipfs.jsnode

import scala.concurrent.{Future, Promise}
import scala.scalajs.js


package object util {
  object FutureCall0 {
    def apply[_](fn: (js.Function0[_]) => Unit): Future[_] = {
      val p = Promise[Unit]
      fn { () =>
        p.success()
      }
      p.future
    }
  }

  object FutureCall1 {
    def apply[A](fn: (js.Function1[A, _])=>Unit): Future[A] = {
      val p = Promise[A]
      fn { (a) =>
        p.success(a)
      }
      p.future
    }
  }

  object FutureCallErr1 {
    def apply[A](fn: (js.Function2[String, A, _])=>Unit): Future[A] = {
      val p = Promise[A]
      fn { (err, a) =>
        if(err != null)
          p.failure(new Exception(err))
        else
          p.success(a)
      }
      p.future
    }
  }
}

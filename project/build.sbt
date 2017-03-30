resolvers += "ipfsPublish" at "https://ipfs.io/ipfs/QmRDoEHSQma4kaKLxd9YXF71FQvfBzSh8Rvdojc2UfThDu"

addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.15")
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % "0.5.0")
addSbtPlugin("eu.devtty" % "sbt-publish-ipfs" % "1.2.0")

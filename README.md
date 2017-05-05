# Scalajs facades for js-ipfs APIs

## Use
NOTE: This is still in progress! Things might and will change!

SBT: (requires ScalaJSBundlerPlugin)

Example `build.sbt`
```scala
resolvers += "ipfs" at "https://ipfs.io/ipfs/QmYCo4C1gYBzehAAhQjxBWZuxLsxrcKstKtnM9c5i8xAKw"

lazy val root = (project in file(".")).settings(
  webpackConfigFile := Some(baseDirectory.value / "webpack.config.js"),

  scalaVersion := "2.12.1",
  libraryDependencies ++= Seq(
    "eu.devtty" %%% "api-ipfs-node" % "0.2.1-SNAPSHOT",
    "org.scala-js" %%% "scalajs-dom" % "0.9.1"
  )
).enablePlugins(ScalaJSBundlerPlugin)
```

You'll need `webpack.config.js` next to your `build.sbt`
```js
'use strict'

const path = require('path');
module.exports = require('./scalajs.webpack.config');

module.exports.resolve = {
    alias: {
        zlib: 'browserify-zlib-next'
    }
};

//Fix npm link
module.exports.resolve = { fallback: path.join(__dirname, "node_modules") };
module.exports.resolveLoader = { fallback: path.join(__dirname, "node_modules") };

module.exports.module.preLoaders.push({ test: /\.json$/, loader: 'json-loader' });
```

## Status

* cid: **need tests**
* multibase: **need tests**
* multiaddr: **need tests**
* peer-info: **need tests**
* peer-id: **partial**
* ipld-format: **partial**

* core-api: **in progress**
  * bitswap: TODO//no spec
  * block: **done-ish**
  * bootstrap: TODO//no spec
  * config: **done**
  * dag: **really need tests**
  * dht: TODO//no spec
  * files: **done**
  * generic: **need tests**
  * object: TODO
  * pin: TODO//no spec
  * pubsub: **done**/unsubscribe is broken
  * repo: TODO//no spec
  * swarm: **done**

* http-node: **in progress**
  * bitswap: TODO//no spec
  * block: **done-ish**
  * bootstrap: TODO
  * config: **done**
  * dag: **done-ish**
  * dht: TODO
  * files: **done**
  * generic: **done**
  * object: TODO
  * pin: TODO
  * pubsub: TODO
  * repo: TODO
  * swarm: **done**

* js-ipfs-node: **in progress**
  * bitswap: TODO//no spec
  * block: **done-ish**
  * bootstrap: TODO//no spec
  * config: **done**
  * dag: **done-ish**
  * dht: TODO//no spec//no implementation
  * files: **done**
  * generic: **started**
  * object: TODO
  * pin: TODO//no spec//no implementation
  * pubsub: **done**/unsubscribe is broken
  * repo: TODO//no spec
  * swarm: **done**

## License

[MIT](LICENSE)

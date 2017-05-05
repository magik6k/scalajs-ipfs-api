import sbt.Keys.publishTo

lazy val publishDir = settingKey[String]("Publishing directory")
lazy val publishIpfs = taskKey[Unit]("Publish to IPFS")

lazy val pubDir = {
  import java.nio.file.Files
  Files.createTempDirectory("sbt-ipfs-").toString
}

def commonSettings(pname: String) = Seq(
  version := "0.2.1-SNAPSHOT",
  organization := "eu.devtty",
  scalaVersion := "2.12.1",
  name := pname,
  emitSourceMaps in fullOptJS := false,

  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.4.5" % "test"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework"),

  publishDir := { pubDir },

  publishTo := Some(Resolver.file("ipfs", file(publishDir.value))),

  publishIpfs := {
    publish.value
    ("ipfs add --pin=false -r " + publishDir.value) !
  }
)

///////
// MULTIFORMATS

lazy val multiaddr = (project in file("multiaddr")).settings(commonSettings("multiaddr"): _*).settings(
  npmDependencies in Compile += "multiaddr" -> "2.3.0"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(multihash)

lazy val multibase = (project in file("multibase")).settings(commonSettings("multibase"): _*).settings(
  npmDependencies in Compile += "multibase" -> "0.3.4",
  libraryDependencies ++= Seq(
    "io.scalajs" %%% "nodejs" % "0.4.0-pre1"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

lazy val multihash = (project in file("multihash")).settings(commonSettings("multihash"): _*).settings(
  npmDependencies in Compile += "multihashes" -> "0.4.5",
  libraryDependencies ++= Seq(
    "io.scalajs" %%% "nodejs" % "0.4.0-pre1"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

///////
// LIBP2P / Lower level

lazy val ipld = (project in file("ipld")).settings(commonSettings("ipld"): _*).settings(
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(cid)

lazy val cid = (project in file("cid")).settings(commonSettings("cid"): _*).settings(
  npmDependencies in Compile += "cids" -> "0.4.2"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(multihash)

lazy val peerInfo = (project in file("peerInfo")).settings(commonSettings("peerInfo"): _*).settings(
  npmDependencies in Compile += "peer-info" -> "0.9.0"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(peerId, multiaddr)

lazy val peerId = (project in file("peerId")).settings(commonSettings("peerId"): _*).settings(
  npmDependencies in Compile += "ipfs-peer-id" -> "0.3.2"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(multihash)

///////
// MAIN MODULES

lazy val coreApi = (project in file("core-api")).settings(commonSettings("core-ipfs-api"): _*).settings(
  npmDependencies in Compile += "ipfs-block" -> "0.6.0"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(cid, multiaddr, peerInfo, ipld)

lazy val jsInterface = (project in file("js-interface")).settings(commonSettings("js-interface"): _*).settings(

).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(coreApi)

lazy val jsNode = (project in file("js-ipfs-node")).settings(commonSettings("js-ipfs-api"): _*).settings(
  npmDependencies in Compile += "ipfs" -> "0.23.1"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(coreApi, jsInterface)

lazy val apiNode = (project in file("api-ipfs-node")).settings(commonSettings("api-ipfs-node"): _*).settings(
  npmDependencies in Compile += "ipfs-api" -> "14.0.0"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)
  .dependsOn(coreApi, jsInterface)

lazy val root = (project in file(".")) settings (
    publish := { },
    publishDir := { pubDir },
    publishTo := Some(Resolver.file("ipfs", file(publishDir.value))),
    publishIpfs := { }
  ) aggregate(multiaddr,
    multibase,
    multihash,
    ipld,
    cid,
    peerInfo,
    peerId,
    coreApi,
    jsInterface,
    jsNode,
    apiNode)

//onLoad in Global := (Command.process("project jsNode", _: State)) compose (onLoad in Global).value

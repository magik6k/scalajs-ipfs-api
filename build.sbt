lazy val publishDir = settingKey[String]("Publishing directory")
lazy val publishIpfs = taskKey[Unit]("Publish to IPFS")

def commonSettings(pname: String) = Seq(
  version := "0.1-SNAPSHOT",
  organization := "io.ipfs",
  scalaVersion := "2.12.1",
  name := pname,
  emitSourceMaps in fullOptJS := false,

  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.4.5" % "test"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework")
)

///////
// MULTIFORMATS

lazy val multiaddr = (project in file("multiaddr")).settings(commonSettings("multiaddr"): _*).settings(
  npmDependencies in Compile += "multiaddr" -> "2.3.0"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(multihash)

lazy val multibase = (project in file("multibase")).settings(commonSettings("multibase"): _*).settings(
  npmDependencies in Compile += "multibase" -> "0.3.4"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)

lazy val multihash = (project in file("multihash")).settings(commonSettings("multihash"): _*).settings(
  npmDependencies in Compile += "multihashes" -> "0.4.5",
  libraryDependencies ++= Seq(
    "io.scalajs" %%% "nodejs" % "0.4.0-pre1"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)

///////
// LIBP2P / Lower level

lazy val ipld = (project in file("ipld")).settings(commonSettings("ipld"): _*).settings(
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(cid)

lazy val cid = (project in file("cid")).settings(commonSettings("cid"): _*).settings(
  npmDependencies in Compile += "cids" -> "0.4.2"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(multihash)

lazy val peerInfo = (project in file("peerInfo")).settings(commonSettings("peerInfo"): _*).settings(
  npmDependencies in Compile += "peer-info" -> "0.9.0"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(peerId, multiaddr)

lazy val peerId = (project in file("peerId")).settings(commonSettings("peerId"): _*).settings(
  npmDependencies in Compile += "ipfs-peer-id" -> "0.3.2"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(multihash)

///////
// MAIN MODULES

lazy val coreApi = (project in file("core-api")).settings(commonSettings("core-api"): _*).settings(
  npmDependencies in Compile += "ipfs-block" -> "0.6.0"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(cid, multiaddr, peerInfo, ipld)

lazy val jsNode = (project in file("js-ipfs-node")).settings(commonSettings("js-ipfs-node"): _*).settings(
  npmDependencies in Compile += "ipfs" -> "0.23.1"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(coreApi)

lazy val httpNode = (project in file("http-node")).settings(commonSettings("http-node"): _*).settings(

).enablePlugins(ScalaJSPlugin, IpfsPublish)
  .dependsOn(coreApi)

onLoad in Global := (Command.process("project coreApi", _: State)) compose (onLoad in Global).value

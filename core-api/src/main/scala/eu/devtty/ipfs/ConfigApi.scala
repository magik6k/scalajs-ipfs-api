package eu.devtty.ipfs

import scala.concurrent.Future
import scala.scalajs.js

trait ConfigApi {
  /**
    *
    * @param key The key of the value that should be fetched from the config file.
    *            If no key is passed, then the whole config should be returned.
    * @return JSON object containing the configuration of the IPFS node
    */
  def get(key: String): Future[js.Dynamic]

  /**
    * Adds or replaces a config value.
    * @param key the key value that will be added or replaced (in case of the value already).
    * @param value value to be set.
    * @return
    *
    * @note This operation will not spark the restart of any service,
    *       i.e: if a config.replace changes the multiaddrs of the Swarm,
    *       Swarm will have to be restarted manually for the changes to take
    *       difference.
    */
  def set(key: String, value: String): Future[_]

  /**
    * Adds or replaces a config file.
    * @param config JSON object that contains the new config.
    * @return
    *
    * @note This operation will not spark the restart of any service,
    *       i.e: if a config.replace changes the multiaddrs of the Swarm,
    *       Swarm will have to be restarted manually for the changes to take
    *       difference.
    */
  def replace(config: js.Object): Future[_]

}

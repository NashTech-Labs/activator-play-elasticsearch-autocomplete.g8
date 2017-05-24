package util

import org.elasticsearch.common.settings.Settings
import org.elasticsearch.node.{Node, NodeBuilder}

trait TestHelper {

  val localClient: Node = {
    val esSettings = Settings.settingsBuilder()
      .put("cluster.name", "elasticsearch")
      .put("discovery.zen.ping.multicast.enabled", false)
      .put("http.enabled", false)
      .put("client", true)
      .put("path.home","data")
      .put("local", true)
      .put("client.transport.sniff", false)
    val node =NodeBuilder.nodeBuilder().settings(esSettings).node()
    node
  }

}

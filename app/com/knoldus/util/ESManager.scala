package com.knoldus.util

import java.net.InetAddress

import com.typesafe.config.Config
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse
import org.elasticsearch.action.admin.indices.refresh.{RefreshRequest, RefreshResponse}
import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import play.api.{Configuration, Logger}

/**
  * Manage all the elasticsearch setting for tcp client
  */
trait ESManager {

  val configuration: Configuration
  val logger: Logger


  lazy val client: Client = TransportClient.builder().settings(settings).build().addTransportAddresses(addresses: _*)

  val config: Config = configuration.underlying
  val ingestIndex = config.getString("index")
  val nodes = config.getString("nodes").split(',').toList.map(_.trim)
  val port = config.getInt("port")
  val clusterName = config.getString("cluster")
  val addresses = nodes.map { host => new InetSocketTransportAddress(InetAddress.getByName(host), port) }

  logger.info(s"ElasticClient: Nodes => $nodes , Port => {$port}")
  private val settings = Settings.settingsBuilder()
    .put("threadpool.search.queue_size", -1)
    .put("threadpool.index.queue_size", -1)
    .put("cluster.name", clusterName)
    .build()

  def refreshIndex(index: String = ingestIndex): RefreshResponse = client.admin().indices().refresh(new RefreshRequest(index)).actionGet()

  def createIndex(index: String = ingestIndex): CreateIndexResponse = client.admin().indices.prepareCreate(index).execute().actionGet()

  def deleteIndex(index: String = ingestIndex): DeleteIndexResponse = client.admin().indices.prepareDelete(index).execute().actionGet()

}


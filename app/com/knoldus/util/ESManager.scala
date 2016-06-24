package com.knoldus.util

import java.net.InetAddress

import com.typesafe.config.{Config, ConfigFactory}
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse
import org.elasticsearch.action.admin.indices.refresh.{RefreshRequest, RefreshResponse}
import org.elasticsearch.client.Client
import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import play.api.Logger

import scala.collection.JavaConversions._

/**
  * Manage all the elasticsearch setting for tcp client
  */
trait ESManager {

val config: Config = ConfigFactory.load()

  lazy val client: Client = TransportClient.builder().settings(settings).build().addTransportAddresses(addresses: _*)


  val ingestIndex = config.getString("index")
  val nodes = config.getStringList("nodes").toList
  val port = config.getInt("port")
  val clusterName = config.getString("cluster")

  val addresses = nodes.map { host => new InetSocketTransportAddress(InetAddress.getByName(host), port) }

  Logger.info(s"ElasticClient: Nodes => $nodes , Port => {$port}")
  private val settings = Settings.settingsBuilder()
    .put("threadpool.search.queue_size", -1)
    .put("threadpool.index.queue_size", -1)
    .put("cluster.name", clusterName)
    .build()

  def refreshIndex(index: String = ingestIndex): RefreshResponse = client.admin().indices().refresh(new RefreshRequest(index)).actionGet()

  def createIndex(index: String = ingestIndex): CreateIndexResponse = client.admin().indices.prepareCreate(index).execute().actionGet()

  def deleteIndex(index: String = ingestIndex): DeleteIndexResponse = client.admin().indices.prepareDelete(index).execute().actionGet()

}


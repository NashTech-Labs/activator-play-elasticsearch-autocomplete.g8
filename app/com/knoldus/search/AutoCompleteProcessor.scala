package com.knoldus.search

import com.google.inject.ImplementedBy
import com.knoldus.util.ESManager
import net.liftweb.json.DefaultFormats
import org.elasticsearch.index.query.{MatchQueryBuilder, QueryBuilders}
import play.api.Logger

@ImplementedBy(classOf[AutoCompleteProcessor])
trait AutoCompleteProcessorApi extends ESManager {

  private val log = Logger(this.getClass)
  implicit protected val formats = DefaultFormats
  /**
    * Finds matched items from elasticsearch
    *
    * @param text , term which is to be searched
    * @return : List of all matched items
    */
  def getMatches(text: String): List[String] = {
    try {
      val query = client.prepareSearch(ingestIndex)
        .setQuery(QueryBuilders.matchPhraseQuery("_all", text).operator(MatchQueryBuilder.Operator.AND)).get
      query.getHits.hits().toList.map {
        hit => hit.getSource.get("Title").toString
      }
    } catch {
      case ex: Exception =>
        log.error("Some error has occured",ex)
        Nil
    }
  }

  def getMovies(text: String): List[String] = {
    try {
      val query = client.prepareSearch(ingestIndex)
        .setQuery(QueryBuilders.matchPhraseQuery("_all", text).operator(MatchQueryBuilder.Operator.AND)).get
      query.getHits.hits().toList.map {
        hit => hit.getSourceAsString
      }
    } catch {
      case ex: Exception =>
        log.error("Some error has occured", ex)
        Nil
    }
  }

}

class AutoCompleteProcessor extends AutoCompleteProcessorApi

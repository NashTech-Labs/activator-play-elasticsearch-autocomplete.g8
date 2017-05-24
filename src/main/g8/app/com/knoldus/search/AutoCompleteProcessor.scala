package com.knoldus.search

import com.google.inject.Inject
import com.knoldus.util.ESManager
import org.elasticsearch.index.query.{MatchQueryBuilder, QueryBuilders}
import play.api.Logger

class AutoCompleteProcessor @Inject()(esManager: ESManager) {

  import esManager._

  private val log = Logger(this.getClass)

  /**
    * Finds matched items from elasticsearch
    *
    * @param text , term which is to be searched
    * @return : List of all matched items
    */
  def getMatches(text: String): List[String] = {
    try {
      val query = client.prepareSearch(ingestIndex)
        .setQuery(QueryBuilders.matchPhraseQuery("Title", text)).get
      query.getHits.hits().toList.map {
        hit => hit.getSource.get("Title").toString
      }
    } catch {
      case ex: Exception =>
        log.error("Some error has occured", ex)
        Nil
    }
  }

  /**
    * Returns all the document from elasticseach
    *
    * @param text which is to be searched
    * @return List of document json
    */
  def getMovies(text: String): List[String] = {
    try {
      val query = client.prepareSearch(ingestIndex)
        .setQuery(QueryBuilders.matchPhraseQuery("Title", text)).get
      query.getHits.hits().toList.map {
        hit => hit.getSourceAsString
      }
    } catch {
      case ex: Exception =>
        log.error("Exception occurred: ", ex)
        Nil
    }
  }

}
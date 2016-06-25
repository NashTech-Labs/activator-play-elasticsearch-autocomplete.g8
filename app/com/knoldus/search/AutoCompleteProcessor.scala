package com.knoldus.search

import com.knoldus.util.ESManager
import org.elasticsearch.index.query.{MatchQueryBuilder, QueryBuilders}
import play.api.Logger

trait AutoCompleteProcessor {
  this: ESManager =>

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
        .setQuery(QueryBuilders.matchPhraseQuery("_all", text).operator(MatchQueryBuilder.Operator.AND)).get
      query.getHits.hits().toList.map {
        hit => hit.getSource.get("name").toString
      }
    } catch {
      case ex: Exception =>
        log.error(ex.printStackTrace.toString)
        Nil
    }
  }

}

object AutoCompleteProcessor extends AutoCompleteProcessor with ESManager

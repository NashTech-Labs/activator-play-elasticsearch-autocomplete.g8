package com.knoldus.search

import com.knoldus.util.ESManager
import org.elasticsearch.index.query.{MatchQueryBuilder, QueryBuilders}


trait AutoCompleteProcessor {
  this: ESManager =>

  def getMatches(text: String): List[String] = {
    val query = client.prepareSearch(ingestIndex)
      .setQuery(QueryBuilders.matchPhraseQuery("_all",text).operator(MatchQueryBuilder.Operator.AND)).get
    query.getHits.hits().toList.map {
      hit => hit.getSource.get("name").toString
    }
  }

}


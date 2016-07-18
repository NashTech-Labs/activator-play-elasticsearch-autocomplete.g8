package com.knoldus.search

import com.knoldus.util.ESManager
import play.api.test.{PlaySpecification, WithApplication}

class AutoCompleteProcessorTest extends PlaySpecification with ESManager {

  val autoCompleteProcessor = new AutoCompleteProcessor

  "Play Specification" should {

    "autocomplete" in new WithApplication {
      val result = autoCompleteProcessor.getMatches("go")
      assert(result === List("Gone Girl"))
    }

    "get movies" in new WithApplication {
      val result = autoCompleteProcessor.getMovies("Gone Girl")
      print(result)
      assert(result.head.contains("Gone Girl"))
    }

  }

}

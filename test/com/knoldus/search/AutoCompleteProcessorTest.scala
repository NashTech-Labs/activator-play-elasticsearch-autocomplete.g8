package com.knoldus.search

import com.knoldus.util.ESManager
import play.api.test.{PlaySpecification, WithApplication}

class AutoCompleteProcessorTest extends PlaySpecification with AutoCompleteProcessor with ESManager {

  "Play Specification" should {

    "autocomplete" in new WithApplication {

      val result = getMatches("at")
      assert(result === List("prabhat kashyap","batman vs superman"))
    }

  }



}

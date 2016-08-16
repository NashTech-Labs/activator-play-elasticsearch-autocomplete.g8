package com.knoldus.search

import java.io.File

import org.elasticsearch.action.admin.indices.refresh.RefreshRequest
import org.specs2.specification.BeforeAfterAll
import play.api.test.{PlaySpecification, WithApplication}
import util.TestHelper

import scala.io.Source

class AutoCompleteProcessorTest extends PlaySpecification with TestHelper with BeforeAfterAll{

  val autoCompleteProcessor = new AutoCompleteProcessor
  val client = localClient.client

  override def afterAll = {
    client.close()
    client.admin().indices().prepareDelete("testing").get
  }

  override def beforeAll = {
    val settings = Source.fromFile(new File("extra/es-mapping.json")).mkString
    client.admin().indices().prepareCreate("testing").setSource(settings).get
    val bulkRequest = client.prepareBulk()
    Source.fromFile(new File("extra/movies.json")).getLines().foreach{
      movie => bulkRequest.add(client.prepareIndex("testing", "movies").setSource(movie))
    }
    bulkRequest.get()
    client.admin().indices().refresh(new RefreshRequest("testing")).get
  }

  "Play Specification" should {

    "autocomplete" in new WithApplication {
      val result = autoCompleteProcessor.getMatches("go")
      assert(result === List("Gone Girl"))
    }

    "get movies" in new WithApplication {
      val result = autoCompleteProcessor.getMovies("Gone Girl")
      assert(result.head.contains("Gone Girl"))
    }

  }

}

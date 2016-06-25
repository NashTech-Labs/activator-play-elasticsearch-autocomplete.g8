package controllers

import com.knoldus.search.AutoCompleteProcessor
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import play.api.mvc.Results
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}
import org.specs2.mock.Mockito

/**
  * Add your spec here.
  * You can mock out a whole application including requests, plugins etc.
  * For more information, consult the wiki.
  */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends PlaySpecification with Results with Mockito {

  val mockedProcessor = mock[AutoCompleteProcessor]
  val mockedJars = mock[WebJarAssets]

  val testController = new Application(mockedJars, mockedProcessor)

  "Application" should {

    "search" in new WithApplication {
      mockedProcessor.getMatches("java") returns List("java", "javascript", "jQuery")
      val result = testController.searchText("java").apply(FakeRequest("GET", "/search_text"))
      status(result) must be equalTo 200
    }
  }

}
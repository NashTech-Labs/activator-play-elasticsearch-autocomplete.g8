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

    "search suggestion" in new WithApplication {
      mockedProcessor.getMatches("java") returns List("java", "javascript", "jQuery")
      val result = testController.searchText("java").apply(FakeRequest("GET", "/search_text"))
      val resultAsString = contentAsString(result)
      resultAsString === """["java","javascript","jQuery"]"""
      status(result) must be equalTo 200
    }

    "search movies" in new WithApplication {
      mockedProcessor.getMovies("Batman v Superman: Dawn of Justice") returns List("""{"Title":"Batman v Superman: Dawn of Justice","Year":"2016","Released":"25 Mar 2016","Genre":"Action, Adventure, Sci-Fi","Director":"Zack Snyder","Plot":"steel.","Poster":"http://ia.media-imdb.com/images/M/MV5BNTE5NzU3MTYzOF5BMl5BanBnXkFtZTgwNTM5NjQxODE@._V1_SX300.jpg","imdbRating":"7.0"}""")
      val result = testController.searchMovie("Batman v Superman: Dawn of Justice").apply(FakeRequest("GET", "/search_content"))
      status(result) must be equalTo 200
    }

  }

}
package controllers

import javax.inject.Inject

import com.knoldus.search.AutoCompleteProcessor
import domain.Content
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import views.html
import com.knoldus.util.JsonFormatter._

class Application @Inject()(webJarAssets: WebJarAssets, autoCompleteProcessor: AutoCompleteProcessor) extends Controller {

  private val log = Logger(this.getClass)


  /**
    * Render Home page
    */
  def index = Action {
    Ok(html.index(webJarAssets))
  }

  /**
    * Finds all matched items with term
    *
    * @param term , the tet which is to be searched
    * @return list json of matched items
    */
  def searchText(term: String) = Action {
    val matchedItems: List[String] = autoCompleteProcessor.getMatches(term)
    log.info(s"Matched items with text = $term => " + matchedItems)
    Ok(Json.toJson(matchedItems))
  }

  /**
    * Search all movie content on the basis of search text
    */
  def searchMovie(search: String) = Action {
    val moviesJson: List[String] = autoCompleteProcessor.getMovies(search)
    val movieList = moviesJson.map {
      movie => Json.parse(movie).as[Content]
    }
    Ok(views.html.searchedContent(movieList))
  }


}


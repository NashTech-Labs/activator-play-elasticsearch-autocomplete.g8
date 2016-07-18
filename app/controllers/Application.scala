package controllers

import javax.inject.Inject

import com.knoldus.search.AutoCompleteProcessorApi
import net.liftweb.json.DefaultFormats
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import views.html
import net.liftweb.json.{ parse => liftParser }

class Application @Inject()(webJarAssets: WebJarAssets, autoCompleteProcessor: AutoCompleteProcessorApi) extends Controller {

  private val log = Logger(this.getClass)
  implicit protected val formats = DefaultFormats
  /**
    * Render main template
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
    val matchedItems = autoCompleteProcessor.getMatches(term)
    log.info(s"Matched items with text = $term => " + matchedItems)
    Ok(Json.toJson(matchedItems))
  }

  def searchContent(search: String) = Action {
    val movies = autoCompleteProcessor.getMovies(search)
    log.info(movies.toString())
    val res = movies.map {
      movie => liftParser(movie).extract[domain.Content]
    }
    Ok(views.html.searchedContent(res))
  }


}


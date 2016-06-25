package controllers

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc._
import views.html
import com.knoldus.search.AutoCompleteProcessor
import play.api.Logger

class Application @Inject()(webJarAssets: WebJarAssets) extends Controller {

  private val log = Logger(this.getClass)

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
    val matchedItems = AutoCompleteProcessor.getMatches(term)
    log.info(s"Matched items with text = $term => " + matchedItems)
    Ok(Json.toJson(matchedItems))
  }

}
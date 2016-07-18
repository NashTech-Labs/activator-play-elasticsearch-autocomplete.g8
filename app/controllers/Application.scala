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

  def searchContent(search: String) = Action {
    val res = List(domain.Content("Batman v Superman: Dawn of Justice", "2016", "Action, Adventure, Sci-Fi",
      "The General public is controversial over having superman on their planet and letting the dark knight pursue the streets of Gotham. And while this is happening, a power-phobic batman tries to attack superman, meanwhile, superman tries to settle on a decision, and Lex Luther, a millionaire, tries to use his own advantages to fight the man of steel.",
    "http://ia.media-imdb.com/images/M/MV5BNTE5NzU3MTYzOF5BMl5BanBnXkFtZTgwNTM5NjQxODE@._V1_SX300.jpg","22 Jun 2001", "Zack Snyder","7.4"),
      domain.Content("The Fast and the Furious", "2001", "Action, Crime, Thriller",
        "Los Angeles street racer Dominic Toretto falls under the suspicion of the LAPD as a string of high-speed electronics truck robberies rocks the area. Brian O'Connor, an officer of the LAPD, joins the ranks of Toretto's highly skilled racing crew undercover to convict Toretto. However, O'Connor finds himself both enamored with this new world and in love with Toretto's sister, Mia. As a rival racing crew gains strength, O'Connor must decide where his loyalties really lie.",
        "http://ia.media-imdb.com/images/M/MV5BMjAwNTQ0Nzc2M15BMl5BanBnXkFtZTcwNTk1MDIwNA@@._V1_SX300.jpg", "22 Jun 2001", "Rob Cohen", "3.5"))
    Ok(views.html.searchedContent(res))
  }


}

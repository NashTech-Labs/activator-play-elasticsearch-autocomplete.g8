package controllers

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc._
import views.html

class Application @Inject()(webJarAssets: WebJarAssets) extends Controller {

  def index = Action {Ok(html.index(webJarAssets))}

  def searchText(term: String) = Action {
    val searchList = {
      if (term == "j")
        List("Java", "Javascript", "Jquery")
      else
        List("Levis", "Levis men shirt")
    }
    Ok(Json.toJson(searchList))
  }

}
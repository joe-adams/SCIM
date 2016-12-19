package controllers

import play.api.mvc.{Action, Controller}
import javax.inject._

import models.user.Name
import play.api.libs.json._
import service.DataService
//import com.typesafe.

class SCIM @Inject() (dataService:DataService) extends Controller with LazyLogging{

  def users(filter:Option[String], count:Option[String], startIndex:Option[String]) = Action { request =>
    // Todo: Provide logic to get users
    // Todo: Support optional filter parameter on exact email match
    // Todo: Support optional count parameter to limit responses
    // Todo: Support optional startIndex parameter to begin responses on the nth user
    Ok
  }

  def user(uid:String) = Action { request =>
    // Todo: Provide logic to get a single user, identified by user id
    Ok
  }

  def createUser = Action(parse.tolerantJson) { request =>
    import models.User._
    import models.InsertUser
    val insertUserResult = request.body.validate[InsertUser]
    insertUserResult.fold(
      errors => {
        //log.info("bad"+error)
        BadRequest(Json.obj("status" ->"Error", "message" -> JsError.toJson(errors)))
      },
      user => {
        //Place.save(place)
        Ok(Json.obj("status" ->"OK", "message" -> ("User '"+user.name+"' saved.") ))
      }
    )
  }

  def updateUser(uid:String) = Action { request =>
    // Todo: Provide logic to update a single user
    Ok
  }

  def deleteUser(uid:String) = Action { request =>
    // Todo: Provide logic to delete a single user
    Ok
  }

  def groups(count:Option[String], startIndex:Option[String]) = Action { request =>
    // Todo: Provide logic to get groups
    // Todo: Support optional count parameter to limit responses
    // Todo: Support optional startIndex parameter to begin responses on the nth group
    Ok
  }

  def patchGroup(gid:String) = Action { request =>
    // Todo: Provide logic to patch a single group
    // Todo: Update group assignment
    Ok
  }

}

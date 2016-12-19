package models.user

import play.api.libs.json._
import play.api.libs.functional.syntax._

import play.api.libs.json.{JsPath, Reads}

case class Name (
                  formatted:Option[String],
                  familyName:Option[String],
                  givenName:Option[String],
                  middleName:Option[String],
                  honorificPrefix:Option[String],
                  honorificSuffix:Option[String]
                )

object Name{
  implicit val nameReads:Reads[Name]=(
      (JsPath \ "formatted").readNullable[String] and
      (JsPath \ "familyName").readNullable[String] and
      (JsPath \ "givenName").readNullable[String] and
      (JsPath \ "middleName").readNullable[String] and
      (JsPath \ "honorificPrefix").readNullable[String] and
      (JsPath \ "honorificSuffix").readNullable[String]
    )(Name.apply _)
}
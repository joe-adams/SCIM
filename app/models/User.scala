package models

import models.common.Meta
import models.user.Name
import models.user.ValueAndType._
import play.api.libs.json.{JsPath, Reads}
import play.api.libs.functional.syntax._

case class User(id:String,
                userName:String,
                name:Name,
                displayName:Option[String],
                nickName:Option[String],
                profileUrl:Option[String],
                title:Option[String],
                userType:Option[String],
                preferredLanguage:Option[String],
                locale:Option[String],
                timezone:Option[String],
                active:Option[Boolean],
                emails:Option[Seq[Email]],
                phoneNumbers:Option[Seq[Phone]],
                ims:Option[Seq[Im]],
                photos:Option[Seq[Photo]],
                x509Certificates:Option[Seq[X509Certificate]],
                password:Option[Option[String]],
                groups:Option[Seq[childGroups]],
                meta:Option[Meta]
)


case class InsertUser(
                userName:String,
                name:Option[Name],
                displayName:Option[String],
                nickName:Option[String],
                profileUrl:Option[String],
                title:Option[String],
                userType:Option[String],
                preferredLanguage:Option[String],
                locale:Option[String],
                timezone:Option[String],
                active:Option[Boolean],
                emails:Option[Seq[Email]],
                phoneNumbers:Option[Seq[Phone]],
                ims:Option[Seq[Im]],
                photos:Option[Seq[Photo]],
                x509Certificates:Option[Seq[X509Certificate]],
                password:Option[String],
                groups:Option[Seq[String]]
               )


case class X509Certificate(value:String)

case class childGroups(value:String)

object User{
  val schemas="schemas"
  val urn="urn:ietf:params:scim:schemas:core:2.0:User"
  implicit val certReader:Reads[X509Certificate]=(JsPath \ "value").read[String].map(X509Certificate)

  implicit val readsUser:Reads[InsertUser]={
    (
      (JsPath \ "userName").read[String] and
        (JsPath \ "name").readNullable[Name] and
        (JsPath \ "displayName").readNullable[String] and
        (JsPath \ "nickName").readNullable[String] and
        (JsPath \ "profileUrl").readNullable[String] and
        (JsPath \ "title").readNullable[String] and

        (JsPath \ "userType").readNullable[String] and
        (JsPath \ "preferredLanguage").readNullable[String] and
        (JsPath \ "locale").readNullable[String] and
        (JsPath \ "timezone").readNullable[String] and
        (JsPath \ "active").readNullable[Boolean] and
        (JsPath \ "emails").readNullable[Seq[Email]] and
        (JsPath \ "phoneNumbers").readNullable[Seq[Phone]] and
        (JsPath \ "ims").readNullable[Seq[Im]] and
        (JsPath \ "photos").readNullable[Seq[Photo]]  and
        (JsPath \ "x509Certificates").readNullable[Seq[X509Certificate]] and
        (JsPath \ "password").readNullable[String] and
        (JsPath \ "groups").readNullable[Seq[String]]
      )(InsertUser.apply _)

  }



}
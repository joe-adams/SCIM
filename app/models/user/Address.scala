package models.user

import play.api.libs.functional.syntax._
import models.user.EnumType.AddressType
import play.api.libs.functional.FunctionalBuilder
import play.api.libs.json.{JsPath, Reads}

case class Address (
                    formatted: Option[String],
                    streetAddress: Option[String],
                    locality: Option[String],
                    region: Option[String],
                    postalCode: Option[String],
                    country: Option[String],
                    theType:AddressType,
                    primary:Boolean=false
                   )

object Address{
  implicit val readAddress:Reads[Address] =(
    (JsPath \ "formatted").readNullable[String] and
      (JsPath \ "streetAddress").readNullable[String] and
      (JsPath \ "locality").readNullable[String] and
      (JsPath \ "region").readNullable[String] and
      (JsPath \ "postalCode").readNullable[String] and
      (JsPath \ "country").readNullable[String] and
      EnumType.addresses.readsJson and
      EnumType.isPrimary
    )(Address.apply _)
}


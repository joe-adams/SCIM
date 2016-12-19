package models

import models.common.Meta


case class Group(id:String,
                 displayName:String,
                 meta: Meta
                )

object Group{
  val schemas="schemas"
  val urn="urn:ietf:params:scim:schemas:core:2.0:Group"
}

package models.common

case class Meta (resourceType: ResourceType.Value,created:Long,lastModified:Long,location:String)

object ResourceType extends Enumeration{
  val USER,GROUP=Value
}
package models.user

import play.api.libs.json.{JsPath, JsValue, Reads}


trait EnumType {
  def name:String
}

object EnumType{

  val isPrimary:Reads[Boolean]=((JsPath \ "primary").readNullable[Boolean].map(_==Some(true)))

  trait PhoneType  extends EnumType
  trait AddressType extends PhoneType
  type EmailType =AddressType
  trait ImType extends EnumType
  trait PhotoType extends EnumType

  case class EnumTypeSet[T <: EnumType](set:Set[T]){
    val fromName:Map[String,T]=set.groupBy(_.name).mapValues(_.head)
    val toName:Map[T,String]=fromName.map{ case (string,enum)=>(enum,string)}.toMap
    val readsJson:Reads[T]=((JsPath \ "type")).read[String].map(fromName)
  }

  object AddressOrEmail extends Enumeration{
    class ExtendedVal(i:Int,override val name:String) extends Val(i:Int,name:String) with AddressType
    def value(name:String)=new ExtendedVal(nextId,name)
    val WORK=value("work")
    val HOME=value("home")
    val OTHER=value("other")
    val set:Set[AddressType]=this.values.map(_ match{
      case a:AddressType=>Some(a)
      case _=>None
    }).flatten.toSet
  }

  object Phone extends Enumeration{
    class ExtendedVal(i:Int,override val name:String) extends Val(i:Int,name:String) with PhoneType
    def value(name:String)=new ExtendedVal(nextId,name)
    val MOBILE = value("mobile")
    val FAX = value("fax")
    val PAGER = value("pager")
    val set:Set[PhoneType]=this.values.map(_ match{
      case a:PhoneType=>Some(a)
      case _=>None
    }).flatten.toSet
  }

  object Im extends Enumeration{
    class ExtendedVal(i:Int,override val name:String) extends Val(i:Int,name:String) with ImType
    def value(name:String)=new ExtendedVal(nextId,name)
    val AIM=value("aim")
    val GTALK=value("gtalk")
    val ICQ=value("icq")
    val XMPP=value("xmpp")
    val MSN=value("skype")
    val QQ=value("qq")
    val YAHOO=value("yahoo")
    val OTHER=value("other")
    val set:Set[ImType]=this.values.map(_ match{
      case a:ImType=>Some(a)
      case _=>None
    }).flatten.toSet
  }

  object Photo extends Enumeration{
    class ExtendedVal(i:Int,override val name:String) extends Val(i:Int,name:String) with PhotoType
    def value(name:String)=new ExtendedVal(nextId,name)
    val PHOTO=value("photo")
    val THUMBNAIL=value("thumbnail")
    val set:Set[PhotoType]=this.values.map(_ match{
      case a:PhotoType=>Some(a)
      case _=>None
    }).flatten.toSet
  }

  val addresses=EnumTypeSet(AddressOrEmail.set)
  val emails: EnumTypeSet[EmailType] =addresses
  val phones =EnumTypeSet(AddressOrEmail.set++Phone.set)
  val ims=EnumTypeSet(Im.set)
  val photo=EnumTypeSet(Photo.set)

}

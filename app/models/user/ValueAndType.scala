package models.user

import models.user.EnumType._
import play.api.libs.functional.FunctionalBuilder
import play.api.libs.json._
import play.api.libs.functional.syntax._



case class ValueAndType[T <: EnumType](value:String, theType:EnumType){
  override def toString: String = StringFunctionHelpers.valueAndType(this)
}

case class ValueTypePrimary[T <: EnumType](valueAndType:ValueAndType[T],primary:Boolean=false){
  override def toString: String = StringFunctionHelpers.valueTypePrimary(this)
}

object ValueAndType{

  type Email=ValueTypePrimary[EmailType]
  type Phone=ValueAndType[PhoneType]
  type Im=ValueAndType[ImType]
  type Photo=ValueAndType[PhotoType]

  def readsValueType[T <: EnumType](readsEnum:Reads[T])= ((JsPath \ "value").read[String] and readsEnum)(ValueAndType.apply[T] _)
  def readsValueTypePrimary[T <: EnumType](readsEnum:Reads[T]):Reads[ValueTypePrimary[T]]=(readsValueType[T](readsEnum) and isPrimary)(ValueTypePrimary.apply[T] _)

  implicit val readsEmail:Reads[Email]=readsValueTypePrimary(emails.readsJson)
  implicit val readsPhone:Reads[Phone]=readsValueType(phones.readsJson)
  implicit val readsIms:Reads[Im]=readsValueType(ims.readsJson)
  implicit val readsPhoto:Reads[Photo]=readsValueType(photo.readsJson)

}

private object StringFunctionHelpers{
  val (valueAndType,valueTypePrimary)= {
    //This just traces a workflow. As an extension of a simple function, it is only here for clarification.
    case class serializeObject[T](toSeq:T=>Seq[String]) extends (T=>String){
      def combine(seq:Seq[String])=seq.mkString("{",",","}")
      def apply(t:T):String= toSeq.andThen(combine) (t)
    }
    val q:Char='"'
    def quotedKeyValue(key:String,value:Any)= s"$q$key$q : $q$value$q"
    val value=quotedKeyValue("value",_:String)
    val typeString=quotedKeyValue("type",_:Any)
    val primaryTrue: String =q+"primary"+q+" : true"
    def valueTypePropStrings(vt:ValueAndType[_]): Seq[String] =Seq(value(vt.value),typeString(vt.theType))
    def valueTypePrimaryString(pvt:ValueTypePrimary[_]): Seq[String]= {
      val seq=valueTypePropStrings(pvt.valueAndType)
      if (pvt.primary) seq :+ primaryTrue else seq
    }
    val serializeValueType:ValueAndType[_]=>String =serializeObject(valueTypePropStrings)
    val serializeValueTypePrimary: ValueTypePrimary[_]=>String =serializeObject(valueTypePrimaryString)
    (serializeValueType,serializeValueTypePrimary)
  }
}




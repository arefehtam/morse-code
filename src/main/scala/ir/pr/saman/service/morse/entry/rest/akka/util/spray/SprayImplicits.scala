package ir.pr.saman.service.morse.entry.rest.akka.util.spray

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import ir.pr.saman.service.morse.dto.MessageDTO
import ir.pr.saman.service.morse.entry.rest.akka.api.TranslateBodyDTO
import ir.pr.saman.service.morse.entry.rest.akka.api.exception.NotSupportedExceptionDTO
import spray.json.DefaultJsonProtocol

trait SprayImplicits extends SprayJsonSupport with DefaultJsonProtocol {

  import spray.json._

  implicit val messageFormat: RootJsonFormat[MessageDTO] = jsonFormat3(MessageDTO)
  implicit val notSupportedExceptionFormat: RootJsonFormat[NotSupportedExceptionDTO] = jsonFormat2(NotSupportedExceptionDTO)
  implicit val translateBody: RootJsonFormat[TranslateBodyDTO] = jsonFormat1(TranslateBodyDTO)

}

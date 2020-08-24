package ir.pr.saman.service.morse.entry.rest.akka.factory

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import ir.pr.saman.service.morse.domain.Message
import ir.pr.saman.service.morse.dto.MessageDTO
import spray.json.DefaultJsonProtocol

object FactoryDTO extends SprayJsonSupport with DefaultJsonProtocol {

  def message(o: Message): MessageDTO = MessageDTO(o.query, o.morseCode, o.queryEncoding)

}

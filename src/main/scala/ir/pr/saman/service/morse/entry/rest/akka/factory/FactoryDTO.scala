package ir.pr.saman.service.morse.entry.rest.akka.factory

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import ir.pr.saman.service.morse.domain.MorseObject
import ir.pr.saman.service.morse.dto.MorseObjectDTO
import spray.json.DefaultJsonProtocol

object FactoryDTO extends SprayJsonSupport with DefaultJsonProtocol {

  def message(o: MorseObject): MorseObjectDTO = MorseObjectDTO(o.query, o.morseCode, o.queryEncoding)

}

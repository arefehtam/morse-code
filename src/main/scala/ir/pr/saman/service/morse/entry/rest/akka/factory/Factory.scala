package ir.pr.saman.service.morse.entry.rest.akka.factory

import ir.pr.saman.service.morse.contract.service.TranslateToMorseCodeService.TranslateBody
import ir.pr.saman.service.morse.entry.rest.akka.api.TranslateBodyDTO

object Factory {

  def query(dto: TranslateBodyDTO): TranslateBody = TranslateBody(dto.query)

}

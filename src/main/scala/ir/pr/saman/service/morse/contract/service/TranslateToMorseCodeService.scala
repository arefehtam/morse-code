package ir.pr.saman.service.morse.contract.service

import ir.pr.saman.service.morse.domain.Message

trait TranslateToMorseCodeService extends Service[TranslateToMorseCodeService.TranslateBody, Message]

object TranslateToMorseCodeService {

  case class TranslateBody(query: String, code: String = "Base64")

}

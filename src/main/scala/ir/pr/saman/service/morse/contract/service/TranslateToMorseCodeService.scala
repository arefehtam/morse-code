package ir.pr.saman.service.morse.contract.service

import ir.pr.saman.service.morse.domain.MorseObject

trait TranslateToMorseCodeService extends Service[TranslateToMorseCodeService.TranslateBody, MorseObject]

object TranslateToMorseCodeService {

  case class TranslateBody(query: String, code: String = "Base64")

}

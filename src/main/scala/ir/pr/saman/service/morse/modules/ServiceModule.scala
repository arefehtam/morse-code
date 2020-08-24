package ir.pr.saman.service.morse.modules

import ir.pr.saman.service.morse.contract.service.TranslateToMorseCodeService
import ir.pr.saman.service.morse.usecase.TranslateToMorseCodeUseCase

object ServiceModule {

  val translateToMorseCodeService: TranslateToMorseCodeService = TranslateToMorseCodeUseCase

}

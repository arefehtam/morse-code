package ir.pr.saman.service.morse.usecase

import java.util.Base64

import ir.pr.saman.service.morse.contract.service.TranslateToMorseCodeService
import ir.pr.saman.service.morse.domain.Message
import ir.pr.saman.service.morse.exception._
import ir.pr.saman.service.morse.modules.ConstantsModule.morseCodes

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.language.postfixOps

trait TranslateToMorseCodeUseCase extends TranslateToMorseCodeService {
  override def call(body: TranslateToMorseCodeService.TranslateBody)(implicit ec: ExecutionContext): Future[Message] = Future {
    for {
      character <- body.query.toUpperCase
      _ = if (!(morseCodes contains character)) throw NotSupportedException(ErrorKey.Character, character)
    } yield morseCodes(character)
  } map (r => Message(Base64.getEncoder.encodeToString(body.query getBytes), r mkString, body.code))

}

object TranslateToMorseCodeUseCase extends TranslateToMorseCodeUseCase

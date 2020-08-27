package ir.pr.saman.service.morse.usecase

import java.util.Base64

import com.typesafe.scalalogging.Logger
import ir.pr.saman.service.morse.contract.service.TranslateToMorseCodeService
import ir.pr.saman.service.morse.domain.MorseObject
import ir.pr.saman.service.morse.exception._
import ir.pr.saman.service.morse.modules.ConstantsModule.morseCodes

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.language.postfixOps

trait TranslateToMorseCodeUseCase extends TranslateToMorseCodeService {

  val logger = Logger("Translate")

  /**
    * It translates query string to morse code, also encoded query to Base64
    * @param body.query: string query
    *            .code: default to 'Base64'
    * @param ec: implicit execution context, here it is a wrapper and returen Future.successful or Future.failed
    * @return: morse object corresponding to input query
    */

  override def call(body: TranslateToMorseCodeService.TranslateBody)(implicit ec: ExecutionContext): Future[MorseObject] = Future {
    logger info s"Start Translating ${body.query take 1000 }..."
    for {
      character <- body.query.toUpperCase
      _ = if (!(morseCodes contains character)) throw NotSupportedException(ErrorKey.Character, character)
    } yield morseCodes(character)
  } map (r => MorseObject(Base64.getEncoder.encodeToString(body.query getBytes), r mkString, body.code))

}

object TranslateToMorseCodeUseCase extends TranslateToMorseCodeUseCase

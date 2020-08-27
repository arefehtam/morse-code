package ir.pr.saman.service.morse.entry.rest.akka

import akka.http.scaladsl.model.ContentTypes.`application/json`
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.testkit.ScalatestRouteTest
import ir.pr.saman.service.morse.dto.MorseObjectDTO
import ir.pr.saman.service.morse.entry.rest.akka.Server.route
import ir.pr.saman.service.morse.entry.rest.akka.api.exception.NotSupportedExceptionDTO
import ir.pr.saman.service.morse.entry.rest.akka.util.spray.SprayImplicits
import ir.pr.saman.service.morse.exception.ErrorKey
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec


class ServerSpec extends AnyWordSpec with Matchers with ScalatestRouteTest with SprayImplicits {

  "The service" should {

    "return a greeting for GET requests to the healths path" in {
      // tests:
      Get("/api/v1/services/codes/morse/healths") ~> route ~> check {
        responseAs[String] shouldEqual "Morse Service is up and running"
      }
    }
    "return a hello morse code for POST requests to the translate path" in {
      Post("/api/v1/services/codes/morse/translate", HttpEntity(`application/json`, """{ "query": "hello" }""")) ~>
        route ~> check {
        responseAs[MorseObjectDTO] shouldEqual MorseObjectDTO("aGVsbG8=", "......-...-..---", "Base64")
      }
    }
    "return the same morse code for HELLO and hello" in {
      Post("/api/v1/services/codes/morse/translate", HttpEntity(`application/json`, """{ "query": "HELLO" }""")) ~>
        route ~> check {
        val result = responseAs[MorseObjectDTO]
        result.morseCode shouldEqual "......-...-..---"
      }
    }

    "throw exception when an space is in the query" in {
      Post("/api/v1/services/codes/morse/translate", HttpEntity(`application/json`, """{ "query": "How Are You" }""")) ~>
        route ~> check {
        responseAs[NotSupportedExceptionDTO] shouldEqual NotSupportedExceptionDTO(ErrorKey.Character, ' ')
      }
    }

  }
}

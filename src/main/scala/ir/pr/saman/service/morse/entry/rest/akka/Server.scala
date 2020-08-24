package ir.pr.saman.service.morse.entry.rest.akka

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import ch.qos.logback.classic.Logger
import ir.pr.saman.service.morse.entry.rest.akka.api.TranslateBodyDTO
import ir.pr.saman.service.morse.entry.rest.akka.factory.ExceptionFactoryDTO
import ir.pr.saman.service.morse.entry.rest.akka.factory.Factory
import ir.pr.saman.service.morse.entry.rest.akka.factory.FactoryDTO
import ir.pr.saman.service.morse.entry.rest.akka.util.spray.SprayImplicits
import ir.pr.saman.service.morse.exception.NotSupportedException
import ir.pr.saman.service.morse.modules.ServiceModule.translateToMorseCodeService
import ir.pr.saman.service.morse.modules.config.akka.ActorModule._
import ir.pr.saman.service.morse.modules.config.akka.ActorModule.ec
import ir.pr.saman.service.morse.modules.config.akka.ServerModule._
import org.slf4j.LoggerFactory

import scala.concurrent.ExecutionContext
import scala.util.Failure
import scala.util.Success


class Server() {

  import Server.logger

  logger info "Starting REST server..."

  private val binding = Http().bindAndHandle(
    Server.route,
    interface,
    port
  )

  binding foreach { b =>
    logger info s"http server bound to: ${b.localAddress}"
  }

  Runtime.getRuntime addShutdownHook new Thread() {
    override def run(): Unit = {
      logger info "Stopping REST server..."
      binding flatMap (_ unbind()) onComplete (_ => actorSystem terminate())
      logger info "REST server stopped"
    }
  }
  logger info "REST server started"

}

object Server extends SprayImplicits {
  val logger: Logger = (LoggerFactory getLogger "REST API").asInstanceOf[Logger]

  def apply(): Server = new Server()

  def route(implicit ec: ExecutionContext): Route =
    pathPrefix("api" / "v1") {
      concat(
        path("services" / "codes" / "morse" / "healths") {
          get {
            complete("Morse Service is up and running")
          }
        },
        path("services" / "codes" / "morse" / "translate") {
          post {
            entity(as[TranslateBodyDTO]) { bodyDTO =>
              val body = Factory query bodyDTO
              val result = translateToMorseCodeService call body
              onComplete(result) {
                case Success(value) => complete(FactoryDTO message value)
                case Failure(exp: NotSupportedException) => complete(StatusCodes.NotImplemented, ExceptionFactoryDTO notSupportedException exp)
                case Failure(throwable) => failWith(throwable)
              }
            }
          }
        }
      )
    }

}

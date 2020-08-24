package ir.pr.saman.service.morse.modules.config.akka

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.Materializer
import com.typesafe.config.Config
import ir.pr.saman.service.morse.modules.config.ConfigModule

import scala.concurrent.ExecutionContext

object ActorModule extends ConfigModule {

  val conf: Config = config.getConfig("akka.http.server.preview").withFallback(config.resolve())

  implicit val actorSystem = ActorSystem("dbSystem", conf)
  implicit val mat: Materializer = ActorMaterializer()
  implicit val ec: ExecutionContext = actorSystem.dispatcher
}

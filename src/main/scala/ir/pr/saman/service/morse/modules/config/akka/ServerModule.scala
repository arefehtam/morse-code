package ir.pr.saman.service.morse.modules.config.akka

import ir.pr.saman.service.morse.modules.config.ConfigModule

object ServerModule extends ConfigModule {

  import config._

  lazy val interface: String = getString("akka.http.server.rest.interface")
  lazy val port: Int = getInt("akka.http.server.rest.port")

}

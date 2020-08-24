package ir.pr.saman.service.morse.entry.rest.akka.factory

import ir.pr.saman.service.morse.entry.rest.akka.api.exception.NotSupportedExceptionDTO
import ir.pr.saman.service.morse.exception.NotSupportedException

object ExceptionFactoryDTO {

  def notSupportedException(o: NotSupportedException): NotSupportedExceptionDTO = NotSupportedExceptionDTO(o.which, o.what)

}

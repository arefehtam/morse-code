package ir.pr.saman.service.morse.exception

case class NotSupportedException(which: String, what: Char) extends Throwable {

  override def getMessage: String = s"$which '${what.toString}' is not supported"

}

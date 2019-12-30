package support

import domain.User
import io.circe.Json.Null
import io.circe.{Decoder, Encoder, Json, Printer}
import io.circe.generic.semiauto
import zio.RIO
import org.http4s.circe.{jsonEncoderOf, jsonOf}
import org.http4s.{EntityDecoder, EntityEncoder}
import io.circe.syntax._
import zio.interop.catz._

trait CirceEncoder {

  implicit val UserEncoder: Encoder[User] = semiauto.deriveEncoder[User]

}

trait CirceDecoder {

  implicit val UserDecoder: Decoder[User] = semiauto.deriveDecoder[User]

}

trait JsonSupport extends CirceEncoder with CirceDecoder

trait JsonHttp4s[R] extends JsonSupport {

  type ClockRIO[A] = RIO[R, A]

  implicit val printer: Printer = Printer.noSpaces.copy(dropNullValues = true)

  implicit def valueAsJson[A](value: A)(implicit encoder: Encoder[A]): Json =
    Encoder[A](encoder)(value)

  implicit def optionAsJson[A](op: Option[A])(implicit encoder: Encoder[A]): Json =
    op.map(valueAsJson(_)(encoder)).getOrElse(Null)

  implicit def valueListAsJson[A](value: List[A])(implicit encoder: Encoder[List[A]]): Json = value match {
    case Nil  => Json.Null
    case list => encoder(list)
  }

  implicit class AnyObjectToJsonImplicit[T](obj: T) {
    implicit def toJson(implicit e: Encoder[T]): Json = obj.asJson
  }

  implicit def circeJsonDecoderCRIO[A](implicit decoder: Decoder[A]): EntityDecoder[ClockRIO, A] =
    jsonOf[ClockRIO, A]
  implicit def circeJsonEncoderCRIO[A](implicit encoder: Encoder[A]): EntityEncoder[ClockRIO, A] =
    jsonEncoderOf[ClockRIO, A]

}

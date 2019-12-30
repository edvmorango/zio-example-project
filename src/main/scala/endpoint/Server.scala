package endpoint

import cats.data.Kleisli
import cats.effect.ExitCode
import environment.Environments.AppEnvironment
import org.http4s.{Request, Response}
import org.http4s.server.Router
import org.http4s.server.blaze.BlazeServerBuilder
import zio.{RIO, ZIO}
import zio.interop.catz._
import org.http4s.implicits._

object Server {

  type ServerRIO[A] = RIO[AppEnvironment, A]

  def createRoutes(basePath: String): Kleisli[ServerRIO, Request[ServerRIO], Response[ServerRIO]] = {

    val userEndpoint = new UserEndpoint[AppEnvironment]("user").endpoints

    Router[ServerRIO](basePath -> userEndpoint).orNotFound
  }

  def createServer: ZIO[AppEnvironment, Throwable, Unit] = {

    ZIO
      .runtime[AppEnvironment]
      .flatMap { implicit rts =>
        BlazeServerBuilder[ServerRIO]
          .bindHttp(8080, "0.0.0.0")
          .withHttpApp(createRoutes("/workshop/v1"))
          .serve
          .compile[ServerRIO, ServerRIO, ExitCode]
          .drain
      }
  }

}

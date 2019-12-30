package endpoint

import domain.User
import environment.Environments.UserEnvironment
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import service.UserService
import support.JsonHttp4s
import zio.RIO
import zio.interop.catz._
class UserEndpoint[R <: UserEnvironment](rootUri: String) extends JsonHttp4s[R] {

  type UserEndpointTask[A] = RIO[R, A]

  val dsl: Http4sDsl[UserEndpointTask] = Http4sDsl[UserEndpointTask]

  import dsl._

  def endpoints: HttpRoutes[UserEndpointTask] = HttpRoutes.of[UserEndpointTask] {

    case req @ POST -> Root / `rootUri` =>
      val pipeline = for {
        user <- req.as[User]
        _    <- UserService.createUser(user).mapError(x => new Exception(x.toString))
      } yield user

      Ok(pipeline)

  }

}

package service

import domain.User
import environment.Environments.UserEnvironment
import failures.UserFailure
import zio.ZIO

object UserService {

  def createUser(user: User): ZIO[UserEnvironment, UserFailure, Unit] = ZIO.accessM { env =>
    val pipeline = for {
      _ <- env.userRep.insert(user)
    } yield ()

    pipeline
  }

}

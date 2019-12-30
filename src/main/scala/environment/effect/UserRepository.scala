package environment.effect

import domain.User
import environment.config.DBConfig
import failures.UserFailure.UserRepositoryFailure
import zio.{Ref, ZIO}

trait UserRepository {

  val userRep: UserRepository.Effect

}

object UserRepository {

  trait Effect {

    def insert(user: User): ZIO[DBConfig, UserRepositoryFailure, Unit]

  }

  trait Live {

    def userRep(ref: Ref[Map[Int, User]]) = new Effect {
      override def insert(user: User): ZIO[DBConfig, UserRepositoryFailure, Unit] = ZIO.accessM { env =>
        for {
          _ <- ZIO.effectTotal(println(s"Connected at: ${env.dbconfig.url}"))
          _ <- ref.update(c => c ++ Map(user.id -> user))
        } yield ()
      }
    }

  }

  object Live extends Live
}

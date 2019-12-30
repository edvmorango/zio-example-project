package app

import domain.User
import endpoint.Server
import environment.Environments
import zio.{App, Ref, ZIO}

object MainApp extends App {

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, Int] = {

    val program = for {
      userDb <- Ref.make[Map[Int, User]](Map.empty)
      env    <- Environments.createEnvironment(userDb)
      _      <- Server.createServer.provide(env)
    } yield ()

    program.either.as(1)

  }
}

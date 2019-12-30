package environment

import domain.User
import environment.config.DBConfig
import environment.effect.UserRepository
import zio.{Ref, ZIO}
import zio.blocking.Blocking
import zio.clock.Clock
import zio.console.Console
import zio.random.Random
import zio.system.System

object Environments {

  type AppEnvironment = Clock with Console with System with Random with Blocking with DBConfig with UserRepository

  type UserEnvironment = Blocking with DBConfig with UserRepository

  def createEnvironment(ref: Ref[Map[Int, User]]): ZIO[Any, Nothing, AppEnvironment] =
    ZIO.effect {
      new Clock with Console with System with Random with Blocking with DBConfig with UserRepository {
        override val clock: Clock.Service[Any]       = Clock.Live.clock
        override val console: Console.Service[Any]   = Console.Live.console
        override val system: System.Service[Any]     = System.Live.system
        override val random: Random.Service[Any]     = Random.Live.random
        override val blocking: Blocking.Service[Any] = Blocking.Live.blocking
        override val dbconfig: DBConfig.Config       = DBConfig.Live.dbconfig
        override val userRep: UserRepository.Effect  = UserRepository.Live.userRep(ref)
      }
    }.orDie
}

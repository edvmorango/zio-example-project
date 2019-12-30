package failures

sealed trait UserFailure

object UserFailure {

  case class InvalidName(name: String) extends UserFailure

  case class PasswordFailure(msg: String) extends UserFailure

  case class UserRepositoryFailure(f: UserRepositoryFailure) extends UserFailure

}

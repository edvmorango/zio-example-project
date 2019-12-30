package environment.config

trait DBConfig {

  val dbconfig: DBConfig.Config

}

object DBConfig {

  trait Config {

    val url: String
    val user: String
    val password: String

  }

  trait Live {

    val dbconfig: Config = new Config {
      override val url: String      = "..."
      override val user: String     = "..."
      override val password: String = "..."
    }

  }

  object Live extends Live

}

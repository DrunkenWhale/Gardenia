package gardenia.connect

import java.sql.Connection

trait DataBaseConnection {
  val mysqlUrl: String
  val user: String
  val password: String
  val connection: Connection
}

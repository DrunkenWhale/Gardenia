package gardenia.connect

import java.sql.Connection

object DataBaseManager {

  private var connection: DataBaseConnection = _

  def getConnection: Connection = {
    connection.connection
  }

  def register(dataBaseConnection: DataBaseConnection): Unit = {
    connection = dataBaseConnection
  }

}

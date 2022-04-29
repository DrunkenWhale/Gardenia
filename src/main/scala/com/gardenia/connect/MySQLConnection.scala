package com.gardenia.connect

import java.sql.{Connection, DriverManager}

object MySQLConnection {

  //  Class.forName("com.mysql.jdbc.Driver")

}

final case class MySQLConnection(override val mysqlUrl: String,
                                 override val user: String,
                                 override val password: String
                                ) extends DataBaseConnection {
  override val connection: Connection =
    DriverManager.getConnection(mysqlUrl, user, password)
}
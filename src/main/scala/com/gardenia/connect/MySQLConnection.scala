package com.gardenia.connect

import java.sql.{Connection, DriverManager}

object MySQLConnection extends DataBaseConnection {

  //  Class.forName("com.mysql.jdbc.Driver")

  private val mysqlUrl: String = "jdbc:mysql://localhost/test"
  private val user = "root"
  private val password = "3777777"

  protected[gardenia] override val connection: Connection = DriverManager.getConnection(mysqlUrl, user, password)

}

package com.gardenia.connect

import java.sql.Connection

trait DataBaseConnection {
  protected val connection: Connection
}

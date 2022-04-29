package com.gardenia.connect

import java.sql.Connection
import scala.collection.mutable.ListBuffer

object TableMeta {

  private val excludeTableName = Array("sys_config")

  def getTableMetaList: List[TableMeta] = {
    val connection = DataBaseManager.getConnection
    val tableSet = connection
      .getMetaData
      .getTables(null,
        null,
        null,
        Array[String]("TABLE")
      )
    val res = ListBuffer[TableMeta]()
    while (tableSet.next()) {
      val name = tableSet.getString("TABLE_NAME")
      if (!excludeTableName.contains(name)) {
        res.addOne(
          TableMeta(
            name = name,
            columnList = getColumnNameToTypeList(name)
          )
        )
      }
    }
    res.result()
  }

  private[gardenia] def getColumnNameToTypeList(tableName: String): List[(String, String)] = {
    val connection = DataBaseManager.getConnection
    val resSet = connection
      .getMetaData
      .getColumns(null,
        null,
        tableName,
        "%")
    val columnListBuffer = ListBuffer[(String, String)]()
    while (resSet.next()) {
      columnListBuffer.addOne(
        (
          resSet.getString("COLUMN_NAME"),
          resSet.getString("TYPE_NAME")
        )
      )
    }
    columnListBuffer.result()
  }

}

final
case class TableMeta(
                      name: String,
                      columnList: List[(String, String)]
                    )
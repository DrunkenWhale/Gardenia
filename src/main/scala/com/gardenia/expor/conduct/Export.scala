package com.gardenia.expor.conduct

import com.gardenia.connect.{DataBaseManager, MySQLConnection, TableMeta}
import com.gardenia.expor.tpe.{ExportTypes, JsonType}

import scala.reflect.ClassTag

class Export(override private[expor] val exportPath: String) extends ExportConduct {

  def exportCode[T <: ExportTypes]()(implicit clsTag: ClassTag[T]): Unit = {
    val operationCode = {
      clsTag.runtimeClass.getSimpleName match
        case "JsonType" => 1
        case "JavaClassType" => 2
        case "CaseClassType" => 3
        case x: String => throw new Exception("Unknown Type: " + x)
    }
    DataBaseManager.register(MySQLConnection(
      mysqlUrl = "jdbc:mysql://localhost:3306/test", user = "root", password = "3777777"
    ))
    val tableMetaList = TableMeta.getTableMetaList
    println(tableMetaList)
  }

}


package com.gardenia.expor.conduct

import com.gardenia.connect.{DataBaseManager, MySQLConnection, TableMeta}
import com.gardenia.expor.tpe.{ExportTypes, JsonType}

import scala.reflect.ClassTag

class Export(override private[expor] val exportPath: String) extends ExportConduct {

  def exportSourceCode[T <: ExportTypes]()(implicit clsTag: ClassTag[T]): Unit = {
    val operationCode = {
      clsTag.runtimeClass.getSimpleName match
        case "JsonType" =>
          val tableMetaList = TableMeta.getTableMetaList
        //TODO to json (using rosemary)
        case "JavaClassType" =>
          val tableMetaList = TableMeta.getTableMetaList
        //TODO to java class
        case "CaseClassType" =>
          val tableMetaList = TableMeta.getTableMetaList
        //TODO to scala case class
        case x: String => throw new Exception("Unknown Type: " + x)
    }


  }

}


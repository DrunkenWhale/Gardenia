package com.gardenia.expor.conduct

import com.gardenia.connect.{DataBaseManager, MySQLConnection, TableMeta}
import com.gardenia.expor.conduct.Export.generatorCaseClass
import com.gardenia.expor.tpe.{ExportTypes, JsonType}

import java.io.File
import java.nio.file.attribute.FileAttribute
import java.nio.file.{Files, Path, StandardOpenOption}
import scala.reflect.ClassTag

class Export(override private[expor] val exportPath: String) extends ExportConduct {

  def exportSourceCode[T <: ExportTypes]()(implicit clsTag: ClassTag[T]): Unit = {

    clsTag.runtimeClass.getSimpleName match
      case "JsonType" =>
        val tableMetaList = TableMeta.getTableMetaList
      //TODO to json (using rosemary)
      case "JavaClassType" =>
        val tableMetaList = TableMeta.getTableMetaList
      //TODO to java class
      case "CaseClassType" => {
        val tableMetaList = TableMeta.getTableMetaList
        tableMetaList.foreach(
          meta => {
            val path: Path = Path.of(exportPath + File.separator + meta.name + ".scala")
            println(path.getFileName)
            Files.createFile(path)
            Files.write(path, generatorCaseClass(meta).getBytes(), StandardOpenOption.WRITE)
          }
        )
      }
      //TODO to scala case class
      case x: String => throw new Exception("Unknown Type: " + x)
  }

}

object Export {
  private def generatorCaseClass(tableMeta: TableMeta): String = {
    s"case class ${tableMeta.name}" +
      tableMeta.columnList.map((name, tpe) => s"$name: ${typeMapping(tpe)}").mkString("(", ",", ")")
  }

  private def typeMapping(typeName: String): String = {
    typeName match
      case "INT" => "Int"
      case "TEXT" | "CHAR" | "VARCHAR" => "String"
      case "BIT" => "Boolean"
      case x => throw new Exception(s"Unknown SQL Type $x")
  }

}


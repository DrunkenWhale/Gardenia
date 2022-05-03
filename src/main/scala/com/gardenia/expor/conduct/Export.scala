package com.gardenia.expor.conduct

import com.gardenia.connect.{DataBaseManager, MySQLConnection, TableMeta}
import com.gardenia.expor.conduct.Export.{generatorCaseClass, generatorJavaClass, typeMappingJavaType}
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
        tableMetaList.foreach(
          meta => {
            val path: Path = Path.of(exportPath + File.separator + meta.name + ".java")
            Files.createFile(path)
            Files.write(path, generatorJavaClass(meta).getBytes(), StandardOpenOption.WRITE)
          }
        )
      //TODO to java class

      case "CaseClassType" => {
        val tableMetaList = TableMeta.getTableMetaList
        tableMetaList.foreach(
          meta => {
            val path: Path = Path.of(exportPath + File.separator + meta.name + ".scala")
            Files.createFile(path)
            Files.write(path, generatorCaseClass(meta).getBytes(), StandardOpenOption.WRITE)
          }
        )
      }

      case x: String => throw new Exception("Unknown Type: " + x)
  }

}

object Export {

  private def generatorJavaClass(tableMeta: TableMeta): String = {

    val parameterList = tableMeta.columnList.map((name, tpe) =>
      s"private ${typeMappingJavaType(tpe)} $name;"
    ).mkString("\n")

    val constructorParameters = tableMeta.columnList.map((name, tpe) => s"${typeMappingJavaType(tpe)} $name").mkString(",")

    val constructorBody = tableMeta.columnList.map((name, tpe) => s"this.$name=$name;").mkString("\n")

    val constructor =
      s"""
         |public ${tableMeta.name}($constructorParameters){
         |   $constructorBody
         |}
         |""".stripMargin

    val beanMethodList = tableMeta.columnList.map((name, tpe) => {
      val t = typeMappingJavaType(tpe)
      s"""
         |public $t get${name.charAt(0).toUpper.toString.appendedAll(name.substring(1))}(){
         |  return this.$name
         |}
         |
         |public void set${name.charAt(0).toUpper.toString.appendedAll(name.substring(1))}($t $name){
         |    this.$name = $name
         |}
         |""".stripMargin
    }).mkString("\n")


    s"""
       |public class ${tableMeta.name}{
       |
       |$parameterList
       |
       |$constructor
       |
       |$beanMethodList
       |
       |}
       |""".stripMargin
  }

  private def typeMappingJavaType(typeName: String): String = {
    typeName match
      case "INT" => "int"
      case "TEXT" | "CHAR" | "VARCHAR" => "String"
      case "BIT" => "boolean"
      case x => throw new Exception(s"Unknown SQL Type $x")
  }

  private def generatorCaseClass(tableMeta: TableMeta): String = {
    s"case class ${tableMeta.name}" +
      tableMeta.columnList.map((name, tpe) => s"$name: ${typeMappingScalaType(tpe)}").mkString("(", ",", ")")
  }

  private def typeMappingScalaType(typeName: String): String = {
    // TODO
    //expansion type mapping
    typeName match
      case "INT" => "Int"
      case "TEXT" | "CHAR" | "VARCHAR" => "String"
      case "BIT" => "Boolean"
      case x => throw new Exception(s"Unknown SQL Type $x")
  }

}


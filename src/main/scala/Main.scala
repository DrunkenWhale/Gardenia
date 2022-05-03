import gardenia.connect.{DataBaseManager, MySQLConnection}
import gardenia.expor.conduct.Export
import gardenia.expor.tpe.{CaseClassType, ExportTypes, JavaClassType, JsonType}

import java.sql.{Connection, DriverManager}
import scala.collection.mutable.ListBuffer

@main
def main(): Unit = {
  DataBaseManager.register(MySQLConnection(
    mysqlUrl = "jdbc:mysql://localhost:3306/test", user = "root", password = "password"
  ))
  Export("C:\\Users\\Nephren\\Data\\Project\\Gardenia\\test")
    .exportSourceCode[JsonType]()

  Export("C:\\Users\\Nephren\\Data\\Project\\Gardenia\\test")
    .exportSourceCode[JavaClassType]()

  Export("C:\\Users\\Nephren\\Data\\Project\\Gardenia\\test")
    .exportSourceCode[CaseClassType]()
}


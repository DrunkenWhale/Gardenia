import com.gardenia.connect.{DataBaseManager, MySQLConnection}
import com.gardenia.expor.tpe.{CaseClassType, ExportTypes}

import java.sql.{Connection, DriverManager}
import scala.collection.mutable.ListBuffer

@main
def main(): Unit = {
  DataBaseManager.register(MySQLConnection(
    mysqlUrl = "jdbc:mysql://localhost:3306/test", user = "root", password = "3777777"
  ))
  com.gardenia.expor.conduct.Export("C:\\Users\\Nephren\\Data\\Project\\Gardenia").exportSourceCode[CaseClassType]()
}


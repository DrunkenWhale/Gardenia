import com.gardenia.connect.MySQLConnection
import com.gardenia.expor.tpe.{CaseClassType, ExportTypes}

import java.sql.{Connection, DriverManager}
import scala.collection.mutable.ListBuffer

@main
def main(): Unit = {
  com.gardenia.expor.conduct.Export("").exportCode[CaseClassType]()
}


import java.sql.{Connection, DriverManager}

@main
def main(): Unit = {
  //  Class.forName("com.mysql.jdbc.Driver")
  val mysqlUrl: String = "jdbc:mysql://localhost"
  val user = "root"
  val password = "3777777"
  val connection: Connection = DriverManager.getConnection(mysqlUrl, user, password)
  val res = connection.prepareStatement("SHOW DATABASES;").executeQuery()
  while (res.next()) {
    println(res.getString(1))
  }
  println(res.getObject(1))
}
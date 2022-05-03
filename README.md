# Gardenia

db codegen tool

## example

```scala

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
    .exportSourceCode[CaseClasssType]()
}


```

may be generator like this:

### JsonType

```json

[
  {
    "test": {
      "name": "TEXT",
      "id": "INT"
    }
  },
  {
    "test1": {
      "gender": "BIT",
      "name": "TEXT",
      "id": "INT"
    }
  },
  {
    "test2": {
      "id": "INT"
    }
  },
  {
    "test3": {
      "a": "TEXT",
      "b": "TIMESTAMP",
      "id": "INT"
    }
  }
]

```

### JavaClassType

for example, test3

```java


public class test3 {

    private int id;
    private String a;
    private java.sql.Timestamp b;


    public test3(int id, String a, java.sql.Timestamp b) {
        this.id = id;
        this.a = a;
        this.b = b;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getA() {
        return this.a;
    }

    public void setA(String a) {
        this.a = a;
    }


    public java.sql.Timestamp getB() {
        return this.b;
    }

    public void setB(java.sql.Timestamp b) {
        this.b = b;
    }


}

```

### CaseClassType

like before, test3

```scala

case class test3(id: Int, a: String, b: java.sql.Timestamp)

```
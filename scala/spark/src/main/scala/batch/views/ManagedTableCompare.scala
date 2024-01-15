package batch.views

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

object ManagedTableCompare extends App {

  private def createContext(): SparkSession = {
    SparkSession.builder()
      .config(new SparkConf()
        .set("spark.sql.warehouse.dir", "/home/abhishek/workspace/data/hive-metastore"))
      .master("local[*]").getOrCreate()
  }

  private def testSqlForSaveAsTable(ss: SparkSession): Unit = {
    val df = ss.createDataFrame(
      ss.sparkContext.parallelize(Seq(Row("Abhishek", 0), Row("Abhinav", 8))),
      StructType(Array(StructField("name", StringType), StructField("age", IntegerType))))
    df.write.mode("overwrite").saveAsTable("managedTable")
    ss.sql("select * from managedTable").show()
  }

  private def testSqlForSave(ss: SparkSession): Unit = {
    val df = ss.createDataFrame(
      ss.sparkContext.parallelize(Seq(Row("Abhishek", 0), Row("Abhinav", 8))),
      StructType(Array(StructField("name", StringType), StructField("age", IntegerType))))
    df.write.mode("overwrite").save("/home/abhishek/workspace/data/spark-data-dir/unmanagedTable")
    ss.read.parquet("/home/abhishek/workspace/data/spark-data-dir/unmanagedTable").show
  }

  val ss = createContext()
  testSqlForSaveAsTable(ss)
  testSqlForSave(ss)
}

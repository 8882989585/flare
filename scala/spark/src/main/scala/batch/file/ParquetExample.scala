package batch.file

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object ParquetExample extends App {

  private def partitionParquetWithDFW(): Unit = {
    val ss = SparkSession.builder().master("local[*]").getOrCreate()
    val df = ss.read.parquet("/home/abhishek/workspace/flare/data/spark/batch/file/Titanic.parquet")
    df.write
      .partitionBy("Survived", "Sex")
      .mode("overwrite")
      .save("/home/abhishek/workspace/data/parsed/titanic_partitioned_table")
  }

  private def partitionParquetWithDF(): Unit = {
    val ss = SparkSession.builder().master("local[*]").getOrCreate()
    val df = ss.read.parquet("/home/abhishek/workspace/flare/data/spark/batch/file/Titanic.parquet")
    df.repartition(col("Sex"), col("Survived"))
      .write
      .mode("overwrite")
      .save("/home/abhishek/workspace/data/parsed/titanic_partitioned_table")
  }

  partitionParquetWithDFW()
  partitionParquetWithDF()
}

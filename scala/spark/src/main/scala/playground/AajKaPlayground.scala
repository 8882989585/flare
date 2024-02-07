package playground

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{col, desc, lit, sum, udf}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession, functions}

object AajKaPlayground extends App {

  def creatingSparkSession(): SparkSession = {
    val ss = SparkSession.builder().master("local[*]").getOrCreate()
    ss
  }

  def creatingAnRdd(): Unit = {
    val ss = creatingSparkSession()
    import ss.implicits._
    val rdd = ss.sparkContext.parallelize(Seq(("Abhishek"), ("Abhinav")), 4)
    val df = rdd.toDF("name")
    val rdd2 = ss.sparkContext.parallelize(Seq(Row("Abhishek", 11)))
    val df2 = ss.createDataFrame(rdd2, StructType(Array(StructField("name", StringType), StructField("age", IntegerType))))
    df.show()
    df2.show()
    rdd.map(x => x.concat("jim")).toDF("ename").show
  }

  //  creatingAnRdd()

  def rddTransfromations(): Unit = {
    val ss = creatingSparkSession()
    import ss.implicits._
    val data2 = Array(
      ("Abhishek", 1, 2), ("Abhinav", 67, 4), ("Abhishek", 1, 5), ("Abhishek", 1, 6), ("Abhishek", 1, 7),
      ("Abhishek", 1, 8), ("Abhinav", 67, 7), ("Abhinav", 67, 8), ("Abhinav", 45, 7))
    val rdd2 = ss.sparkContext.parallelize(data2, 4)

    val t = rdd2.map(l => (l._1, (l._2, l._3))).aggregateByKey(0)((a: Int, v: (Int, Int)) => (a + v._2), (a: Int, v: Int) => a + v)
    val v = rdd2.map(l => (l._1, (l._2, l._3))).reduceByKey((v: (Int, Int), u: (Int, Int)) => (v._1 + u._1, v._2 + u._2))
    t.foreach(l => print(l))
  }

  //  rddTransfromations()

  val myUDF = (mm: Int) => {
    mm * 10
  }

  def readingACsv(): Unit = {
    val ss = creatingSparkSession()
    val df = ss.read.schema("name String, marks INt").options(Map("header" -> "true")).csv("/home/abhishek/Downloads/data1.csv")
    //    df.show()
    //    df.groupBy(col("name")).agg(Map("marks" -> "sum")).show()
    val df2 = df.groupBy(col("name")).agg(sum("marks").as("t_sum"))
    val df3 = df.select(col("name").alias("name3"), (functions.max(col("marks")) over Window.partitionBy("name").orderBy(desc("marks"))).as("max_m"))
      .groupBy(col("name3"))
      .agg(functions.max("max_m").as("mm"))
    val df4 = df2.join(df3, df2("name") === df3("name3")).where(col("mm").geq(lit(40)))
    val df5 = df4.select(col("name"), col("mm"), udf(myUDF).apply(col("mm")).as("mmt"))
    val df6 = df5.union(df5)
    val df7 = df6.distinct()

    df7.rdd.foreachPartition(p => p.foreach(r => {
      println("==========================================================")
      println("==========================================================")
      println(r.get(2).asInstanceOf[Int] - r.get(1).asInstanceOf[Int])
    }))
  }


  readingACsv()
}

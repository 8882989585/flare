import pyspark
from pyspark.sql import SparkSession
from pyspark.sql import Row

spark = SparkSession \
    .builder \
    .appName("mongodbtest1") \
    .master('local') \
    .config("spark.mongodb.input.uri", "mongodb://127.0.0.1") \
    .config("spark.mongodb.output.uri", "mongodb://127.0.0.1") \
    .config('spark.jars.packages', 'org.mongodb.spark:mongo-spark-connector_2.12:3.0.1') \
    .getOrCreate()

df = (((spark.read.format("com.mongodb.spark.sql.DefaultSource")
             .option("database", "recon"))
             .option("collection", "idx_check")
             .option("hint", """{"x": 1,"y":1,"z":1}"""))
             .load())

df.filter(df.x == 0).show(truncate=False)
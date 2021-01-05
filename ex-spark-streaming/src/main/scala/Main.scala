import org.apache.commons.codec.StringDecoder
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object Main {

  def main(args: Array[String]): Unit = {
    import org.apache.spark._

    val conf = new SparkConf().setMaster("spark://192.168.83.2:7077")
      .set("spark.executor.memory", "512M")
      .set("spark.dynamicAllocation.enabled", "false")
      .set("num.executors", "1")
      .set("spark.memory.fraction", "0.7")
      .set("spark.executor.cores", "2")
      .set("spark.cores.max", "4")
      .set("spark.default.parallelism", "6")
      .setJars(Seq("/Users/windlively/IdeaProjects/leisure/ex-spark-streaming/target/ex-spark-streaming-uranus.jar"))
      .set("log4j.logger.org.apache.spark.storage.BlockManager", "TRACE")
      .setAppName("NetWorkCount");
    val ssc = new StreamingContext(conf, Seconds(10))
//    val lines = ssc.socketTextStream("192.168.83.1", 9999)
//    val words = lines.flatMap(_.split(" "))
//    val pairs = words.map(w => (w, 1))
//    val wordCount = pairs.reduceByKey(_ + _)

//    wordCount.print()
//    ssc.start()
//    ssc.awaitTermination()

    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "192.168.83.1:9093",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "spark-streaming-test",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )

    val kafkaStream = KafkaUtils.createDirectStream(ssc=ssc, LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe(Seq("spider_weibo"), kafkaParams))

    kafkaStream.foreachRDD(rdd => {
      rdd.foreach(r => {
        System.err.println(s"offset: ${r.offset}\nmessage: ${r.value}")
      })
    })
    ssc.start();
    ssc.awaitTermination()
  }
}

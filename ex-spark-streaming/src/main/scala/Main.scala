import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.codec.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.internal.Logging
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.codehaus.jackson.`type`._

import java.lang.String
import java.util.HashMap
import scala.collection.mutable
import scala.util.parsing.json.JSON


object Main extends Logging {

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
      .setAppName("KafkaStreamingTest");
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

    val kafkaStream = KafkaUtils.createDirectStream(ssc = ssc, LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe(Seq("spider_weibo"), kafkaParams))
    val objectMapper = new ObjectMapper();

    kafkaStream.foreachRDD(rdd => {
      if (!rdd.isEmpty()) {
        val str = rdd.map(s => JSON.parseFull(s.value).get.asInstanceOf[Map[String, Any]])
          .map(_.getOrElse("content", "").toString)
          .reduce(_ + "\n" + _)
        logInfo(s"$str")
      }
      logInfo(s"rdd count: ${rdd.count}")
    })
    ssc.start();
    ssc.awaitTermination()
  }
}

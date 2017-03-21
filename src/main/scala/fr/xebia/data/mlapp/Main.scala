package fr.xebia.data.mlapp

import fr.xebia.data.mlapp.tweets.Tweets
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Minutes, Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}


object Main {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("ML App")
      .set("spark.driver.allowMultipleContexts", "true")

    val sc = new SparkContext(conf)
    sc.setLogLevel("ERROR")

    val ssc = new StreamingContext(conf, Minutes(5))

    val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = args.take(4)

    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)

    val filters = Seq("#Presidentielle2017", "Presidentielle", "Présidentielle", "présidentielle", "presidentielle", "Hamon", "Macron", "Mélenchon", "Melenchon", "Fillon", "Le Pen")
    val stream = TwitterUtils.createStream(ssc, None, filters)

    val text = stream.map(l => Tweets(l.getId, l.getText, l.getCreatedAt, l.getUser.getName, l.getUser.getScreenName))

    text.saveAsTextFiles("~/aphelip/tweets/tweets")

    ssc.start()
    ssc.awaitTermination()
  }

}

package fr.xebia.data.mlapp.tweets

import java.util.Date

import org.scalatest.{FlatSpec, Matchers}


class TweetsTest extends FlatSpec with Matchers {

  "Date" should "be correctly converted" in {
    val date = new Date(1492970400000l)  // 23th of April 8PM, result of the first round
    Tweets.cleanDate(date) shouldBe "2017-04-23 20:00:00"
  }

  "Text" should "be correctly converted to right CSV format" in {
    val tweet = """This a \ horific "tweet" we have here | no ? """
    Tweets.cleanText(tweet) shouldBe """"This a \\ horific \"tweet\" we have here | no ? """"
  }

}

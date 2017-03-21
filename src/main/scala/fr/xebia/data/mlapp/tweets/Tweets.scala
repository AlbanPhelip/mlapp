package fr.xebia.data.mlapp.tweets

import java.util.Date

import org.joda.time.format.DateTimeFormat


case class Tweets(id: Long,
                  text: String,
                  createdAt: Date,
                  userName: String,
                  userScreenName: String) {

  override def toString: String = {
    s"$id|${Tweets.cleanText(text)}|${Tweets.cleanDate(createdAt)}|${Tweets.cleanText(userName)}|${Tweets.cleanText(userScreenName)}"
  }

}


object Tweets {
  def cleanText(text: String, quoteChar: Char = '"', escapeChar: Char = '\\'): String = {
    val replacedText = text
      .replace(s"$escapeChar", s"$escapeChar$escapeChar")
      .replace(s"$quoteChar", s"$escapeChar$quoteChar")

    s"$quoteChar$replacedText$quoteChar"
  }

  def cleanDate(date: Date): String = {
    DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").print(date.getTime)
  }
}

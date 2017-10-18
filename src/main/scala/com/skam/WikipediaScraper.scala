package com.skam

import com.skam.{Logger => SKLogger}
import net.liftweb.json.JsonAST.{JField, JObject, JString}
import net.ruippeixotog.scalascraper.model.Document
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupDocument
import net.ruippeixotog.scalascraper.model.Element

import scala.collection.mutable.Queue

/** Implements functions for our abstract logger
  * @see Scraper.scal
  * @param logger
  */
class WikipediaScraper(logger: SKLogger) extends Scraper(logger) {

  val MOVIE_TABLE : String = ""
  val ACTOR_TABLE : String = ""
  val linksToVist = new Queue[String]()

  /**
    * Overload default constructor
    */
  def this() {
    this(null)
  }

  def getMovieKeys(doc: JsoupBrowser.JsoupDocument) : List[String] = {
    (doc >> extractor("table.infobox.vevent th", texts, asIs[Iterable[String]])).toList
  }

  def getMovieValues(doc: JsoupBrowser.JsoupDocument) : List[Element] = {
    doc >> extractor("table.infobox.vevent td", elementList)
  }

  def getMoveTextValues(doc: JsoupBrowser.JsoupDocument) : List[String] = {
    (doc >> extractor("table.infobox.vevent td", texts, asIs[Iterable[String]])).toList
  }

  /**
    * Extract all a tags inside li elements
    * Actor tables have the following format
    * <li>
    *   <a href="/wiki/actor_name" title="actor_name">Actor Name</a>
    * </li>
    * @param doc
    * @return
    */
  def scrapeActorTable(doc: JsoupDocument) : JObject = {
    doc >?> elementList("table.infobox.vevent tr") match {
      /**
        * Unwraps from option
        * then maps actor name to a link
        */
      case Some(e)  => JObject(getMovieKeys(doc) zip getMoveTextValues(doc) map { case (k, v) => JField(k, JString(v))})
      case None     => JObject(null)
    }
  }

  /**
    * Given valid movie document,
    * parses html for relevant data
    *
    * @param doc
    * @return
    */
  def parseMovieDoc(doc: Document) : JObject = {
    val movieData: Iterable[JField]  = ((doc >> texts("th")) zip (doc >> texts("td")) ) map { case (x, y) => JField(x, JString(y)) }
    JObject(movieData.toList)
  }

  /**
    * Given valid movie document,
    * parses for the cast block
    * @param doc
    * @return
    */
  def parseCast(doc: Document) : Unit = {
  }

  /**
    * Determines whether given HTML is valid for our spider to parse
    * @return is valid page
    */
  def isValidMoviePage(doc : Document): Boolean = {
    doc >?> element("table.infobox.vevent") match {
      case None => false
      case _    => true
    }
  }

  /**
    * Given a valid HTML document, determines whether a valid actor page is given
    * @param doc
    * @return
    */
  def isValidActorPage(doc : Document) : Boolean = {
    doc >?> element("table.infobox.biograph.vcard") match {
      case Some(i)    => true
      case None       => false
    }
  }

  /**
    *
    * @param doc
    */
  def getMovieTitle(doc: Document) : String = {
    doc >> text("title")
  }

}

/**
  * Companion Object for our scraper class
  */
object WikipediaScraper {
  def apply() : WikipediaScraper = new WikipediaScraper()
  def apply(logger: SKLogger): WikipediaScraper = new WikipediaScraper(logger)

  /**
    * Driver for our scraping program
    *
    */
  def main() : Unit = {
  }
}
package com.skam

import net.liftweb.json.JsonAST.JObject
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.browser.JsoupBrowser.JsoupDocument
import org.scalatest.FunSuite
import org.scalatest.Matchers._

class WikipediaScraperTest extends FunSuite {
  val scraper = new WikipediaScraper()
  val browser = JsoupBrowser()
  val validDoc : JsoupDocument = browser.get("https://en.wikipedia.org/wiki/The_Notebook").asInstanceOf[JsoupDocument]
  //val invalidDoc : browser.DocumentType = browser.get("")
  val validActor : JsoupDocument = browser.get("https://en.wikipedia.org/wiki/James_Garner").asInstanceOf[JsoupDocument]
  //val invalidActor : browser.DocumentType = browser.get("")


  test("testGetMovieKeys") {
    val keys = scraper.getMovieKeys(validDoc)
    keys.length shouldBe 19
  }

  test ("testGetMovieValues") {
    val values = scraper.getMovieValues(validDoc)
    values.length shouldBe 19
  }

  test ("testScrapeActorTable") {
    val obj : JObject = scraper.scrapeActorTable(validDoc)
    obj should not be None
  }

  test("testGetMovieJSON") {
    scraper.parseMovieDoc(validDoc) should not be null
  }

  test("testGetMovieTitle") {
    scraper.getMovieTitle(validDoc) should startWith ("The Notebook")
  }

  test("testIsValidMoviePage") {
    scraper.isValidMoviePage(validDoc) shouldBe true
  }

}

package com.skam

import com.skam.{Logger => SKLogger}
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import org.scalatest.FunSuite
import org.scalatest.Matchers._

class ScraperTest extends FunSuite {
  //val logger : Logger = new SKLogger("scraper_test.log", 1)
  val scraper : Scraper = new WikipediaScraper()

  test("testLog") {
    scraper.log shouldBe a [SKLogger]
  }

  test("testUserAgents") {
    scraper.userAgents shouldBe a [List[String]]
    scraper.browsers.length should equal (scraper.userAgents.length)
  }

  test("testBrowsers") {
    scraper.browsers shouldBe a [List[JsoupBrowser]]
    scraper.browsers.length should equal (scraper.userAgents.length)
  }

}

package com.skam

import com.skam.{Logger => SKLogger}
import org.scalatest.FunSuite
import org.scalatest.Matchers._

import scala.io.Source

class LoggerTest extends FunSuite {
  val filename : String = "wiki.log"
  val verbosity : Int = 1
  //val logger : SKLogger = new SKLogger(filename, verbosity)
  val visitURL = "https://wikipedia.com/the_notebook"
  val visitingLine : String = "Visiting: " + visitURL
  val warning = "Invalid HTML"
  val warningLine : String = "Warning: " + warning
  val failure = "Stackoverflow"
  val failureLine : String = "Failure: " + failure
  val success = "Good Job"
  val successLine : String = "Success: " + success
/*
  test("testLog") {
    filename should be (logger.logName)
    verbosity should be (logger.level)
  }

  test("testGetLine") {
    logger.visiting(visitURL)
    Source.fromFile(logger.logName).getLines().foreach(println)
    logger.getLines() should not equal List()
  }

  test("testVisiting") {
    logger.visiting(visitURL)
    logger.getLines().foreach(println)
    logger.getLines().contains(visitingLine) shouldBe true
  }

  test("testWriteWarning") {
    logger.writeWarning(warning)
    logger.getLines().foreach(println)
    logger.getLines().contains(warningLine) shouldBe true
  }

  test("testWriteFailure") {
    logger.writeFailure(failure)
    logger.getLines().foreach(println)
    logger.getLines().contains(failureLine) shouldBe true
  }

  test("testWriteSuccess") {
    logger.writeSuccess(success)
    logger.getLines().foreach(println)
    logger.getLines().contains(successLine) shouldBe true
  }

  test("testIsBlank") {
    logger.isBlank(None) shouldBe true
    logger.isBlank(Option("valid input")) shouldBe false
  }

  test("testCloseWriter") {
    logger.closeWriter()
  }
  */
}

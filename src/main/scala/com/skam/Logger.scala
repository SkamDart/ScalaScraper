package com.skam

/**
  * Java Imports
  */
import java.io._

/**
  * Scala Imports
  */
import scala.io.Source

trait Logger {
  protected def print(message: String)

  // override this to be notified before the application
  // quits due to an error
  protected def cleanup(): Unit = {
    println("Error")
  }

  def info(message: String) {
    print(s"[info] $message")
  }

  def warning(message: String) {
    print(s"[warning] $message")
  }

  def error(message: String) = {
    print(s"[error] $message")
    cleanup()
    sys.exit(1)
  }
}

/**
  * Simple class that writes to a file
  * @param logfile file to append to
  * @param verbosity
  */
class SkamLogger(logfile: String, verbosity: Int) extends Logger {

  val logName : String = logfile
  val level : Int = verbosity
  val f : File = new File(logName)

  /** One PrintWriter object per class
    * Creates file if doesn't exit
    */
  private val writer : BufferedWriter = new BufferedWriter(new FileWriter(f))

  override def print(message: String): Unit = ???

  /** Get's classes private printwriter instance
    * @return PrintWriter instance
    */
  def getWriter : BufferedWriter = writer

  /** Closes writer object
    * Should be called at exit
    */
  def closeWriter() : Unit = {
    writer.close()
  }

  /** Reads log file into List of strings
    * Used primarily for testing
    * @return
    */
  def getLines() : List[String] = Source.fromFile(logName).getLines().toList

  /** Checks if input from user is valid to be written to file or not
    * Makes use of the option abstact class, if interested see
    * @see http://www.scala-lang.org/api/2.12.3/scala/Option.html
    * @param input string to be written to file
    * @return
    */
  def isBlank( input : Option[String]) : Boolean = {
    input match {
      case None     => true
      case Some(s)  => s.trim.isEmpty
    }
  }

  /** Private helper function to handle the actual writing to and from our file
    * In theory will have exception handling so we don't have to implement
    * in all methods exposed to developer
    * @todo exception handling
    * @param str
    */
  private def writeToFile(str: String) : Unit = {
    //if (!isBlank(Option(str))) {
    println("pls write")
      writer.write(str + "\n")
    //}

  }

  /**
    * Writes visiting stamp to logfile
    * @param URL URL that the crawler is visiting
    */
  def visiting(URL: String) : Unit = {
    writeToFile("Visting: " + URL)
  }

  /**
    * Writes warning stamp to logfile
    * @param warning
    */
  def writeWarning(warning : String) : Unit = {
    writeToFile("Warning: " + warning)
  }

  /**
    * Writes successful stamp to logfile
    * @param succ
    */
  def writeSuccess(succ : String) : Unit = {
    writeToFile("Success: " + succ)
  }

  /**
    * Writes failure stamp to logfile
    * @param e error message to write to file
    */
  def writeFailure(e : String) : Unit = {
    writeToFile("Failure: " + e)
  }

}

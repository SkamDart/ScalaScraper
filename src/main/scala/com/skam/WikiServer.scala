package com.skam

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import scala.io.StdIn

/**
  *  Our WikiServer
  *
  */
object WikiServer extends App with WikiRoutes {

  /**
    * Actors are the scala form of threads
    * Internally uses a thread pool
    */
  implicit val system: ActorSystem = ActorSystem("wiki-system")

  /**
    * Executes async actions
    */
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  /**
    * needed for the future flatMap/onComplete in the end
    */
  implicit val executionContext : ExecutionContext = system.dispatcher

  /**
    * SERVER INFO
    */
  val host : String = "localhost"
  val port : Int = 8080

  /**
    * Instantiated in WikiRoutes.scala
    */
  lazy val routes: Route = wikiRoutes

  /**
    * Runs server until enter is returned
    * triggers unbinding from port
    * shuts down once done
    */
  println(s"Server online at http://$host:$port/\nPress RETURN to stop...")
  val bindingFuture = Http().bindAndHandle(routes, host, port)
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())
}


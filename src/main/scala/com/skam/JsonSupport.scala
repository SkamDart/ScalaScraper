package com.skam

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.skam.MovieActor.ActionPerformed

/**
  * Trait that encodes our Movies and MovieActors in JSON format for us
  */
trait JsonSupport extends SprayJsonSupport {
  // import the default encoders for primitive types (Int, String, Lists etc)
  import spray.json.DefaultJsonProtocol._

  /**
  implicit val movieJsonFormat = jsonFormat1(Movie)
  implicit val movieActorJsonFormat = jsonFormat5(MovieActor)
  implicit val moviesJsonFormat = jsonFormat1(Movies)
  implicit val movieActorsJsonFormat = jsonFormat5(MovieActors)
  */
  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
}
package com.skam

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.MethodDirectives.{delete, get, post}
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import scala.util.parsing.json.JSONObject

/**
  * High-level Logic For webserver
  * uses native JsonSupport
  */
trait WikiRoutes extends JsonSupport {

  /**
    * Returns the first Actor object that has name “Bruce Willis”, displays actor attributes and metadata
  * @param name
  */
  def fetchActor(name: String) : Nil.type = {
    Nil
  }

  /**
    * Filters out all actors that don’t have “Bob” in their name (Should allow for similar filtering for any other attribute)
    * @param metadata list of parameters to search for
    * @return
    */
  def fetchActorMeta(metadata: JSONObject): Nil.type = {
    Nil
  }

  /**
    * Updates JSON object associated with an actor
    * @param name name of actor/actress
    * @param metadata what information to update
    * @return
    */
  def updateActorMeta(name: String, metadata: JSONObject): Boolean = {
    true
  }

  /**
    * updates move entry
    * @param name
    * @param metadata
    * @return
    */
  def updateMovieMeta(name: String, metadata: JSONObject): Boolean = {
    true
  }

  /**
    * creates actor entry
    * @param name name of actor to update
    * @param metadata
    * @return
    */
  def createActorMeta(name: String, metadata: JSONObject): Boolean = {
    true
  }

  /**
    * creates movie entry
    * @param name
    * @param metadata
    * @return
    */
  def createMovieMeta(name: String, metadata: JSONObject): Boolean = {
    true
  }

  /**
    * Routes
    */
  val ACTORS = "actors"
  val MOVIES = "movies"

  implicit def system: ActorSystem

  /**
    * Lists all parameters passed to a certain route
    * @param param
    * @return
    */
  def paramString(param: (String, String)): String = s"""${param._1} = '${param._2}'"""

  def actorQuery(param: (String, String)): Unit = {
    val attr: String = param._1
    val foo: String = param._2
  }

  /**
    * Server routes
    */
  lazy val wikiRoutes: Route =
    pathPrefix(ACTORS) {
      get {
        parameterMap { params =>
          complete(s"The params are ${params.map(paramString).mkString(", ")}")
        }
      } ~
      post {
        parameterMap { params =>
          complete(s"The parameters are ${params.map(paramString).mkString(", ")}")
        }
      } ~
      delete {
        parameterMap { params =>
          complete(s"The parameters are ${params.map(paramString).mkString(", ")}")
        }
      } ~
      put {
        parameterMap { params =>
          complete(s"The parameters are ${params.map(paramString).mkString(", ")}")
        }
      }
    } ~
    pathPrefix(MOVIES) {
      get {
        parameterMap { params =>
          complete(s"The parameters are ${params.map(paramString).mkString(", ")}")
        }
      } ~
      post {
        parameterMap { params =>
          complete(s"The parameters are ${params.map(paramString).mkString(", ")}")
        }
      } ~
      delete {
        parameterMap { params =>
          complete(s"The parameters are ${params.map(paramString).mkString(", ")}")
        }
      } ~
      put {
        parameterMap { params =>
          complete(s"The parameters are ${params.map(paramString).mkString(", ")}")
        }
      }
    }
}


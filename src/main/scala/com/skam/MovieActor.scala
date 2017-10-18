package com.skam

import akka.actor.{Actor, ActorLogging, Props}

final case class MovieAct(name: String,
                           json_class: String,
                           age: Int,
                           total_gross: Int,
                           movies: List[String])

final case class MovieActors(actors: Seq[MovieAct])

final case class Movie(json_class: String,
                       name: String,
                       wiki_page: String,
                       box_office: Int,
                       year: Int,
                       actors: List[String])

final case class Movies(movies: Seq[Movie])

object MovieActor {
  final case class ActionPerformed(description: String)
  final case object GetMovies
  final case object GetMovieActors

  /**
    * Have scala actors handle CRUD operations
    * @param movieActor case class movie actor to create
    */
  final case class CreateMovieActor(movieActor: MovieAct)
  final case class CreateMovie(movie: Movie)

  /** Delete
    * @param name movie/actor to delete
   */
  final case class DeleteMovieActor(name: String)
  final case class DeleteMovie(name: String)

  /**
    * Get movie/actor by name
    * @param name
    */
  final case class GetMovie(name: String)
  final case class GetMovieActor(name: String)

  /**
    * Update with case class
    * @param movie
    */
  final case class PutMovie(movie: Movie)
  final case class PutMovieActors(name: MovieAct)

  def props: Props = Props[MovieActor]
}

class MovieActor extends Actor with ActorLogging {
  import MovieActor._

  var movies = Set.empty[Movie]
  var movieActors = Set.empty[MovieAct]

  def receive: Receive = {

    case GetMovies =>
      sender() ! Movies(movies.toSeq)

    case GetMovieActors =>
      sender() ! MovieActors(movieActors.toSeq)

    case CreateMovieActor(movieActor: MovieAct) =>
      movieActors += movieActor
      sender() ! ActionPerformed(s"Movie Actor ${movieActor.name} created")

    case CreateMovie(movie: Movie) =>
      movies += movie
      sender() ! ActionPerformed(s"Movie ${movie.name} created")

    case DeleteMovieActor(name: String) =>
      movieActors.find(_.name == name) foreach { actor => movieActors -= actor }
      sender() ! ActionPerformed(s"MovieActor $name deleted")

    case DeleteMovie(name: String) =>
      movies.find(_.name == name) foreach { movie => movies -= movie}
      sender() ! ActionPerformed(s"Movie $name deleted")

    case GetMovie(name: String) =>
      sender() ! movies.find(_.name == name)

    case GetMovieActor(name: String) =>
      sender() ! movieActors.find(_.name == name)

  }
}

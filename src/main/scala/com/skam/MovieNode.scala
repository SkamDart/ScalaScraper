package com.skam

/**
  * MovieNode class that inherits properties from Graph node
  * Also allows us to pass a single type into our graph
  * @param movieName name of the movie
  * @param wiki_page URL to wikipedia page
  * @param box_office how much did the movie gross
  * @param made when was it made
  * @param acts what actors are in it
  */
class MovieNode(movieName: String,
                wiki_page: String,
                box_office: Int,
                made: Int,
                acts: Vector[String]) extends GraphNode {

  val jsonClass: String = "Movie"
  var name: String = movieName
  var wikiPage: String = wiki_page
  var boxOffice: Int = box_office
  var year: Int = made
  var actors: Vector[String] = actors

}

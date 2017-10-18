package com.skam

class ActorNode(actorName: String,
                actorAge: Int,
                total_gross: Int,
                movieVector: Vector[String]) extends GraphNode {
  val jsonClass: String = "Actor"
  var name: String = actorName
  var age: Int = actorAge
  var totalGross: Int = total_gross
  var movies: Vector[String] = movieVector
}

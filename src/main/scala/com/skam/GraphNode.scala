package com.skam

abstract class GraphNode {
  var name: String
  var jsonClass: String
  /**
    * custom toString
    */
  override def toString: String = s"name = $name"
}

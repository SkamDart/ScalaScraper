package com.skam

/**
  * Case classes are good for modeling immutable data.
  * No new is needed for instantiation of case class since
  * case classes have an apply method that handles object construction
  *
  * @see https://docs.scala-lang.org/tour/case-classes.html for more details on case classes
  * @see https://github.com/vkostyukov/scalacaster/blob/master/src/graph/Graph.scala
  * @param source from where?
  * @param target to where?
  * @param value edge weight
  * @tparam E edge parameterized class type
  * @tparam N node parameterized class type
  */
case class Edge[E, N](source: Graph[E, N], target: Graph[E, N], value: E)

/**
  * Graph constructor with default value as null
  * @param value
  * @tparam E parameterized class type for edges
  * @tparam N parameterized class type for nodes
  */
class Graph[E, N](var value: N = null.asInstanceOf[N]) {

  /**
    * Import only for graph class
    */
  import scala.collection.immutable.Queue

  var inEdges: List[Edge[E, N]] = Nil
  var outEdges: List[Edge[E, N]] = Nil

  /**
    * All successors of this graph.
    */
  def successors : List[Graph[E, N]] = outEdges.map(_.target)

  /**
    * All predecessors of this graph.
    */
  def predecessors : List[Graph[E, N]] = inEdges.map(_.source)

  /**
    * Adds new edge to this graph.
    */
  def connect(from: N, via: E, to: N): (Graph[E, N], Graph[E, N]) = {
    val fromGraph: Graph[E, N] = {
      if (value == null) {
        value = from
        this
      } else {
        hop(from) match {
          case Some(g) => g
          case None => Graph.one(from)
        }
      }
    }

    val toGraph: Graph[E, N] = hop(to) match {
      case Some(g) => g
      case None => Graph.one(to)
    }

    fromGraph.outEdges = Edge(fromGraph, toGraph, via) :: fromGraph.outEdges
    toGraph.inEdges = Edge(fromGraph, toGraph, via) :: toGraph.inEdges

    (fromGraph, toGraph)
  }

  /**
    * Hops to the given node 'n' if its exist in this graph.
    */
  def hop(n: N): Option[Graph[E, N]] = graphsByDepth.find(_.value == n)

  /**
    * Genertates list of Nodes using DFS
    */
  def nodesByDepth: List[N] = graphsByDepth.map(_.value)

  /**
    * Generates list of Nodes using BFS
    */
  def nodesByBreadth: List[N] = graphsByBreadth.map(_.value)

  /**
    * DFS implementation that returns all components of a graph
    * @return
    */
  def graphsByDepth: List[Graph[E, N]] = {
    def loop(g: Graph[E, N], s: Set[Graph[E, N]]): Set[Graph[E, N]] =
      if (!s(g)) {
        val ss = g.successors.foldLeft(s + g)((acc, gg) => loop(gg, acc))
        g.predecessors.foldLeft(ss)((acc, gg) => loop(gg, acc))
      } else s
    loop(this, Set()).toList
  }

  /**
    * BFS implementation that returns all components of a graph
    * @return
    */
  def graphsByBreadth: List[Graph[E, N]] = {
    def loop(q: Queue[Graph[E, N]], s: Set[Graph[E, N]]): Set[Graph[E, N]] =
      if (q.nonEmpty && !s(q.head)) {
        val qq = q.head.successors.foldLeft(q.tail)((acc, gg) => acc :+ gg)
        loop(q.head.predecessors.foldLeft(qq)((acc, gg) => acc :+ gg), s + q.head)
      } else s
    loop(Queue(this), Set()).toList
  }

  /**
    * Converts a JSON file into a graph
    * @param file filename of JSON structure
    * @return graph with nodes that are
    */
  def jsonToGraph(file: String): Graph[E, N] = {

  }

  /**
    * Cardinality of edge set
    * @return number of edges
    */
  def size: Int = graphsByDepth.size

  /**
    * Implements graph equality
    * @param o
    * @return
    */
  override def equals(o: Any): Boolean = o match {
    case g: Graph[_, _] => g.value == value
    case _ => false
  }

  /**
    * custom toString implementation
    * @return
    */
  override def toString: String = "Graph(" + value + ")"
}

/**
  * companion object to expose
  */
object Graph {

  /**
    *
    * @param tuples
    * @tparam E
    * @tparam N
    * @return
    */
  def apply[E, N](tuples: (N, E, N)*): Graph[E, N] = {
    val g: Graph[E, N] = Graph.empty
    for ((from, via, to) <- tuples) {
      g.connect(from, via, to)
    }
    g
  }

  /**
    * graph constructor with single node
    * @param n
    * @tparam E
    * @tparam N
    * @return
    */
  def one[E, N](n: N): Graph[E, N] = new Graph(n)

  /**
    * Empty graph constructor
    * @tparam E
    * @tparam N
    * @return
    */
  def empty[E, N]: Graph[E, N] = new Graph
}

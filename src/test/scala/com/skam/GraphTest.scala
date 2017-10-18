package com.skam

import org.scalatest.FunSuite

import org.scalatest._
import Matchers._

class GraphTest extends FunSuite {

  var G: Graph[Int, String] = Graph(
            ("A", 20, "B")
            , ("B", 30, "C")
            , ("C", 40, "D")
            , ("D", 50, "A")
            , ("A", 100, "C")
            , ("D", 200, "B")
    )

  var a : Graph[Int, String] = G.hop("A").orNull
  var b : Graph[Int, String] = G.hop("B").orNull
  var c : Graph[Int, String] = G.hop("C").orNull
  var d : Graph[Int, String] = G.hop("D").orNull

  test("testPredecessors") {
    G.predecessors should have length 1
    G.predecessors shouldBe List(d)
  }

  test("testSuccessors") {
    G.successors should have length 2
  }

  test("testNodesByBreadth") {
    G.nodesByBreadth should have length 4
    G.nodesByBreadth should equal (List(a.value, c.value, b.value, d.value))
  }

  test("testGraphsByBreadth") {
    G.graphsByBreadth should have length 4
    G.graphsByBreadth should equal (List(a, c, b, d))
  }

  test("testGraphsByDepth") {
    G.graphsByDepth should have length 4
    G.graphsByDepth should equal (List(a, c, d, b))
  }

  test("testNodesByDepth") {
    G.nodesByDepth should have length 4
    G.nodesByDepth should equal (List(a.value, c.value, d.value, b.value))
  }

  test("testHop") {
    G.hop("A") should be (Some(a))
    G.hop("ZZ") should be (None)
  }

  test("testValue") {
    a.value should equal ("A")
  }

  test("testValue_$eq") {

  }

  test("testConnect") {

  }

  test("testOutEdges") {
    G.outEdges should have length 2
  }

  test("testOutEdges_$eq") {

  }

  test("testInEdges") {
    G.inEdges should have length 1
  }

  test("testInEdges_$eq") {

  }

  test("testSize") {
    G.size should be (4)
  }

  test("testEquals") {
    a.equals(a) should be (true)
    a.equals(b) should be (false)
  }

  test("testToString") {

  }

  test("testOne") {
    val one : Graph[Int, Int] = Graph.one[Int, Int](1)
    one.size should be (1)
  }

  test("testEmpty") {
    val e : Graph[Int, Int] = Graph.empty
    e.size should be (0)
  }

}

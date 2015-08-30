(ns poker-hands.core-test
  (:use midje.sweet)
  (:use [poker-hands.core]))

(facts
  "A poker hand scorer"

  (fact
    "scores a High card hand"

    (score (hand "2H 3D 5S 9C 2D")) => "high card: 9"
    (score (hand "2H 8D 5S 9C KD")) => "high card: K")

  (fact
    "scores a Flush hand"

    (score (hand "2S 8S AS QS 3S")) => "flush"))

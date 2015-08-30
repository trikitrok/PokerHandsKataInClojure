(ns poker-hands.core-test
  (:use midje.sweet)
  (:use [poker-hands.core]))

(facts
  "A poker hand"

  (fact
    "it scores a High card hand"

    (score (hand "2H 3D 5S 9C KD")) => "high card: 9"))

(ns poker-hands.core-test
  (:use midje.sweet)
  (:use [poker-hands.core]))

(facts
  "A poker hand"

  (fact
    "can be a High card hand"
    (hand-type "2H 3D 5S 9C 4D") => {:hand-type :high-card :highest-card "9"}
    (hand-type "2H 8D 5S 9C KD") => {:hand-type :high-card :highest-card "K"})

  (fact
    "can be a Flush hand"
    (hand-type "2S 8S AS QS 3S") => {:hand-type :flush :highest-card "A"})

  (fact
    "scores a Pair hand"
    (score (hand "2S 8D 5C QS 2D")) => "pair of 2")

  (fact
    "scores a Two Pair hand"
    (score (hand "2S 5D 5C QS 2D")) => "two pairs of 2 and 5"))

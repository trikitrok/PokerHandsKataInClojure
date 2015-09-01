(ns poker-hands.hands-test
  (:use midje.sweet)
  (:use [poker-hands.hands])
  (:require [poker-hands.examples :as examples]))

(facts
  "A poker hand"

  (fact
    "can be a High card hand"
    (hand "2H 3D 5S 9C 4D") => examples/high-card-with-9
    (hand "2H 8D 5S 9C KD") => examples/high-card-with-K)

  (fact
    "can be a Flush hand"
    (hand "KC JC 9C 3C 2C") => examples/flush-of-K-J-9-3-2)

  (fact
    "can be a Pair hand"
    (hand "2S 8D 5C QS 2D") => examples/pair-of-2)

  (fact
    "can be a Two Pair hand"
    (hand "2S 5D 5C QS 2D") => examples/two-pairs-of-5-2)

  (fact
    "can be a Three of a Kind hand"
    (hand "5S 5D 5C QS 2D") => examples/triplet-of-5)

  (fact
    "can be a Straight hand"
    (hand "QS JD 10H 9S 8C") => examples/straight-with-Q
    (hand "AS KC QH JH 10D") => examples/straight-with-A
    (let [wheeel-description "5C 4H 3D 2C AS"]
      (hand wheeel-description) => examples/straight-wheel))

  (fact
    "can be a Straight Flush"
    (hand "QS JS 10S 9S 8S") => examples/straight-flush-with-Q)

  (fact
    "can be a Full House"
    (hand "5S 2S 5D 2D 5C") => examples/full-house-of-5-2)

  (fact
    "can be a Four of a Kind rank"
    (hand "5S 5D 5C QS 5H") => examples/four-kind-of-5))

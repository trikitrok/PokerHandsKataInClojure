(ns poker-hands.hands-test
  (:use midje.sweet)
  (:use [poker-hands.hands])
  (:require [poker-hands.examples.types :as examples]))

(facts
  "A poker hand"

  (fact
    "can be a High card hand"
    (create-hand "7H 9D 5S 3C 2D") => examples/high-card-with-9-7-5-3-2
    (create-hand "5H 7D JS 9C KD") => examples/high-card-with-K-J-9-7-5)

  (fact
    "can be a Flush hand"
    (create-hand "KC JC 9C 3C 2C") => examples/flush-of-K-J-9-3-2)

  (fact
    "can be a Pair hand"
    (create-hand "2S 8D 5C QS 2D") => examples/pair-of-2)

  (fact
    "can be a Two Pair hand"
    (create-hand "2S 5D 5C QS 2D") => examples/two-pairs-of-5-2)

  (fact
    "can be a Three of a Kind hand"
    (create-hand "5S 5D 5C QS 2D") => examples/triplet-of-5)

  (fact
    "can be a Straight hand"
    (create-hand "QS JD 10H 9S 8C") => examples/straight-with-Q
    (create-hand "AS KC QH JH 10D") => examples/straight-with-A
    (let [wheeel-description "5C 4H 3D 2C AS"]
      (create-hand wheeel-description) => examples/straight-wheel))

  (fact
    "can be a Straight Flush hand"
    (create-hand "QS JS 10S 9S 8S") => examples/straight-flush-with-Q)

  (fact
    "can be a Full House hand"
    (create-hand "5S 2S 5D 2D 5C") => examples/full-house-of-5-2)

  (fact
    "can be a Four of a Kind hand"
    (create-hand "5S 5D 5C QS 5H") => examples/four-kind-of-5))

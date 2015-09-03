(ns poker-hands.core-test
  (:use poker-hands.core)
  (:use midje.sweet))

(facts
  "About comparing poker hands"

  (compare-hands
    "Black: 5H 7D JS 9C KD"
    "White: 5S 5D 10H 10S 8C") => "White wins. - with Two Pairs: 10 and 5"

  (compare-hands
    "White: 5H 7D JS 9C KD"
    "Black: KC JC 9C 3C 2C") => "Black wins. - with a Flush of K J 9 3 2")
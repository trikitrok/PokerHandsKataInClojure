(ns poker-hands.hands-test
  (:use midje.sweet)
  (:use [poker-hands.hands]))

(facts
  "A poker hand"

  (fact
    "can be a High card hand"
    (hand "2H 3D 5S 9C 4D") => {:type :high-card :highest-card "9"}
    (hand "2H 8D 5S 9C KD") => {:type :high-card :highest-card "K"})

  (fact
    "can be a Flush hand"
    (hand "2S 8S AS QS 3S") => {:type :flush :highest-card "A"})

  (fact
    "can be a Pair hand"
    (hand "2S 8D 5C QS 2D") => {:type :pair :pair-card ["2"] :no-pair-cards ["Q" "8" "5"]})

  (fact
    "can be a Two Pair hand"
    (hand "2S 5D 5C QS 2D") => {:type :two-pairs :pair-cards ["5" "2"] :no-pair-cards ["Q"]})

  (fact
    "can be a Three of a Kind hand"
    (hand "5S 5D 5C QS 2D") => {:type :triplet :triplet-card ["5"] :no-triplet-cards ["Q" "2"]})

  (fact
    "can be a Straight hand"
    (hand "QS JD 10H 9S 8C") => {:type :straight :highest-card "Q"}
    (hand "AS KC QH JH 10D") => {:type :straight :highest-card "A"}
    (let [wheeel-description "5C 4H 3D 2C AS"]
      (hand wheeel-description) => {:type :straight :highest-card "5"}))

  (fact
    "can be a Straight Flush"
    (hand "QS JS 10S 9S 8S") => {:type :straight-flush :highest-card "Q"})

  (fact
    "can be a Full House"
    (hand "5S 2S 5D 2D 5C") => {:type :full-house :triplet-card ["5"] :pair-card ["2"]})

  (fact
    "can be a Four of a Kind rank"
    (hand "5S 5D 5C QS 5H") => {:type :four-kind :four-kind-card ["5"] :no-four-card ["Q"]}))


(ns poker-hands.examples
  (:require [poker-hands.hands :as hands]))

(defn black-player-with [hand]
  (assoc hand :player :black))

(defn white-player-with [hand]
  (assoc hand :player :white))

(def straight-flush-with-Q (hands/map->StraightFlush {:highest-card "Q"}))
(def straight-flush-with-K (hands/map->StraightFlush {:highest-card "K"}))
(def straight-flush-with-A (hands/map->StraightFlush {:highest-card "A"}))
(def straight-flush-with-6 (hands/map->StraightFlush {:highest-card "6"}))

(def four-kind-of-5 (hands/map->FourKind {:four-kind-card ["5"] :no-four-card ["Q"]}))
(def four-kind-of-6 (hands/map->FourKind {:four-kind-card ["6"] :no-four-card ["10"]}))
(def four-kind-of-4-with-kicking-Q (hands/map->FourKind {:four-kind-card ["4"] :no-four-card ["Q"]}))
(def four-kind-of-4-with-kicking-K (hands/map->FourKind {:four-kind-card ["4"] :no-four-card ["K"]}))

(def high-card-with-K {:type :high-card :highest-card "K"})
(def high-card-with-9 {:type :high-card :highest-card "9"})

(def pair-of-2 {:type :pair :pair-card ["2"] :no-pair-cards ["Q" "8" "5"]})

(def two-pairs-of-5-2 {:type :two-pairs :pair-cards ["5" "2"] :no-pair-cards ["Q"]})

(def triplet-of-5 {:type :triplet :triplet-card ["5"] :no-triplet-cards ["Q" "2"]})

(def straight-with-Q {:type :straight :highest-card "Q"})
(def straight-with-A {:type :straight :highest-card "A"})
(def straight-wheel {:type :straight :highest-card "5"})

(def flush-with-A {:type :flush :highest-card "A"})

(def full-house-of-5-2 (hands/map->FullHouse {:type :full-house :triplet-card ["5"] :pair-card ["2"]}))
(def full-house-of-9-4  (hands/map->FullHouse {:type :full-house :triplet-card ["9"] :pair-card ["4"]}))
(def full-house-of-8-A (hands/map->FullHouse {:type :full-house :triplet-card ["8"] :pair-card ["A"]}))
(def full-house-of-8-K (hands/map->FullHouse {:type :full-house :triplet-card ["8"] :pair-card ["K"]}))
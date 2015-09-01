(ns poker-hands.examples
  (:require [poker-hands.hands :as hands]))

(defn black-player-with [hand]
  (assoc hand :player :black))

(defn white-player-with [hand]
  (assoc hand :player :white))

(def straight-flush-with-Q (hands/map->StraightFlush {:cards ["Q"]}))
(def straight-flush-with-K (hands/map->StraightFlush {:cards ["K"]}))
(def straight-flush-with-A (hands/map->StraightFlush {:cards ["A"]}))
(def straight-flush-with-6 (hands/map->StraightFlush {:cards ["6"]}))

(def four-kind-of-5 (hands/map->FourKind {:cards ["5" "Q"]}))
(def four-kind-of-6 (hands/map->FourKind {:cards ["6" "10"]}))
(def four-kind-of-4-with-kicking-Q (hands/map->FourKind {:cards ["4" "Q"]}))
(def four-kind-of-4-with-kicking-K (hands/map->FourKind {:cards ["4" "K"]}))

(def high-card-with-K-J-9-7-5 (hands/map->HighCard {:cards ["K" "J" "9" "7" "5"]}))
(def high-card-with-9-7-5-3-2 (hands/map->HighCard {:cards ["9" "7" "5" "3" "2"]}))
(def high-card-with-A-J-9-5-3 (hands/map->HighCard {:cards ["A" "J" "9" "5" "3"]}))
(def high-card-with-A-10-9-6-4 (hands/map->HighCard {:cards ["A" "10" "9" "6" "4"]}))

(def pair-of-2 (hands/map->Pair {:cards ["2" "Q" "8" "5"]}))
(def pair-of-6-6-4-3-2 (hands/map->Pair {:cards ["6" "4" "3" "2"]}))
(def pair-of-5-5-A-K-Q (hands/map->Pair {:cards ["5" "A" "K" "Q"]}))
(def pair-of-5-5-10-K-Q (hands/map->Pair {:cards ["5" "10" "K" "Q"]}))
(def pair-of-5-5-10-J-Q (hands/map->Pair {:cards ["5" "10" "J" "Q"]}))
(def pair-of-5-5-10-J-J (hands/map->Pair {:cards ["5" "10" "J" "J"]}))


(def two-pairs-of-5-2 (hands/map->TwoPairs {:cards ["5" "2" "Q"]}))
(def two-pairs-of-J-J-2-2-4 (hands/map->TwoPairs {:cards ["J" "2" "4"]}))
(def two-pairs-of-10-10-9-9-8 (hands/map->TwoPairs {:cards ["10" "9" "8"]}))
(def two-pairs-of-10-10-5-5-K (hands/map->TwoPairs {:cards ["10" "5" "K"]}))
(def two-pairs-of-10-10-5-5-A (hands/map->TwoPairs {:cards ["10" "5" "A"]}))


(def triplet-of-5 (hands/map->Triplet {:cards ["5" "Q" "2"]}))
(def triplet-of-5-5-5-3-2 (hands/map->Triplet {:cards ["5" "3" "2"]}))
(def triplet-of-4-4-4-K-5 (hands/map->Triplet {:cards ["4" "K" "5"]}))
(def triplet-of-4-4-4-Q-9 (hands/map->Triplet {:cards ["4" "Q" "9"]}))
(def triplet-of-4-4-4-Q-8 (hands/map->Triplet {:cards ["4" "Q" "8"]}))

(def straight-with-Q (hands/map->Straight {:cards ["Q"]}))
(def straight-with-A (hands/map->Straight {:cards ["A"]}))
(def straight-wheel (hands/map->Straight {:cards ["5"]}))

(def flush-of-K-J-9-3-2 (hands/map->Flush {:cards ["K" "J" "9" "3" "2"]}))
(def flush-of-K-J-7-6-5 (hands/map->Flush {:cards ["K" "J" "7" "6" "5"]}))

(def full-house-of-5-2 (hands/map->FullHouse {:cards ["5" "2"]}))
(def full-house-of-9-4 (hands/map->FullHouse {:cards ["9" "4"]}))
(def full-house-of-8-A (hands/map->FullHouse {:cards ["8" "A"]}))
(def full-house-of-8-K (hands/map->FullHouse {:cards ["8" "K"]}))
(ns poker-hands.types-examples
  (:require [poker-hands.hand-types :as hand-types]))

(defn black-player-with [hand]
  (assoc hand :player :black))

(defn white-player-with [hand]
  (assoc hand :player :white))

(def straight-flush-with-Q (hand-types/map->StraightFlush {:faces ["Q"]}))
(def straight-flush-with-K (hand-types/map->StraightFlush {:faces ["K"]}))
(def straight-flush-with-A (hand-types/map->StraightFlush {:faces ["A"]}))
(def straight-flush-with-6 (hand-types/map->StraightFlush {:faces ["6"]}))

(def four-kind-of-5 (hand-types/map->FourKind {:faces ["5" "Q"]}))
(def four-kind-of-6 (hand-types/map->FourKind {:faces ["6" "10"]}))
(def four-kind-of-4-with-kicking-Q (hand-types/map->FourKind {:faces ["4" "Q"]}))
(def four-kind-of-4-with-kicking-K (hand-types/map->FourKind {:faces ["4" "K"]}))

(def high-card-with-K-J-9-7-5 (hand-types/map->HighCard {:faces ["K" "J" "9" "7" "5"]}))
(def high-card-with-9-7-5-3-2 (hand-types/map->HighCard {:faces ["9" "7" "5" "3" "2"]}))
(def high-card-with-A-J-9-5-3 (hand-types/map->HighCard {:faces ["A" "J" "9" "5" "3"]}))
(def high-card-with-A-10-9-6-4 (hand-types/map->HighCard {:faces ["A" "10" "9" "6" "4"]}))

(def pair-of-2 (hand-types/map->Pair {:faces ["2" "Q" "8" "5"]}))
(def pair-of-6-6-4-3-2 (hand-types/map->Pair {:faces ["6" "4" "3" "2"]}))
(def pair-of-5-5-A-K-Q (hand-types/map->Pair {:faces ["5" "A" "K" "Q"]}))
(def pair-of-5-5-10-K-Q (hand-types/map->Pair {:faces ["5" "10" "K" "Q"]}))
(def pair-of-5-5-10-J-Q (hand-types/map->Pair {:faces ["5" "10" "J" "Q"]}))
(def pair-of-5-5-10-J-J (hand-types/map->Pair {:faces ["5" "10" "J" "J"]}))

(def two-pairs-of-5-2 (hand-types/map->TwoPairs {:faces ["5" "2" "Q"]}))
(def two-pairs-of-J-J-2-2-4 (hand-types/map->TwoPairs {:faces ["J" "2" "4"]}))
(def two-pairs-of-10-10-9-9-8 (hand-types/map->TwoPairs {:faces ["10" "9" "8"]}))
(def two-pairs-of-10-10-5-5-K (hand-types/map->TwoPairs {:faces ["10" "5" "K"]}))
(def two-pairs-of-10-10-5-5-A (hand-types/map->TwoPairs {:faces ["10" "5" "A"]}))

(def triplet-of-5 (hand-types/map->Triplet {:faces ["5" "Q" "2"]}))
(def triplet-of-5-5-5-3-2 (hand-types/map->Triplet {:faces ["5" "3" "2"]}))
(def triplet-of-4-4-4-K-5 (hand-types/map->Triplet {:faces ["4" "K" "5"]}))
(def triplet-of-4-4-4-Q-9 (hand-types/map->Triplet {:faces ["4" "Q" "9"]}))
(def triplet-of-4-4-4-Q-8 (hand-types/map->Triplet {:faces ["4" "Q" "8"]}))

(def straight-with-Q (hand-types/map->Straight {:faces ["Q"]}))
(def straight-with-A (hand-types/map->Straight {:faces ["A"]}))
(def straight-wheel (hand-types/map->Straight {:faces ["5"]}))

(def flush-of-K-J-9-3-2 (hand-types/map->Flush {:faces ["K" "J" "9" "3" "2"]}))
(def flush-of-K-J-7-6-5 (hand-types/map->Flush {:faces ["K" "J" "7" "6" "5"]}))

(def full-house-of-5-2 (hand-types/map->FullHouse {:faces ["5" "2"]}))
(def full-house-of-9-4 (hand-types/map->FullHouse {:faces ["9" "4"]}))
(def full-house-of-8-A (hand-types/map->FullHouse {:faces ["8" "A"]}))
(def full-house-of-8-K (hand-types/map->FullHouse {:faces ["8" "K"]}))

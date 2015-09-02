(ns poker-hands.types-examples
  (:require [poker-hands.creation :as creation]))

(defn black-player-with [hand]
  (assoc hand :player :black))

(defn white-player-with [hand]
  (assoc hand :player :white))

(def straight-flush-with-Q (creation/map->StraightFlush {:faces ["Q"]}))
(def straight-flush-with-K (creation/map->StraightFlush {:faces ["K"]}))
(def straight-flush-with-A (creation/map->StraightFlush {:faces ["A"]}))
(def straight-flush-with-6 (creation/map->StraightFlush {:faces ["6"]}))

(def four-kind-of-5 (creation/map->FourKind {:faces ["5" "Q"]}))
(def four-kind-of-6 (creation/map->FourKind {:faces ["6" "10"]}))
(def four-kind-of-4-with-kicking-Q (creation/map->FourKind {:faces ["4" "Q"]}))
(def four-kind-of-4-with-kicking-K (creation/map->FourKind {:faces ["4" "K"]}))

(def high-card-with-K-J-9-7-5 (creation/map->HighCard {:faces ["K" "J" "9" "7" "5"]}))
(def high-card-with-9-7-5-3-2 (creation/map->HighCard {:faces ["9" "7" "5" "3" "2"]}))
(def high-card-with-A-J-9-5-3 (creation/map->HighCard {:faces ["A" "J" "9" "5" "3"]}))
(def high-card-with-A-10-9-6-4 (creation/map->HighCard {:faces ["A" "10" "9" "6" "4"]}))

(def pair-of-2 (creation/map->Pair {:faces ["2" "Q" "8" "5"]}))
(def pair-of-6-6-4-3-2 (creation/map->Pair {:faces ["6" "4" "3" "2"]}))
(def pair-of-5-5-A-K-Q (creation/map->Pair {:faces ["5" "A" "K" "Q"]}))
(def pair-of-5-5-10-K-Q (creation/map->Pair {:faces ["5" "10" "K" "Q"]}))
(def pair-of-5-5-10-J-Q (creation/map->Pair {:faces ["5" "10" "J" "Q"]}))
(def pair-of-5-5-10-J-J (creation/map->Pair {:faces ["5" "10" "J" "J"]}))

(def two-pairs-of-5-2 (creation/map->TwoPairs {:faces ["5" "2" "Q"]}))
(def two-pairs-of-J-J-2-2-4 (creation/map->TwoPairs {:faces ["J" "2" "4"]}))
(def two-pairs-of-10-10-9-9-8 (creation/map->TwoPairs {:faces ["10" "9" "8"]}))
(def two-pairs-of-10-10-5-5-K (creation/map->TwoPairs {:faces ["10" "5" "K"]}))
(def two-pairs-of-10-10-5-5-A (creation/map->TwoPairs {:faces ["10" "5" "A"]}))

(def triplet-of-5 (creation/map->Triplet {:faces ["5" "Q" "2"]}))
(def triplet-of-5-5-5-3-2 (creation/map->Triplet {:faces ["5" "3" "2"]}))
(def triplet-of-4-4-4-K-5 (creation/map->Triplet {:faces ["4" "K" "5"]}))
(def triplet-of-4-4-4-Q-9 (creation/map->Triplet {:faces ["4" "Q" "9"]}))
(def triplet-of-4-4-4-Q-8 (creation/map->Triplet {:faces ["4" "Q" "8"]}))

(def straight-with-Q (creation/map->Straight {:faces ["Q"]}))
(def straight-with-A (creation/map->Straight {:faces ["A"]}))
(def straight-wheel (creation/map->Straight {:faces ["5"]}))

(def flush-of-K-J-9-3-2 (creation/map->Flush {:faces ["K" "J" "9" "3" "2"]}))
(def flush-of-K-J-7-6-5 (creation/map->Flush {:faces ["K" "J" "7" "6" "5"]}))

(def full-house-of-5-2 (creation/map->FullHouse {:faces ["5" "2"]}))
(def full-house-of-9-4 (creation/map->FullHouse {:faces ["9" "4"]}))
(def full-house-of-8-A (creation/map->FullHouse {:faces ["8" "A"]}))
(def full-house-of-8-K (creation/map->FullHouse {:faces ["8" "K"]}))
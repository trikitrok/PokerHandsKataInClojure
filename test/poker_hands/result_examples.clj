(ns poker-hands.result-examples
  (:require [poker-hands.types-examples
             :as examples
             :refer [white-player-with black-player-with]]))

(def tie :tie)

; Straight Flush
(def white-player-with-straight-flush-with-Q-wins
  {:winning (white-player-with examples/straight-flush-with-Q)})

(def black-player-with-straight-flush-with-A-wins
  {:winning (black-player-with examples/straight-flush-with-A)})

; Four of a Kind
(def black-player-with-four-kind-of-5-wins
  {:winning (black-player-with examples/four-kind-of-5)})

(def white-player-with-four-kind-of-6-wins
  {:winning (white-player-with examples/four-kind-of-6)})

(def black-player-with-four-kind-of-4-with-kicking-K-wins
  {:winning (black-player-with examples/four-kind-of-4-with-kicking-K)})

; Full House
(def white-player-with-full-house-of-5-2-wins
  {:winning (white-player-with examples/full-house-of-5-2)})

(def white-player-with-full-house-of-9-4-wins
  {:winning (white-player-with examples/full-house-of-9-4)})

(def black-player-with-full-house-of-8-A-wins
  {:winning (black-player-with examples/full-house-of-8-A)})

; Flush
(def black-player-with-flush-of-K-J-9-3-2-wins
  {:winning (black-player-with examples/flush-of-K-J-9-3-2)})

(def white-player-with-flush-of-K-J-9-3-2-wins
  {:winning (white-player-with examples/flush-of-K-J-9-3-2)})

; Straight
(def white-player-with-straight-with-A-wins
  {:winning (white-player-with examples/straight-with-A)})

(def black-player-with-straight-with-Q-wins
  {:winning (black-player-with examples/straight-with-Q)})

; Three of a Kind
(def white-player-with-triplet-of-5-wins
  {:winning (white-player-with examples/triplet-of-5)})

(def black-player-with-triplet-of-5-5-5-3-2-wins
  {:winning (black-player-with examples/triplet-of-5-5-5-3-2)})

(def black-player-with-triplet-of-4-4-4-K-5-wins
  {:winning (black-player-with examples/triplet-of-4-4-4-K-5)})

(def black-player-with-triplet-of-4-4-4-Q-9-wins
  {:winning (black-player-with examples/triplet-of-4-4-4-Q-9)})

; Two Pairs
(def white-player-with-two-pairs-of-5-2-wins
  {:winning (white-player-with examples/two-pairs-of-5-2)})

(def black-player-with-two-pairs-of-J-J-2-2-4-wins
  {:winning (black-player-with examples/two-pairs-of-J-J-2-2-4)})

(def white-player-with-two-pairs-of-10-10-9-9-8-wins
  {:winning (white-player-with examples/two-pairs-of-10-10-9-9-8)})

(def black-player-with-two-pairs-of-10-10-5-5-A-wins
  {:winning (black-player-with examples/two-pairs-of-10-10-5-5-A)})

; Pair
(def black-player-with-pair-of-5-5-10-J-Q-wins
  {:winning (black-player-with examples/pair-of-5-5-10-J-Q)})

(def white-player-with-pair-of-5-5-10-K-Q-wins
  {:winning (white-player-with examples/pair-of-5-5-10-K-Q)})

(def black-player-with-pair-of-5-5-A-K-Q-wins
  {:winning (black-player-with examples/pair-of-5-5-A-K-Q)})

(def black-player-with-pair-of-6-6-4-3-2-wins
  {:winning (black-player-with examples/pair-of-6-6-4-3-2)})

(def black-player-with-pair-of-2-wins
  {:winning (black-player-with examples/pair-of-2)})

; High Card
(def black-player-with-high-card-with-K-J-9-7-5-wins
  {:winning (black-player-with examples/high-card-with-K-J-9-7-5)})

(def black-player-with-high-card-with-A-J-9-5-3-wins
  {:winning (black-player-with examples/high-card-with-A-J-9-5-3)})

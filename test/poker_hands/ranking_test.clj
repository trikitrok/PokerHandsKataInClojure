(ns poker-hands.ranking-test
  (:use midje.sweet)
  (:use [poker-hands.ranking])
  (:require [poker-hands.result-examples :as results])
  (:require [poker-hands.types-examples
             :as examples
             :refer [white-player-with black-player-with]]))

(facts
  "About the hands ranking system"

  (facts
    "Straight Flush hand"
    (facts
      "beats any other kind of hand"
      (let [white-player-with-straight-flush (white-player-with examples/straight-flush-with-Q)]

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/high-card-with-K-J-9-7-5))
        => results/white-player-with-straight-flush-with-Q-wins

        (compare-hands
          (black-player-with examples/pair-of-2)
          white-player-with-straight-flush)
        => results/white-player-with-straight-flush-with-Q-wins

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/two-pairs-of-5-2))
        => results/white-player-with-straight-flush-with-Q-wins

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/triplet-of-5))
        => results/white-player-with-straight-flush-with-Q-wins

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/straight-with-Q))
        => results/white-player-with-straight-flush-with-Q-wins

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/flush-of-K-J-9-3-2))
        => results/white-player-with-straight-flush-with-Q-wins

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/full-house-of-5-2))
        => results/white-player-with-straight-flush-with-Q-wins

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/four-kind-of-5))
        => results/white-player-with-straight-flush-with-Q-wins

        ))

    (facts
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/straight-flush-with-K)
        (black-player-with examples/straight-flush-with-A))
      => results/black-player-with-straight-flush-with-A-wins

      (compare-hands
        (white-player-with examples/straight-flush-with-6)
        (black-player-with examples/straight-flush-with-6)) => results/tie))

  (facts
    "Four of a kind hand"
    (fact
      "beats any other kind of hand except a Straight Flush"
      (let [black-player-with-four-kind-of-5 (black-player-with examples/four-kind-of-5)]

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/full-house-of-5-2))
        => results/black-player-with-four-kind-of-5-wins

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/flush-of-K-J-9-3-2))
        => results/black-player-with-four-kind-of-5-wins

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/straight-with-Q))
        => results/black-player-with-four-kind-of-5-wins

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/triplet-of-5))
        => results/black-player-with-four-kind-of-5-wins

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/two-pairs-of-5-2))
        => results/black-player-with-four-kind-of-5-wins

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/pair-of-2))
        => results/black-player-with-four-kind-of-5-wins

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/high-card-with-K-J-9-7-5))
        => results/black-player-with-four-kind-of-5-wins)

      (fact
        "beats or ties with one of its kind"
        (compare-hands
          (white-player-with examples/four-kind-of-6)
          (black-player-with examples/four-kind-of-5))
        => results/white-player-with-four-kind-of-6-wins

        (compare-hands
          (white-player-with examples/four-kind-of-4-with-kicking-Q)
          (black-player-with examples/four-kind-of-4-with-kicking-K))
        => results/black-player-with-four-kind-of-4-with-kicking-K-wins

        (compare-hands
          (white-player-with examples/four-kind-of-6)
          (black-player-with examples/four-kind-of-6)) => results/tie)))

  (facts
    "Full house hand"
    (fact
      "beats any other kind of hand except Straigh Flush and Four of a Kind"
      (let [white-player-with-full-house-of-5-2 (white-player-with examples/full-house-of-5-2)]

        (compare-hands
          (black-player-with examples/flush-of-K-J-9-3-2)
          white-player-with-full-house-of-5-2)
        => results/white-player-with-full-house-of-5-2-wins

        (compare-hands
          (black-player-with examples/straight-with-Q)
          white-player-with-full-house-of-5-2)
        => results/white-player-with-full-house-of-5-2-wins

        (compare-hands
          (black-player-with examples/triplet-of-5)
          white-player-with-full-house-of-5-2)
        => results/white-player-with-full-house-of-5-2-wins

        (compare-hands
          (black-player-with examples/two-pairs-of-5-2)
          white-player-with-full-house-of-5-2)
        => results/white-player-with-full-house-of-5-2-wins

        (compare-hands
          (black-player-with examples/pair-of-2)
          white-player-with-full-house-of-5-2)
        => results/white-player-with-full-house-of-5-2-wins

        (compare-hands
          (black-player-with examples/high-card-with-K-J-9-7-5)
          white-player-with-full-house-of-5-2)
        => results/white-player-with-full-house-of-5-2-wins))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/full-house-of-9-4)
        (black-player-with examples/full-house-of-8-A))
      => results/white-player-with-full-house-of-9-4-wins

      (compare-hands
        (white-player-with examples/full-house-of-8-K)
        (black-player-with examples/full-house-of-8-A))
      => results/black-player-with-full-house-of-8-A-wins

      (compare-hands
        (white-player-with examples/full-house-of-8-K)
        (black-player-with examples/full-house-of-8-K)) => results/tie))

  (facts
    "Flush hand"
    (fact
      "beats any other kind of hand except Straigh Flush, Four of a Kind and FullHouse"
      (let [black-player-with-flush-of-K-J-9-3-2 (black-player-with examples/flush-of-K-J-9-3-2)]

        (compare-hands
          black-player-with-flush-of-K-J-9-3-2
          (white-player-with examples/straight-with-Q))
        => results/black-player-with-flush-of-K-J-9-3-2-wins

        (compare-hands
          black-player-with-flush-of-K-J-9-3-2
          (white-player-with examples/triplet-of-5))
        => results/black-player-with-flush-of-K-J-9-3-2-wins

        (compare-hands
          black-player-with-flush-of-K-J-9-3-2
          (white-player-with examples/two-pairs-of-5-2))
        => results/black-player-with-flush-of-K-J-9-3-2-wins

        (compare-hands
          black-player-with-flush-of-K-J-9-3-2
          (white-player-with examples/pair-of-2))
        => results/black-player-with-flush-of-K-J-9-3-2-wins

        (compare-hands
          black-player-with-flush-of-K-J-9-3-2
          (white-player-with examples/high-card-with-K-J-9-7-5))
        => results/black-player-with-flush-of-K-J-9-3-2-wins))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/flush-of-K-J-9-3-2)
        (black-player-with examples/flush-of-K-J-7-6-5))
      => results/white-player-with-flush-of-K-J-9-3-2-wins

      (compare-hands
        (white-player-with examples/flush-of-K-J-9-3-2)
        (black-player-with examples/flush-of-K-J-9-3-2)) => results/tie))

  (facts
    "Straight hand"
    (fact
      "beats any other kind of hand except Straigh Flush, Four of a Kind, FullHouse and Flush"
      (let [white-player-with-straight-with-A (white-player-with examples/straight-with-A)]
        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/triplet-of-5))
        => results/white-player-with-straight-with-A-wins

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/two-pairs-of-5-2))
        => results/white-player-with-straight-with-A-wins

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/pair-of-2))
        => results/white-player-with-straight-with-A-wins

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/high-card-with-K-J-9-7-5))
        => results/white-player-with-straight-with-A-wins))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (black-player-with examples/straight-with-Q)
        (white-player-with examples/straight-wheel))
      => results/black-player-with-straight-with-Q-wins

      (compare-hands
        (black-player-with examples/straight-with-Q)
        (white-player-with examples/straight-with-Q)) => results/tie))

  (facts
    "Three of a kind"

    (fact
      "only beats Two Pairs, Pair and High Card hands"
      (let [white-player-with-triplet-of-5 (white-player-with examples/triplet-of-5)]

        (compare-hands
          white-player-with-triplet-of-5
          (black-player-with examples/two-pairs-of-5-2))
        => results/white-player-with-triplet-of-5-wins

        (compare-hands
          white-player-with-triplet-of-5
          (black-player-with examples/pair-of-2))
        => results/white-player-with-triplet-of-5-wins

        (compare-hands
          white-player-with-triplet-of-5
          (black-player-with examples/high-card-with-K-J-9-7-5))
        => results/white-player-with-triplet-of-5-wins))

    (fact
      "beats or ties with one of its kind"

      (compare-hands
        (black-player-with examples/triplet-of-5-5-5-3-2)
        (white-player-with examples/triplet-of-4-4-4-K-5))
      => results/black-player-with-triplet-of-5-5-5-3-2-wins

      (compare-hands
        (black-player-with examples/triplet-of-4-4-4-K-5)
        (white-player-with examples/triplet-of-4-4-4-Q-9))
      => results/black-player-with-triplet-of-4-4-4-K-5-wins

      (compare-hands
        (black-player-with examples/triplet-of-4-4-4-Q-9)
        (white-player-with examples/triplet-of-4-4-4-Q-8))
      => results/black-player-with-triplet-of-4-4-4-Q-9-wins))

  (facts
    "Two pairs hand"

    (fact
      "only beats Pair and High Card hands"
      (let [white-player-with-two-pairs-of-5-2 (white-player-with examples/two-pairs-of-5-2)]

        (compare-hands
          white-player-with-two-pairs-of-5-2
          (black-player-with examples/pair-of-2)) 
        => results/white-player-with-two-pairs-of-5-2-wins))

    (fact
      "beats or ties with one of its kind"

      (compare-hands
        (black-player-with examples/two-pairs-of-J-J-2-2-4)
        (white-player-with examples/two-pairs-of-10-10-9-9-8))
      => results/black-player-with-two-pairs-of-J-J-2-2-4-wins

      (compare-hands
        (black-player-with examples/two-pairs-of-10-10-5-5-K)
        (white-player-with examples/two-pairs-of-10-10-9-9-8))
      => results/white-player-with-two-pairs-of-10-10-9-9-8-wins

      (compare-hands
        (black-player-with examples/two-pairs-of-10-10-5-5-A)
        (white-player-with examples/two-pairs-of-10-10-5-5-K))
      => results/black-player-with-two-pairs-of-10-10-5-5-A-wins

      (compare-hands
        (black-player-with examples/two-pairs-of-5-2)
        (white-player-with examples/two-pairs-of-5-2)) => results/tie))

  (facts
    "Pair hand"
    (fact
      "only beats High Card hands"
      (compare-hands
        (black-player-with examples/pair-of-2)
        (white-player-with examples/high-card-with-K-J-9-7-5))
      => results/black-player-with-pair-of-2-wins)

    (fact
      "beats or ties with one of its kind"

      (compare-hands
        (black-player-with examples/pair-of-6-6-4-3-2)
        (white-player-with examples/pair-of-5-5-A-K-Q))
      => results/black-player-with-pair-of-6-6-4-3-2-wins

      (compare-hands
        (black-player-with examples/pair-of-5-5-A-K-Q)
        (white-player-with examples/pair-of-5-5-10-K-Q))
      => results/black-player-with-pair-of-5-5-A-K-Q-wins

      (compare-hands
        (black-player-with examples/pair-of-5-5-10-J-Q)
        (white-player-with examples/pair-of-5-5-10-K-Q))
      => results/white-player-with-pair-of-5-5-10-K-Q-wins

      (compare-hands
        (black-player-with examples/pair-of-5-5-10-J-Q)
        (white-player-with examples/pair-of-5-5-10-J-J))
      => results/black-player-with-pair-of-5-5-10-J-Q-wins

      (compare-hands
        (black-player-with examples/pair-of-2)
        (white-player-with examples/pair-of-2)) => results/tie))

  (facts
    "High card hands"
    (fact
      "beats or ties with one of its kind"

      (compare-hands (black-player-with examples/high-card-with-K-J-9-7-5)
                     (white-player-with examples/high-card-with-9-7-5-3-2))
      => results/black-player-with-high-card-with-K-J-9-7-5-wins

      (compare-hands (black-player-with examples/high-card-with-A-J-9-5-3)
                     (white-player-with examples/high-card-with-A-10-9-6-4))
      => results/black-player-with-high-card-with-A-J-9-5-3-wins)))
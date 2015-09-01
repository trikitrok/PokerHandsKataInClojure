(ns poker-hands.ranking-test
  (:use midje.sweet)
  (:use [poker-hands.ranking])
  (:require [poker-hands.types-examples
             :as examples
             :refer [white-player-with black-player-with]]))

(facts
  "About the hands ranking system"

  (facts
    "Straight Flush hand"
    (facts
      "beats any other kind of hand"
      (let [resulting-in-white-player-with-straight-flush-win
            {:winning (white-player-with examples/straight-flush-with-Q)}
            white-player-with-straight-flush (white-player-with examples/straight-flush-with-Q)]

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/high-card-with-K-J-9-7-5))
        => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          (black-player-with examples/pair-of-2)
          white-player-with-straight-flush)
        => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/two-pairs-of-5-2))
        => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/triplet-of-5))
        => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/straight-with-Q))
        => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/flush-of-K-J-9-3-2))
        => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/full-house-of-5-2))
        => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/four-kind-of-5))
        => resulting-in-white-player-with-straight-flush-win

        ))

    (facts
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/straight-flush-with-K)
        (black-player-with examples/straight-flush-with-A))
      => {:winning (black-player-with examples/straight-flush-with-A)}

      (compare-hands
        (white-player-with examples/straight-flush-with-6)
        (black-player-with examples/straight-flush-with-6)) => nil))

  (facts
    "Four of a kind hand"
    (fact
      "beats any other kind of hand except a Straight Flush"
      (let [black-player-with-four-kind-of-5 (black-player-with examples/four-kind-of-5)
            resulting-in-black-player-with-four-kind-of-5-win {:winning black-player-with-four-kind-of-5}]

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/full-house-of-5-2))
        => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/flush-of-K-J-9-3-2))
        => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/straight-with-Q))
        => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/triplet-of-5))
        => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/two-pairs-of-5-2))
        => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/pair-of-2))
        => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/high-card-with-K-J-9-7-5))
        => resulting-in-black-player-with-four-kind-of-5-win)

      (fact
        "beats or ties with one of its kind"
        (compare-hands
          (white-player-with examples/four-kind-of-6)
          (black-player-with examples/four-kind-of-5))
        => {:winning (white-player-with examples/four-kind-of-6)}

        (compare-hands
          (white-player-with examples/four-kind-of-4-with-kicking-Q)
          (black-player-with examples/four-kind-of-4-with-kicking-K))
        => {:winning (black-player-with examples/four-kind-of-4-with-kicking-K)}

        (compare-hands
          (white-player-with examples/four-kind-of-6)
          (black-player-with examples/four-kind-of-6)) => nil)))

  (facts
    "Full house hand"
    (fact
      "beats any other kind of hand except Straigh Flush and Four of a Kind"
      (let [white-player-with-full-house-of-5-2 (white-player-with examples/full-house-of-5-2)
            resulting-in-white-player-with-full-house-of-5-2-win {:winning (white-player-with examples/full-house-of-5-2)}]

        (compare-hands
          (black-player-with examples/flush-of-K-J-9-3-2)
          white-player-with-full-house-of-5-2)
        => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/straight-with-Q)
          white-player-with-full-house-of-5-2)
        => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/triplet-of-5)
          white-player-with-full-house-of-5-2)
        => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/two-pairs-of-5-2)
          white-player-with-full-house-of-5-2)
        => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/pair-of-2)
          white-player-with-full-house-of-5-2)
        => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/high-card-with-K-J-9-7-5)
          white-player-with-full-house-of-5-2)
        => resulting-in-white-player-with-full-house-of-5-2-win))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/full-house-of-9-4)
        (black-player-with examples/full-house-of-8-A))
      => {:winning (white-player-with examples/full-house-of-9-4)}

      (compare-hands
        (white-player-with examples/full-house-of-8-K)
        (black-player-with examples/full-house-of-8-A))
      => {:winning (black-player-with examples/full-house-of-8-A)}

      (compare-hands
        (white-player-with examples/full-house-of-8-K)
        (black-player-with examples/full-house-of-8-K)) => nil))

  (facts
    "Flush hand"
    (fact
      "beats any other kind of hand except Straigh Flush, Four of a Kind and FullHouse"
      (let [black-player-with-flush-of-A (black-player-with examples/flush-of-K-J-9-3-2)
            resulting-in-black-player-with-flush-of-A-win {:winning (black-player-with examples/flush-of-K-J-9-3-2)}]

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/straight-with-Q))
        => resulting-in-black-player-with-flush-of-A-win

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/triplet-of-5))
        => resulting-in-black-player-with-flush-of-A-win

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/two-pairs-of-5-2))
        => resulting-in-black-player-with-flush-of-A-win

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/pair-of-2))
        => resulting-in-black-player-with-flush-of-A-win

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/high-card-with-K-J-9-7-5))
        => resulting-in-black-player-with-flush-of-A-win))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/flush-of-K-J-9-3-2)
        (black-player-with examples/flush-of-K-J-7-6-5))
      => {:winning (white-player-with examples/flush-of-K-J-9-3-2)}

      (compare-hands
        (white-player-with examples/flush-of-K-J-9-3-2)
        (black-player-with examples/flush-of-K-J-9-3-2)) => nil))

  (facts
    "Straight hand"
    (fact
      "beats any other kind of hand except Straigh Flush, Four of a Kind, FullHouse and Flush"
      (let [white-player-with-straight-with-A (white-player-with examples/straight-with-A)
            resulting-in-white-player-with-straight-with-A-win {:winning (white-player-with examples/straight-with-A)}]
        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/triplet-of-5))
        => resulting-in-white-player-with-straight-with-A-win

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/two-pairs-of-5-2))
        => resulting-in-white-player-with-straight-with-A-win

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/pair-of-2))
        => resulting-in-white-player-with-straight-with-A-win

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/high-card-with-K-J-9-7-5))
        => resulting-in-white-player-with-straight-with-A-win))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (black-player-with examples/straight-with-Q)
        (white-player-with examples/straight-wheel))
      => {:winning (black-player-with examples/straight-with-Q)}

      (compare-hands
        (black-player-with examples/straight-with-Q)
        (white-player-with examples/straight-with-Q)) => nil))

  (facts
    "Three of a kind"

    (fact
      "only beats Two Pairs, Pair and High Card hands"
      (let [white-player-with-triplet-of-5 (white-player-with examples/triplet-of-5)
            resulting-in-white-player-with-triplet-of-5-win {:winning (white-player-with examples/triplet-of-5)}]

        (compare-hands
          white-player-with-triplet-of-5
          (black-player-with examples/two-pairs-of-5-2))
        => resulting-in-white-player-with-triplet-of-5-win

        (compare-hands
          white-player-with-triplet-of-5
          (black-player-with examples/pair-of-2))
        => resulting-in-white-player-with-triplet-of-5-win

        (compare-hands
          white-player-with-triplet-of-5
          (black-player-with examples/high-card-with-K-J-9-7-5))
        => resulting-in-white-player-with-triplet-of-5-win))

    (fact
      "beats or ties with one of its kind"

      (compare-hands
        (black-player-with examples/triplet-of-5-5-5-3-2)
        (white-player-with examples/triplet-of-4-4-4-K-5))
      => {:winning (black-player-with examples/triplet-of-5-5-5-3-2)}

      (compare-hands
        (black-player-with examples/triplet-of-4-4-4-K-5)
        (white-player-with examples/triplet-of-4-4-4-Q-9))
      => {:winning (black-player-with examples/triplet-of-4-4-4-K-5)}

      (compare-hands
        (black-player-with examples/triplet-of-4-4-4-Q-9)
        (white-player-with examples/triplet-of-4-4-4-Q-8))
      => {:winning (black-player-with examples/triplet-of-4-4-4-Q-9)}))

  (facts
    "Two pairs hand"

    (fact
      "only beats Pair and High Card hands"
      (let [white-player-with-two-pairs-of-5-2 (white-player-with examples/two-pairs-of-5-2)
            resulting-in-white-player-with-two-pairs-of-5-2-win {:winning (white-player-with examples/two-pairs-of-5-2)}]

        (compare-hands
          white-player-with-two-pairs-of-5-2
          (black-player-with examples/pair-of-2)) => resulting-in-white-player-with-two-pairs-of-5-2-win))

    (fact
      "beats or ties with one of its kind"

      (compare-hands
        (black-player-with examples/two-pairs-of-J-J-2-2-4)
        (white-player-with examples/two-pairs-of-10-10-9-9-8))
      => {:winning (black-player-with examples/two-pairs-of-J-J-2-2-4)}

      (compare-hands
        (black-player-with examples/two-pairs-of-10-10-5-5-K)
        (white-player-with examples/two-pairs-of-10-10-9-9-8))
      => {:winning (white-player-with examples/two-pairs-of-10-10-9-9-8)}

      (compare-hands
        (black-player-with examples/two-pairs-of-10-10-5-5-A)
        (white-player-with examples/two-pairs-of-10-10-5-5-K))
      => {:winning (black-player-with examples/two-pairs-of-10-10-5-5-A)}

      (compare-hands
        (black-player-with examples/two-pairs-of-5-2)
        (white-player-with examples/two-pairs-of-5-2)) => nil))

  (facts
    "Pair hand"
    (fact
      "only beats High Card hands"
      (compare-hands
        (black-player-with examples/pair-of-2)
        (white-player-with examples/high-card-with-K-J-9-7-5))
      => {:winning (black-player-with examples/pair-of-2)})

    (fact
      "beats or ties with one of its kind"

      (compare-hands
        (black-player-with examples/pair-of-6-6-4-3-2)
        (white-player-with examples/pair-of-5-5-A-K-Q))
      => {:winning (black-player-with examples/pair-of-6-6-4-3-2)}

      (compare-hands
        (black-player-with examples/pair-of-5-5-A-K-Q)
        (white-player-with examples/pair-of-5-5-10-K-Q))
      => {:winning (black-player-with examples/pair-of-5-5-A-K-Q)}

      (compare-hands
        (black-player-with examples/pair-of-5-5-10-J-Q)
        (white-player-with examples/pair-of-5-5-10-K-Q))
      => {:winning (white-player-with examples/pair-of-5-5-10-K-Q)}

      (compare-hands
        (black-player-with examples/pair-of-5-5-10-J-Q)
        (white-player-with examples/pair-of-5-5-10-J-J))
      => {:winning (black-player-with examples/pair-of-5-5-10-J-Q)}

      (compare-hands
        (black-player-with examples/pair-of-2)
        (white-player-with examples/pair-of-2)) => nil))

  (facts
    "High card hands"
    (fact
      "beats or ties with one of its kind"

      (compare-hands (black-player-with examples/high-card-with-K-J-9-7-5)
                     (white-player-with examples/high-card-with-9-7-5-3-2))
      => {:winning (black-player-with examples/high-card-with-K-J-9-7-5)}

      (compare-hands (black-player-with examples/high-card-with-A-J-9-5-3)
                     (white-player-with examples/high-card-with-A-10-9-6-4))
      => {:winning  (black-player-with examples/high-card-with-A-J-9-5-3)})
    )
  )
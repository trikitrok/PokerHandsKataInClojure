(ns poker-hands.ranking-system-test
  (:use midje.sweet)
  (:use [poker-hands.ranking-system])
  (:require [poker-hands.examples
             :as examples
             :refer [white-player-with black-player-with]]))

(facts
  "About the hands ranking system"

  (facts
    "Straight Flush hand"
    (facts
      "beats any other kind of hand"
      (let [resulting-in-white-player-with-straight-flush-win {:winner :white :message "with a straight flush"}
            white-player-with-straight-flush (white-player-with examples/straight-flush-with-Q)]
        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/high-card-with-K)) => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          (black-player-with examples/pair-of-2)
          white-player-with-straight-flush) => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/two-pairs-of-5-2)) => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/triplet-of-5)) => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/straight-with-Q)) => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/flush-with-A)) => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/full-house-of-5-2)) => resulting-in-white-player-with-straight-flush-win

        (compare-hands
          white-player-with-straight-flush
          (black-player-with
            examples/four-kind-of-5)) => resulting-in-white-player-with-straight-flush-win))

    (facts
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/straight-flush-with-K)
        (black-player-with examples/straight-flush-with-A)) => {:winner :black :message "with a straight flush"}

      (compare-hands
        (white-player-with examples/straight-flush-with-6)
        (black-player-with examples/straight-flush-with-6)) => {:winner :no-winner}))

  (facts
    "Four of a kind"
    (fact
      "beats any other kind of hand except a Straight Flush"
      (let [black-player-with-four-kind-of-5 (black-player-with examples/four-kind-of-5)
            resulting-in-black-player-with-four-kind-of-5-win {:winner :black :message "with a four of 5"}]

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/full-house-of-5-2)) => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/flush-with-A)) => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/straight-with-Q)) => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/triplet-of-5)) => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/two-pairs-of-5-2)) => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/pair-of-2)) => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/high-card-with-K)) => resulting-in-black-player-with-four-kind-of-5-win)

      (fact
        "beats or ties with one of its kind"
        (compare-hands
          (white-player-with examples/four-kind-of-6)
          (black-player-with examples/four-kind-of-5)) => {:winner  :white
                                                           :message "with a four of 6"}

        (compare-hands
          (white-player-with examples/four-kind-of-4-with-kicking-Q)
          (black-player-with examples/four-kind-of-4-with-kicking-K)) => {:winner  :black
                                                                          :message "with a four of 4"}

        (compare-hands
          (white-player-with examples/four-kind-of-6)
          (black-player-with examples/four-kind-of-6)) => {:winner :no-winner}))))

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
            examples/flush-of-K-J-9-3-2)) => resulting-in-white-player-with-straight-flush-win

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
    "Four of a kind hand"
    (fact
      "beats any other kind of hand except a Straight Flush"
      (let [black-player-with-four-kind-of-5 (black-player-with examples/four-kind-of-5)
            resulting-in-black-player-with-four-kind-of-5-win {:winner :black :message "with a four of 5"}]

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/full-house-of-5-2)) => resulting-in-black-player-with-four-kind-of-5-win

        (compare-hands
          black-player-with-four-kind-of-5
          (white-player-with examples/flush-of-K-J-9-3-2)) => resulting-in-black-player-with-four-kind-of-5-win

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
          (black-player-with examples/four-kind-of-6)) => {:winner :no-winner})))

  (facts
    "Full house hand"
    (fact
      "beats any other kind of hand except Straigh Flush and Four of a Kind"
      (let [white-player-with-full-house-of-5-2 (white-player-with examples/full-house-of-5-2)
            resulting-in-white-player-with-full-house-of-5-2-win {:winner  :white
                                                                  :message "with a full house of three 5 and two 2"}]

        (compare-hands
          (black-player-with examples/flush-of-K-J-9-3-2)
          white-player-with-full-house-of-5-2) => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/straight-with-Q)
          white-player-with-full-house-of-5-2) => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/triplet-of-5)
          white-player-with-full-house-of-5-2) => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/two-pairs-of-5-2)
          white-player-with-full-house-of-5-2) => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/pair-of-2)
          white-player-with-full-house-of-5-2) => resulting-in-white-player-with-full-house-of-5-2-win

        (compare-hands
          (black-player-with examples/high-card-with-K)
          white-player-with-full-house-of-5-2) => resulting-in-white-player-with-full-house-of-5-2-win))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/full-house-of-9-4)
        (black-player-with examples/full-house-of-8-A)) => {:winner  :white
                                                            :message "with a full house of three 9 and two 4"}
      (compare-hands
        (white-player-with examples/full-house-of-8-K)
        (black-player-with examples/full-house-of-8-A)) => {:winner  :black
                                                            :message "with a full house of three 8 and two A"}
      (compare-hands
        (white-player-with examples/full-house-of-8-K)
        (black-player-with examples/full-house-of-8-K)) => {:winner :no-winner}))

  (facts
    "Flush hand"
    (fact
      "beats any other kind of hand except Straigh Flush, Four of a Kind and FullHouse"
      (let [black-player-with-flush-of-A (black-player-with examples/flush-of-K-J-9-3-2)
            resulting-in-black-player-with-flush-of-A-win {:winner  :black
                                                           :message "with a flush of K J 9 3 2"}]

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/straight-with-Q)) => resulting-in-black-player-with-flush-of-A-win

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/triplet-of-5)) => resulting-in-black-player-with-flush-of-A-win

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/two-pairs-of-5-2)) => resulting-in-black-player-with-flush-of-A-win

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/pair-of-2)) => resulting-in-black-player-with-flush-of-A-win

        (compare-hands
          black-player-with-flush-of-A
          (white-player-with examples/high-card-with-K)) => resulting-in-black-player-with-flush-of-A-win))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (white-player-with examples/flush-of-K-J-9-3-2)
        (black-player-with examples/flush-of-K-J-7-6-5)) => {:winner  :white
                                                             :message "with a flush of K J 9 3 2"}
      (compare-hands
        (white-player-with examples/flush-of-K-J-9-3-2)
        (black-player-with examples/flush-of-K-J-9-3-2)) => {:winner :no-winner}))

  (facts
    "Straight hand"
    (fact
      "beats any other kind of hand except Straigh Flush, Four of a Kind, FullHouse and Flush"
      (let [white-player-with-straight-with-A (white-player-with examples/straight-with-A)
            resulting-in-white-player-with-straight-with-A-win {:winner  :white
                                                                :message "with a straight of A"}]
        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/triplet-of-5)) => resulting-in-white-player-with-straight-with-A-win

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/two-pairs-of-5-2)) => resulting-in-white-player-with-straight-with-A-win

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/pair-of-2)) => resulting-in-white-player-with-straight-with-A-win

        (compare-hands
          white-player-with-straight-with-A
          (black-player-with examples/high-card-with-K)) => resulting-in-white-player-with-straight-with-A-win))

    (fact
      "beats or ties with one of its kind"
      (compare-hands
        (black-player-with examples/straight-with-Q)
        (white-player-with examples/straight-wheel)) => {:winner  :black
                                                         :message "with a straight of Q"}

      (compare-hands
        (black-player-with examples/straight-with-Q)
        (white-player-with examples/straight-with-Q)) => {:winner  :no-winner})))

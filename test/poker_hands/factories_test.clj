(ns poker-hands.factories-test
  (:use midje.sweet)
  (:require [poker-hands.factories :refer [create]])
  (:require [poker-hands.examples.cards :as examples])
  (:require [poker-hands.examples.types :as types]))

(facts
  "about poker hands creation"

  (fact
    "can create a High Card hand"
    (create
      (examples/identified-as-high-card-with
        examples/higher-cards-7H-9D-5S-3C-2D)) => types/high-card-with-9-7-5-3-2)

  (fact
    "can create a Flush hand"
    (create
      (examples/identified-as-flush-with
        examples/flush-cards-KC-JC-9C-3C-2C)) => types/flush-of-K-J-9-3-2)

  (fact
    "can create a Pair hand"
    (create
      (examples/identified-as-pair-with
        examples/pair-card-with-2S-8D-5C-QS-2D)) => types/pair-of-2)

  (fact
    "can create a Two Pairs hand"
    (create
      (examples/identified-as-two-pairs-with
        examples/two-pair-with-2S-5D-5C-QS-2D)) => types/two-pairs-of-5-2)

  (fact
    "can create a Three of a Kind hand"
    (create
      (examples/identified-as-triplet-with
        examples/triplet-with-5S-5D-5C-QS-2D)) => types/triplet-of-5)

  (fact
    "can create a Straight hand"
    (create
      (examples/identified-as-straight-with
        examples/straight-with-QS-JD-10H-9S-8C)) => types/straight-with-Q

    (create
      (examples/identified-as-straight-with
        examples/straight-with-AS-KC-QH-JH-10D)) => types/straight-with-A

    (create
      (examples/identified-as-straight-with
        examples/straight-wheel-5C-4H-3D-2C-AS)) => types/straight-wheel)

  (fact
    "can create a Straight Flush hand"
    (create
      (examples/identified-as-straight-flush-with
        examples/straight-flush-with-QS-JS-10S-9S-8S)) => types/straight-flush-with-Q)

  (fact
    "can create a Full House hand"
    (create
      (examples/identified-as-full-house-with
        examples/full-house-with-5S-2S-5D-2D-5C)) => types/full-house-of-5-2)

  (fact
    "can create a Four of a Kind hand"
    (create
      (examples/identified-as-four-kind-with
        examples/four-of-kind-with-5S-5D-5C-QS-5H)) => types/four-kind-of-5))

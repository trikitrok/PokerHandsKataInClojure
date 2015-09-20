(ns poker-hands.identification-test
  (:use midje.sweet)
  (:require [poker-hands.identification :refer [identify-hand-type]])
  (:require [poker-hands.examples.cards :as examples]))

(facts
  "about poker hands identification"

  (fact
    "can identify a High Card hand"
    (identify-hand-type
      examples/higher-cards-7H-9D-5S-3C-2D) => {:type  :high-card
                                                :cards examples/higher-cards-7H-9D-5S-3C-2D}

    (identify-hand-type
      examples/higher-cards-5H-7D-JS-9C-KD) => {:type  :high-card
                                                :cards examples/higher-cards-5H-7D-JS-9C-KD})

  (fact
    "can identify a Flush hand"
    (identify-hand-type
      examples/flush-cards-KC-JC-9C-3C-2C) => {:type  :flush
                                               :cards examples/flush-cards-KC-JC-9C-3C-2C})

  (fact
    "can identify a Pair hand"
    (identify-hand-type
      examples/pair-card-with-2S-8D-5C-QS-2D) => {:type  :pair
                                                  :cards examples/pair-card-with-2S-8D-5C-QS-2D})

  (fact
    "can identify a Two Pairs hand"
    (identify-hand-type
      examples/two-pair-with-2S-5D-5C-QS-2D) => {:type  :two-pairs
                                                 :cards examples/two-pair-with-2S-5D-5C-QS-2D})

  (fact
    "can identify a Three of a Kind hand"
    (identify-hand-type
      examples/triplet-with-5S-5D-5C-QS-2D) => {:type  :triplet
                                                :cards examples/triplet-with-5S-5D-5C-QS-2D})

  (fact
    "can identify a Straight hand"
    (identify-hand-type
      examples/straight-with-QS-JD-10H-9S-8C) => {:type  :straight
                                                  :cards examples/straight-with-QS-JD-10H-9S-8C}
    (identify-hand-type
      examples/straight-with-AS-KC-QH-JH-10D) => {:type  :straight
                                                  :cards examples/straight-with-AS-KC-QH-JH-10D}
    (identify-hand-type
      examples/straight-wheel-5C-4H-3D-2C-AS) => {:type  :straight
                                                  :cards examples/straight-wheel-5C-4H-3D-2C-AS})

  (fact
    "can identify a Straight Flush hand"
    (identify-hand-type
      examples/straight-flush-with-QS-JS-10S-9S-8S) => {:type  :straight-flush
                                                        :cards examples/straight-flush-with-QS-JS-10S-9S-8S})

  (fact
    "can identify a Full House hand"
    (identify-hand-type
      examples/full-house-with-5S-2S-5D-2D-5C) => {:type  :full-house
                                                   :cards examples/full-house-with-5S-2S-5D-2D-5C})

  (fact
    "can identify a Four of a Kind hand"
    (identify-hand-type
      examples/four-of-kind-with-5S-5D-5C-QS-5H) => {:type  :four-kind
                                                     :cards examples/four-of-kind-with-5S-5D-5C-QS-5H}))

(ns poker-hands.result-messages-test
  (:use midje.sweet)
  (:use [poker-hands.result-messages])
  (:require [poker-hands.examples.results :as results-examples]))

(facts
  "About results"
  (message
    results-examples/white-player-with-straight-flush-with-Q-wins)
  => "White wins. - with a Straight Flush: Q"

  (message
    results-examples/black-player-with-straight-flush-with-A-wins)
  => "Black wins. - with a Straight Flush: A"

  (message
    results-examples/black-player-with-four-kind-of-5-wins)
  => "Black wins. - with a Four of Kind: 5"

  (message
    results-examples/white-player-with-full-house-of-5-2-wins)
  => "White wins. - with a Full House of three 5 and two 2"

  (message
    results-examples/black-player-with-flush-of-K-J-9-3-2-wins)
  => "Black wins. - with a Flush of K J 9 3 2"

  (message
    results-examples/white-player-with-straight-with-A-wins)
  => "White wins. - with a Straight of A"

  (message
    results-examples/white-player-with-triplet-of-5-wins)
  => "White wins. - with a Triplet of 5"

  (message
    results-examples/white-player-with-two-pairs-of-5-2-wins)
  => "White wins. - with Two Pairs: 5 and 2"

  (message
    results-examples/black-player-with-pair-of-5-5-10-J-Q-wins)
  => "Black wins. - with a Pair: 5"

  (message
    results-examples/black-player-with-high-card-with-K-J-9-7-5-wins)
  => "Black wins. - with a High Card: K J 9 7 5"

  (message results-examples/tie) => "Tie.")

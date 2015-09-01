(ns poker-hands.messages
  (:require [poker-hands.creation])
  (:import (poker_hands.creation StraightFlush FourKind FullHouse Flush Straight Triplet TwoPairs Pair HighCard)))

(defprotocol VictoryMessager
  (victory-message [this]))

(extend-protocol VictoryMessager
  StraightFlush
  (victory-message [_]
    "with a straight flush")

  FourKind
  (victory-message [this]
    (str "with a four of " (first (:cards this))))

  FullHouse
  (victory-message [this]
    (str "with a full house of three " (first (:cards this))
         " and two " (second (:cards this))))

  Flush
  (victory-message [this]
    (str "with a flush of " (clojure.string/join " " (:cards this))))

  Straight
  (victory-message [this]
    (str "with a straight of " (first (:cards this))))

  Triplet
  (victory-message [this]
    (str "with a triplet of " (first (:cards this))))

  TwoPairs
  (victory-message [this]
    (str "with pairs of " (first (:cards this)) " and " (second (:cards this))))

  Pair
  (victory-message [this]
    (str "with a pair of " (first (:cards this))))

  HighCard
  (victory-message [this]
    (str "with a hig card of " (first (:cards this)))))

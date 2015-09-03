(ns poker-hands.results
  (:require [poker-hands.hand-types :as hand-types])
  (:import (poker_hands.hand_types StraightFlush FourKind FullHouse Flush Straight Triplet TwoPairs Pair HighCard)))

(def ^:private faces hand-types/faces)

(defprotocol VictoryMessager
  (victory-message [this]))

(extend-protocol VictoryMessager
  StraightFlush
  (victory-message [this]
    (str "with a Straight Flush: " (first (faces this))))

  FourKind
  (victory-message [this]
    (str "with a Four of Kind: " (first (faces this))))

  FullHouse
  (victory-message [this]
    (str "with a Full House of three " (first (faces this))
         " and two " (second (faces this))))

  Flush
  (victory-message [this]
    (str "with a Flush of " (clojure.string/join " " (faces this))))

  Straight
  (victory-message [this]
    (str "with a Straight of " (first (faces this))))

  Triplet
  (victory-message [this]
    (str "with a Triplet of " (first (faces this))))

  TwoPairs
  (victory-message [this]
    (str "with Two Pairs: " (first (faces this)) " and " (second (faces this))))

  Pair
  (victory-message [this]
    (str "with a Pair: " (first (faces this))))

  HighCard
  (victory-message [this]
    (str "with a High Card: " (clojure.string/join " " (faces this)))))

(def ^:private winning :winning)

(defn- black? [player]
  (= :black player))

(def ^:private player :player)

(defn- winner [player]
  (if (black? player)
    "Black wins."
    "White wins."))

(defn- compose-victory-message [result]
  (let [winning-hand (winning result)]
    (str (winner (player winning-hand))
         " - "
         (victory-message winning-hand))))

(defn- compose-tie-message [] "Tie.")

(def ^:private tie? (partial = :tie))

(defn message [result]
  (if (tie? result)
    (compose-tie-message)
    (compose-victory-message result)))
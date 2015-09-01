(ns poker-hands.ranking-system
  (:require [poker-hands.cards :refer [compute-value]])
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

(defn- ranking [hand]
  (let [hands-ranking [HighCard Pair TwoPairs Triplet Straight Flush FullHouse FourKind StraightFlush]]
    (.indexOf hands-ranking (class hand))))

(defn- result [hand]
  (if (nil? hand)
    {:winner :no-winner}
    {:winner  (:player hand)
     :message (victory-message hand)}))

(defn- untie [hand1 hand2]
  (let
    [first-different-values
     (map compute-value
          (first
            (drop-while #(= (first %) (second %))
                        (map vector
                             (:cards hand1)
                             (:cards hand2)))))]
    (if (empty? first-different-values)
      nil
      (if (> (first first-different-values) (second first-different-values))
        hand1
        hand2))))

(defn compare-hands [hand1 hand2]
  (result
    (cond
      (> (ranking hand1) (ranking hand2)) hand1
      (< (ranking hand1) (ranking hand2)) hand2
      :else (untie hand1 hand2))))

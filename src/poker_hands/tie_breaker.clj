(ns poker-hands.tie-breaker
  (:require [poker-hands.cards :refer [compute-value]])
  (:require [poker-hands.hands])
  (:import (poker_hands.hands StraightFlush FourKind FullHouse Flush)))

(defn- card-value [value-type hand]
  (-> hand
      value-type
      compute-value))

(def ^:private four-kind-card-value
  (partial card-value :four-kind-card))

(def ^:private four-kind-kick-value
  (partial card-value :no-four-card))

(def ^:private triplet-card-value
  (partial card-value :triplet-card))

(def ^:private pair-card-value
  (partial card-value :pair-card))

(defprotocol TieBreaker
  "Tie-breaking"
  (untie [this other]))

(defn- untie-hands [hand1 hand2 value-fn tie-breaking-fn]
  (let [hand1-value (value-fn hand1)
        hand2-value (value-fn hand2)]
    (cond
      (> hand1-value hand2-value) hand1
      (< hand1-value hand2-value) hand2
      :else (tie-breaking-fn hand1 hand2))))

(defn- untie-using-first-highest-card [hand1 hand2]
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

(extend-protocol TieBreaker
  StraightFlush
  (untie [this other]
    (untie-using-first-highest-card this other))

  FourKind
  (untie [this other]
    (untie-hands
      this other four-kind-card-value
      (fn [this other]
        (untie-hands this other four-kind-kick-value (fn [_ _] nil)))))

  FullHouse
  (untie [this other]
    (untie-using-first-highest-card this other))

  Flush
  (untie [this other]
    (untie-using-first-highest-card this other)))
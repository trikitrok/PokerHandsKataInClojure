(ns poker-hands.tie-breaker
  (:require [poker-hands.cards :refer [compute-value]])
  (:require [poker-hands.hands])
  (:import (poker_hands.hands StraightFlush FourKind FullHouse)))

(defn- card-value [value-type hand]
  (-> hand
      value-type
      compute-value))

(def ^:private highest-card-value
  (partial card-value :highest-card))

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

(extend-protocol TieBreaker
  StraightFlush
  (untie [this other]
    (untie-hands this other highest-card-value (fn [_ _] nil)))

  FourKind
  (untie [this other]
    (untie-hands
      this other four-kind-card-value
      (fn [this other]
        (untie-hands this other four-kind-kick-value (fn [_ _] nil)))))

  FullHouse
  (untie [this other]
    (untie-hands
      this other triplet-card-value
      (fn [this other]
        (untie-hands this other pair-card-value (fn [_ _] nil))))))

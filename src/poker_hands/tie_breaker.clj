(ns poker-hands.tie-breaker
  (:require [poker-hands.cards :refer [compute-value]])
  (:require [poker-hands.hands])
  (:import (poker_hands.hands StraightFlush FourKind FullHouse)))

(defn- highest-card-value [hand]
  (-> hand
      :highest-card
      compute-value))

(defn- four-kind-card-value [hand]
  (-> hand
      :four-kind-card
      first
      compute-value))

(defn- four-kind-kick-value [hand]
  (-> hand
      :no-four-card
      first
      compute-value))

(defn- triplet-card-value [hand]
  (-> hand
      :triplet-card
      first
      compute-value))

(defn- pair-card-value [hand]
  (-> hand
      :pair-card
      first
      compute-value))

(defprotocol TieBreaker
  "Tie-breaking"
  (untie [this other]))

(extend-protocol TieBreaker
  StraightFlush
  (untie [this other]
    (let [hand1-value (highest-card-value this)
          hand2-value (highest-card-value other)]
      (cond
        (> hand1-value hand2-value) this
        (< hand1-value hand2-value) other
        :else nil)))

  FourKind
  (untie [this other]
    (let [hand1-value (four-kind-card-value this)
          hand2-value (four-kind-card-value other)]
      (cond
        (> hand1-value hand2-value) this
        (< hand1-value hand2-value) other
        :else (let [hand1-kick-value (four-kind-kick-value this)
                    hand2-kick-value (four-kind-kick-value other)]
                (cond
                  (> hand1-kick-value hand2-kick-value) this
                  (< hand1-kick-value hand2-kick-value) other
                  :else nil)))))

  FullHouse
  (untie [this other]
    (let [hand1-value (triplet-card-value this)
          hand2-value (triplet-card-value other)]
      (cond
        (> hand1-value hand2-value) this
        (< hand1-value hand2-value) other
        :else (let [hand1-kick-value (pair-card-value this)
                    hand2-kick-value (pair-card-value other)]
                (cond
                  (> hand1-kick-value hand2-kick-value) this
                  (< hand1-kick-value hand2-kick-value) other
                  :else nil))))))

(ns poker-hands.tie-breaker
  (:require [poker-hands.cards :refer [compute-value]]))

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

(defmulti untie :type)
(defmethod ^:private untie :straight-flush [hand1 hand2]
  (let [hand1-value (highest-card-value hand1)
        hand2-value (highest-card-value hand2)]
    (cond
      (> hand1-value hand2-value) hand1
      (< hand1-value hand2-value) hand2
      :else nil)))

(defmethod ^:private untie :four-kind [hand1 hand2]
  (let [hand1-value (four-kind-card-value hand1)
        hand2-value (four-kind-card-value hand2)]
    (cond
      (> hand1-value hand2-value) hand1
      (< hand1-value hand2-value) hand2
      :else (let [hand1-kick-value (four-kind-kick-value hand1)
                  hand2-kick-value (four-kind-kick-value hand2)]
              (cond
                (> hand1-kick-value hand2-kick-value) hand1
                (< hand1-kick-value hand2-kick-value) hand2
                :else nil)))))
(ns poker-hands.ranking-system
  (:require [poker-hands.cards :refer [compute-value]]))

(def ^:private hands-ranking
  [:high-card :pair :two-pairs :triplet :straight :flush :full-house :four-kind :straight-flush])

(defn- ranking [hand]
  (.indexOf hands-ranking (:type hand)))

(def ^:private four-kind-card (comp first :four-kind-card))
(defmulti ^:private victory-message :type)
(defmethod ^:private victory-message :straight-flush [_]
  "with a straight flush")
(defmethod ^:private victory-message :four-kind [hand]
  (str "with a four of " (four-kind-card hand)))


(defn- win [hand]
  {:winner  (:player hand)
   :message (victory-message hand)})

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

(defmulti ^:private untie :type)
(defmethod ^:private untie :straight-flush [hand1 hand2]
  (let [hand1-value (highest-card-value hand1)
        hand2-value (highest-card-value hand2)]
    (cond
      (> hand1-value hand2-value) (win hand1)
      (< hand1-value hand2-value) (win hand2)
      :else {:winner :no-winner})))
(defmethod ^:private untie :four-kind [hand1 hand2]
  (let [hand1-value (four-kind-card-value hand1)
        hand2-value (four-kind-card-value hand2)]
    (cond
      (> hand1-value hand2-value) (win hand1)
      (< hand1-value hand2-value) (win hand2)
      :else (let [hand1-kick-value (four-kind-kick-value hand1)
                  hand2-kick-value (four-kind-kick-value hand2)]
              (cond
                (> hand1-kick-value hand2-kick-value) (win hand1)
                (< hand1-kick-value hand2-kick-value) (win hand2)
                :else {:winner :no-winner})))))

(defn compare-hands [hand1 hand2]
  (cond
    (> (ranking hand1) (ranking hand2)) (win hand1)
    (< (ranking hand1) (ranking hand2)) (win hand2)
    :else (untie hand1 hand2)))

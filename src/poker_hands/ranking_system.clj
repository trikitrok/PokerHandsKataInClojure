(ns poker-hands.ranking-system
  (:require [poker-hands.result-messages :as result])
  (:require [poker-hands.tie-breaker :as tie-breaker]))

(def ^:private hands-ranking
  [:high-card :pair :two-pairs :triplet :straight :flush :full-house :four-kind :straight-flush])

(defn- ranking [hand]
  (.indexOf hands-ranking (:type hand)))

(defn- win [hand]
  {:winner  (:player hand)
   :message (result/victory-message hand)})

(defn- result [hand]
  (if (nil? hand)
    {:winner :no-winner}
    (win hand)))

(defn compare-hands [hand1 hand2]
  (result
    (cond
      (> (ranking hand1) (ranking hand2)) hand1
      (< (ranking hand1) (ranking hand2)) hand2
      :else (tie-breaker/untie hand1 hand2))))

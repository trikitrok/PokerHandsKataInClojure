(ns poker-hands.ranking-system
  (:require [poker-hands.cards :refer [compute-value]])
  (:require [poker-hands.hands])
  (:import (poker_hands.hands StraightFlush FourKind FullHouse Flush Straight Triplet TwoPairs Pair)))

(def ^:private hands-ranking
  [:high-card Pair TwoPairs Triplet Straight Flush FullHouse FourKind StraightFlush])

(defn- ranking [hand]
  (.indexOf hands-ranking (or (:type hand) (class hand))))

(defmulti ^:private victory-message class)

(defmethod ^:private victory-message StraightFlush [_]
  "with a straight flush")

(defmethod ^:private victory-message FourKind [hand]
  (str "with a four of " (first (:cards hand))))

(defmethod ^:private victory-message FullHouse [hand]
  (str "with a full house of three " (first (:cards hand))
       " and two " (second (:cards hand))))

(defmethod ^:private victory-message Flush [hand]
  (str "with a flush of " (clojure.string/join " " (:cards hand))))

(defmethod ^:private victory-message Straight [hand]
  (str "with a straight of " (first (:cards hand))))

(defmethod ^:private victory-message Triplet [hand]
  (str "with a triplet of " (first (:cards hand))))

(defmethod ^:private victory-message TwoPairs [hand]
  (str "with pairs of " (first (:cards hand)) " and " (second (:cards hand))))

(defmethod ^:private victory-message Pair [hand]
  (str "with a pair of " (first (:cards hand))))

(defn- win [hand]
  {:winner  (:player hand)
   :message (victory-message hand)})

(defn- result [hand]
  (if (nil? hand)
    {:winner :no-winner}
    (win hand)))

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

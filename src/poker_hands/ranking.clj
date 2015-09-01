(ns poker-hands.ranking
  (:require [poker-hands.cards :refer [compute-value]])
  (:require [poker-hands.creation])
  (:import (poker_hands.creation HighCard TwoPairs Pair Triplet Straight Flush FullHouse FourKind StraightFlush)))

(defn- ranking [hand]
  (let [hands-ranking [HighCard Pair TwoPairs Triplet Straight Flush FullHouse FourKind StraightFlush]]
    (.indexOf hands-ranking (class hand))))

(defn- compute-first-different-pair-of-values [hand1 hand2]
  (map compute-value
       (first
         (drop-while #(= (first %) (second %))
                     (map vector
                          (:cards hand1)
                          (:cards hand2))))))

(defn- untie [hand1 hand2]
  (let
    [first-different-pair-of-values (compute-first-different-pair-of-values hand1 hand2)]
    (if (empty? first-different-pair-of-values)
      nil
      (if (> (first first-different-pair-of-values)
             (second first-different-pair-of-values))
        hand1
        hand2))))

(defn- wrap-in-result [hand]
  (if (nil? hand)
    nil
    {:winning hand}))

(defn- compute-winner-hand [[hand1 hand2]]
  (let [ranking1 (ranking hand1)
        ranking2 (ranking hand2)]
    (cond
      (> ranking1 ranking2) hand1
      (< ranking1 ranking2) hand2
      :else (untie hand1 hand2))))

(defn compare-hands [hand1 hand2]
  (-> [hand1 hand2]
      compute-winner-hand
      wrap-in-result))

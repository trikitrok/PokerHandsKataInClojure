(ns poker-hands.ranking
  (:require [poker-hands.cards :refer [face-rank]])
  (:require [poker-hands.hand-types :as hand-types]))

(defn- first-different-pair-of-ranks [hand1 hand2]
  (map face-rank (first (drop-while #(= (first %) (second %))
                                    (map vector
                                         (hand-types/faces hand1)
                                         (hand-types/faces hand2))))))

(defn- untie [hand1 hand2]
  (let
    [[rank1 rank2 :as first-different-ranks] (first-different-pair-of-ranks hand1 hand2)]
    (if (empty? first-different-ranks)
      nil
      (if (> rank1 rank2)
        hand1
        hand2))))

(defn- wrap-in-result [hand]
  (if (nil? hand)
    :tie
    {:winning hand}))

(defn- compute-winner-hand [[hand1 hand2]]
  (let [ranking1 (hand-types/ranking hand1)
        ranking2 (hand-types/ranking hand2)]
    (cond
      (> ranking1 ranking2) hand1
      (< ranking1 ranking2) hand2
      :else (untie hand1 hand2))))

(defn compare-hands [hand1 hand2]
  (-> [hand1 hand2]
      compute-winner-hand
      wrap-in-result))


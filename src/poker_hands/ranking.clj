(ns poker-hands.ranking
  (:require [poker-hands.cards :refer [face-rank]])
  (:require [poker-hands.hand-types :as hand-types])
  (:import (poker_hands.hand_types
             StraightFlush FourKind FullHouse Flush Straight Triplet TwoPairs Pair HighCard)))

(def ^:private hands-ranking
  [HighCard Pair TwoPairs Triplet Straight Flush FullHouse FourKind StraightFlush])

(defn- ranking [hand]
  (.indexOf hands-ranking (class hand)))

(defn- first-different-ranks [hand1 hand2]
  (->> (map vector (hand-types/faces hand1) (hand-types/faces hand2))
       (drop-while #(= (first %) (second %)))
       first
       (map face-rank)))

(defn- untie [[hand1 hand2]]
  (let
    [[rank1 rank2 :as first-different-ranks] (first-different-ranks hand1 hand2)]
    (if (empty? first-different-ranks)
      nil
      (if (> rank1 rank2)
        hand1
        hand2))))

(defn- compute-winning-hand [hands]
 (let [rankings (map ranking hands)]
   (if (apply = rankings)
     (untie hands)
     (apply max-key ranking hands))))

(def tie? (partial = :tie))

(def winning :winning)

(defn result [hands]
  (if-let [winning-hand (compute-winning-hand hands)]
    {:winning winning-hand}
    :tie))

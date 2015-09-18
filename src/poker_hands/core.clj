(ns poker-hands.core
  (:require [poker-hands.hands :as hands])
  (:require [poker-hands.ranking :as ranking])
  (:require [poker-hands.results :as results]))

(defn extract-player [player-hand]
  (let [player (first (clojure.string/split player-hand #":"))]
    (if (= player "Black") :black :white)))

(defn extract-hand-description [player-hand]
  (clojure.string/trim (second (clojure.string/split player-hand #":"))))

(defn- create-hands [player-hands]
  (let [players (map extract-player player-hands)
        hand-descriptions (map extract-hand-description player-hands)]
    (map #(assoc (hands/create %1) :player %2)
         hand-descriptions
         players)))

(defn- compare-hands [[hand1 hand2]]
  (ranking/compare-hands hand1 hand2))

(defn- show-result [winning-hand]
  (results/message winning-hand))

(defn compare-players-hands [& hands-descriptions]
  (-> hands-descriptions
      create-hands
      compare-hands
      show-result))

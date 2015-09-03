(ns poker-hands.core
  (:require [poker-hands.hands :as hands])
  (:require [poker-hands.ranking :as ranking])
  (:require [poker-hands.results :as results]))

(defn extract-player [player-hand]
  (let [player-description (first (clojure.string/split player-hand #":"))]
    (if (= player-description "Black")
      :black
      :white)))

(defn extract-hand-description [player-hand]
  (clojure.string/trim (second (clojure.string/split player-hand #":"))))

(defn- create-players-hands [player-hands]
  (let [players (map extract-player player-hands)
        hand-descriptions (map extract-hand-description player-hands)]
    (map #(assoc (hands/create %1) :player %2)
         hand-descriptions
         players)))

(defn- compare-player-hands [[hand1 hand2]]
  (ranking/compare-hands hand1 hand2))

(defn- show-result-as-string [winning-hand]
  (results/message winning-hand))

(defn compare-hands [player1-hand player2-hand]
  (-> [player1-hand player2-hand]
      create-players-hands
      compare-player-hands
      show-result-as-string))

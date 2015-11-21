(ns poker-hands.hands
  (:require [poker-hands.cards :as cards])
  (:require [poker-hands.identification :as identification])
  (:require [poker-hands.creation :as creation]))

(defn create-hand [hand-description]
  (-> hand-description
      cards/create-cards
      identification/identify-hand-type
      creation/create-hand))

(defn- extract-player [player-hand]
  (let [player (first (clojure.string/split player-hand #":"))]
    (if (= player "Black") :black :white)))

(defn- extract-hand-description [player-hand]
  (clojure.string/trim (second (clojure.string/split player-hand #":"))))

(defn create [player-hands]
  (let [players (map extract-player player-hands)
        hand-descriptions (map extract-hand-description player-hands)]
    (map #(assoc (create-hand %1) :player %2)
         hand-descriptions
         players)))
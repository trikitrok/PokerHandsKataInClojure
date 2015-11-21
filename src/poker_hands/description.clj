(ns poker-hands.description
  (:require [poker-hands.players :as players]))

(defn extract-player [player-hand]
  (let [player (first (clojure.string/split player-hand #":"))]
    (if (= player "Black")
      players/black
      players/white)))

(defn extract-hand-description [player-hand]
  (clojure.string/trim (second (clojure.string/split player-hand #":"))))

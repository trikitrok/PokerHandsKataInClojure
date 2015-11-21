(ns poker-hands.creation
  (:require [poker-hands.description :refer [extract-hand-description extract-player]])
  (:require [poker-hands.cards :as hand-cards])
  (:require [poker-hands.identification :as hand-identification])
  (:require [poker-hands.factory :as hand-factory]))

(defn create-hand [hand-description]
  (-> hand-description
      hand-cards/create
      hand-identification/identify-hand
      hand-factory/create-hand))

(defn create-player-hand [[player hand-description]]
  (assoc (create-hand hand-description) :player player))

(defn create [player-hands-descriptions]
  (->> player-hands-descriptions
       (map (juxt extract-player extract-hand-description))
       (map create-player-hand)))

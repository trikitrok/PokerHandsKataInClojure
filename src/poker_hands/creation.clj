(ns poker-hands.creation
  (:require [poker-hands.description :as description])
  (:require [poker-hands.cards :as cards])
  (:require [poker-hands.identification :as identification])
  (:require [poker-hands.factories :as factories]))

(defn create-hand [hand-description]
  (-> hand-description
      cards/create-cards
      identification/identify
      factories/create))

(defn create [player-hands]
  (let [players (map description/extract-player player-hands)
        hand-descriptions (map description/extract-hand-description player-hands)]
    (map #(assoc (create-hand %1) :player %2)
         hand-descriptions
         players)))

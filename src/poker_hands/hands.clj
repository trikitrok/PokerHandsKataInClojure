(ns poker-hands.hands
  (:require [poker-hands.cards :as cards])
  (:require [poker-hands.identification :as identification])
  (:require [poker-hands.creation :as creation]))

(defn hand [hand-description]
  (-> hand-description
      cards/create-cards
      identification/identify-hand-type
      creation/create-hand))

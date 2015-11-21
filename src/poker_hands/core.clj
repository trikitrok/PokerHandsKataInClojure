(ns poker-hands.core
  (:require [poker-hands.creation :as hands])
  (:require [poker-hands.ranking :as ranking])
  (:require [poker-hands.result-messages :as results]))

(defn compare-players-hands [& hands-descriptions]
  (-> hands-descriptions
      hands/create
      ranking/result
      results/message))

(ns poker-hands.core
  (:require [poker-hands.hands :as hands])
  (:require [poker-hands.ranking :as ranking])
  (:require [poker-hands.results :as results]))

(defn- compare-hands [[hand1 hand2]]
  (ranking/compare-hands hand1 hand2))

(defn compare-players-hands [& hands-descriptions]
  (-> hands-descriptions
      hands/create
      compare-hands
      results/message))

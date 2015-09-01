(ns poker-hands.tie-breaker
  (:require [poker-hands.cards :refer [compute-value]])
  (:require [poker-hands.hands]))

(defn untie [hand1 hand2]
  (let
    [first-different-values
     (map compute-value
          (first
            (drop-while #(= (first %) (second %))
                        (map vector
                             (:cards hand1)
                             (:cards hand2)))))]
    (if (empty? first-different-values)
      nil
      (if (> (first first-different-values) (second first-different-values))
        hand1
        hand2))))
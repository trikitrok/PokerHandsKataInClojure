(ns poker-hands.examples)

(defn black-player-with [hand]
  (merge {:player :black} hand))

(defn white-player-with [hand]
  (merge {:player :white} hand))

(def straight-flush-with-Q {:type :straight-flush :highest-card "Q"})
(def straight-flush-with-K {:type :straight-flush :highest-card "K"})
(def straight-flush-with-A {:type :straight-flush :highest-card "A"})
(def straight-flush-with-6 {:type :straight-flush :highest-card "6"})

(def four-kind-of-5 {:type :four-kind :four-kind-card ["5"] :no-four-card ["Q"]})
(def four-kind-of-6 {:type :four-kind :four-kind-card ["6"] :no-four-card ["10"]})
(def four-kind-of-4-with-kicking-Q {:type :four-kind :four-kind-card ["4"] :no-four-card ["Q"]})
(def four-kind-of-4-with-kicking-K {:type :four-kind :four-kind-card ["4"] :no-four-card ["K"]})

(def high-card-with-K {:type :high-card :highest-card "K"})

(def pair-of-2 {:type :pair :pair-card ["2"] :no-pair-cards ["Q" "8" "5"]})

(def two-pairs-of-5-2 {:type :two-pairs :pair-cards ["5" "2"] :no-pair-cards ["Q"]})

(def triplet-of-5 {:type :triplet :triplet-card ["5"] :no-triplet-cards ["Q" "2"]})

(def straight-with-Q {:type :straight :highest-card "Q"})

(def flush-with-A {:type :flush :highest-card "A"})

(def full-house-of-5-2 {:type :full-house :triplet-card ["5"] :pair-card ["2"]})
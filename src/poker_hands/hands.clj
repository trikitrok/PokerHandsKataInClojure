(ns poker-hands.hands
  (:require [poker-hands.cards :as cards]))

(defn- highest-cards [hand]
  (sort #(> (:value %1) (:value %2)) hand))

(defn- highest-card [hand]
  (first (highest-cards hand)))

(defn- second-highest-card [hand]
  (second (highest-cards hand)))

(defn- a-high-card [hand]
  {:hand-type    :high-card
   :highest-card (:face (highest-card hand))})

(defn- a-flush [hand]
  {:hand-type    :flush
   :highest-card (:face (highest-card hand))})

(defn- flush? [hand]
  (apply = (map :suit hand)))

(def ^:private pluck-value (partial map :value))

(defn- make-group-selection-pred [compare-fn group-size]
  (comp (partial compare-fn group-size) second))

(def ^:private pairs-pred
  (make-group-selection-pred = 2))

(def ^:private no-pairs-pred
  (make-group-selection-pred not= 2))

(def ^:private face-pairs
  (partial cards/faces-subset pairs-pred))

(defn- pair? [hand]
  (= 1 (count (face-pairs hand))))

(def ^:private no-pair-cards
  (partial cards/subset no-pairs-pred))

(def ^:private pair-cards
  (partial cards/subset pairs-pred))

(defn- a-pair [hand]
  {:hand-type     :pair
   :pair-card     (pair-cards hand)
   :no-pair-cards (no-pair-cards hand)})

(defn- two-pairs? [hand]
  (= 2 (count (face-pairs hand))))

(defn- a-two-pairs [hand]
  {:hand-type     :two-pairs
   :pair-cards    (pair-cards hand)
   :no-pair-cards (no-pair-cards hand)})

(def ^:private triplets-pred
  (make-group-selection-pred = 3))

(def ^:private no-triplets-pred
  (make-group-selection-pred not= 3))

(def ^:private face-triplets
  (partial cards/faces-subset triplets-pred))

(defn- triplet? [hand]
  (= 1 (count (face-triplets hand))))

(def ^:private no-triplet-cards
  (partial cards/subset no-triplets-pred))

(def ^:private triplet-cards
  (partial cards/subset triplets-pred))

(defn- a-triplet [hand]
  {:hand-type        :triplet
   :triplet-card     (triplet-cards hand)
   :no-triplet-cards (no-triplet-cards hand)})

(defn- consecutives? [sorted-values]
  (every? #(= 1 %)
          (map #(- (second %) (first %))
               (partition 2 1 sorted-values))))

(defn- sorted-values [hand]
  (->> hand
       pluck-value
       sort))

(defn- wheel? [hand]
  (= (sorted-values hand) [0 1 2 3 12]))

(defn- straight? [hand]
  (or (wheel? hand)
      (consecutives? (sorted-values hand))))

(defn- a-straight [hand]
  {:hand-type :straight
   :highest-card
              (:face (if (wheel? hand)
                       (second-highest-card hand)
                       (highest-card hand)))})

(defn- a-straight-flush [hand]
  (assoc
    (a-straight hand)
    :hand-type :straight-flush))

(defn- a-full-house [hand]
  {:hand-type    :full-house
   :triplet-card (triplet-cards hand)
   :pair-card    (pair-cards hand)})

(def ^:private four-kind-pred
  (make-group-selection-pred = 4))

(def ^:private no-four-kind-pred
  (make-group-selection-pred not= 4))

(def ^:private face-four-kinds
  (partial cards/faces-subset four-kind-pred))

(defn- four-kind? [hand]
  (= 1 (count (face-four-kinds hand))))

(def ^:private no-four-kind-cards
  (partial cards/subset no-four-kind-pred))

(def ^:private four-kind-cards
  (partial cards/subset four-kind-pred))

(defn- a-four-kind [hand]
  {:hand-type      :four-kind
   :four-kind-card (four-kind-cards hand)
   :no-four-card   (no-four-kind-cards hand)})

(defn- create-hand [hand-description]
  (-> hand-description
      cards/split-in-card-descriptions
      cards/create-cards))

(defn- classify [hand]
  (cond
    (and (flush? hand) (not (straight? hand))) (a-flush hand)
    (and (pair? hand) (not (triplet? hand))) (a-pair hand)
    (two-pairs? hand) (a-two-pairs hand)
    (and (triplet? hand) (not (pair? hand))) (a-triplet hand)
    (and (straight? hand) (not (flush? hand))) (a-straight hand)
    (and (straight? hand) (flush? hand)) (a-straight-flush hand)
    (and (triplet? hand) (pair? hand)) (a-full-house hand)
    (four-kind? hand) (a-four-kind hand)
    :else (a-high-card hand)))

(defn hand [hand-description]
  (-> hand-description
      create-hand
      classify))

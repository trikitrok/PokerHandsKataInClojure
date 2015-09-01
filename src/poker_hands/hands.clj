(ns poker-hands.hands
  (:require [poker-hands.cards :as cards]))

(def ^:private sort-by-greater-face-value
  (partial sort cards/by-greater-face-value))

(def ^:private pluck-faces (partial map :face))

(defn- faces-subset [group-selection-pred hand]
  (->> hand
       pluck-faces
       frequencies
       (filter group-selection-pred)))

(defn- subset [group-selection-pred hand]
  (sort-by-greater-face-value
    (map first (faces-subset group-selection-pred hand))))

(defn- highest-cards [hand]
  (sort #(> (:value %1) (:value %2)) hand))

(defn- highest-card [hand]
  (first (highest-cards hand)))

(defn- second-highest-card [hand]
  (second (highest-cards hand)))

(defn- a-high-card [hand]
  {:type         :high-card
   :highest-card (:face (highest-card hand))})

(defrecord Flush [cards])

(defn- a-flush [hand]
  (Flush. (pluck-faces hand)))

(defn- flush? [hand]
  (apply = (map :suit hand)))

(def ^:private pluck-values (partial map :value))

(defn- make-group-selection-pred [compare-fn group-size]
  (comp (partial compare-fn group-size) second))

(def ^:private pairs-pred
  (make-group-selection-pred = 2))

(def ^:private no-pairs-pred
  (make-group-selection-pred not= 2))

(def ^:private face-pairs
  (partial faces-subset pairs-pred))

(defn- pair? [hand]
  (= 1 (count (face-pairs hand))))

(def ^:private no-pair-cards
  (partial subset no-pairs-pred))

(def ^:private pair-cards
  (partial subset pairs-pred))

(defn- a-pair [hand]
  {:type          :pair
   :pair-card     (pair-cards hand)
   :no-pair-cards (no-pair-cards hand)})

(defn- two-pairs? [hand]
  (= 2 (count (face-pairs hand))))

(defn- a-two-pairs [hand]
  {:type          :two-pairs
   :pair-cards    (pair-cards hand)
   :no-pair-cards (no-pair-cards hand)})

(def ^:private triplets-pred
  (make-group-selection-pred = 3))

(def ^:private no-triplets-pred
  (make-group-selection-pred not= 3))

(def ^:private face-triplets
  (partial faces-subset triplets-pred))

(defn- triplet? [hand]
  (= 1 (count (face-triplets hand))))

(def ^:private no-triplet-cards
  (partial subset no-triplets-pred))

(def ^:private triplet-cards
  (partial subset triplets-pred))

(defn- a-triplet [hand]
  {:type             :triplet
   :triplet-card     (triplet-cards hand)
   :no-triplet-cards (no-triplet-cards hand)})

(defn- consecutives? [sorted-values]
  (every? #(= 1 %)
          (map #(- (second %) (first %))
               (partition 2 1 sorted-values))))

(defn- sorted-values [hand]
  (->> hand
       pluck-values
       sort))

(defn- wheel? [hand]
  (= (sorted-values hand) [0 1 2 3 12]))

(defn- straight? [hand]
  (or (wheel? hand)
      (consecutives? (sorted-values hand))))

(defn- straight-highest-card [hand]
  (:face (if (wheel? hand)
           (second-highest-card hand)
           (highest-card hand))))

(defn- a-straight [hand]
  {:type :straight
   :highest-card (straight-highest-card hand)})

(defrecord StraightFlush [highest-card])

(defn- a-straight-flush [hand]
  (StraightFlush. (straight-highest-card hand)))

(defrecord FullHouse [triplet-card pair-card player])

(defn- a-full-house [hand]
  (FullHouse.
    (triplet-cards hand)
    (pair-cards hand)
    (:player hand)))

(def ^:private four-kind-pred
  (make-group-selection-pred = 4))

(def ^:private no-four-kind-pred
  (make-group-selection-pred not= 4))

(def ^:private face-four-kinds
  (partial faces-subset four-kind-pred))

(defn- four-kind? [hand]
  (= 1 (count (face-four-kinds hand))))

(def ^:private no-four-kind-cards
  (partial subset no-four-kind-pred))

(def ^:private four-kind-cards
  (partial subset four-kind-pred))

(defrecord FourKind [four-kind-card no-four-card player])

(defn- a-four-kind [hand]
  (FourKind.
    (four-kind-cards hand)
    (no-four-kind-cards hand)
    (:player hand)))

(defn- create-hand [hand-description]
  (-> hand-description
      cards/split-in-card-descriptions
      cards/create-cards))

(defn- classify [hand]
  (cond
    (and (flush? hand)
         (not (straight? hand))) (a-flush hand)

    (and (pair? hand)
         (not (triplet? hand))) (a-pair hand)

    (two-pairs? hand) (a-two-pairs hand)

    (and (triplet? hand)
         (not (pair? hand))) (a-triplet hand)

    (and (straight? hand)
         (not (flush? hand))) (a-straight hand)

    (and (straight? hand)
         (flush? hand)) (a-straight-flush hand)

    (and (triplet? hand)
         (pair? hand)) (a-full-house hand)

    (four-kind? hand) (a-four-kind hand)

    :else (a-high-card hand)))

(defn hand [hand-description]
  (-> hand-description
      create-hand
      classify))
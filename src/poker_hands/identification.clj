(ns poker-hands.identification
  (:require [poker-hands.cards]))

(def ^:private pluck-faces (partial map :face))

(defn- faces-subset [group-selection-pred hand]
  (->> hand
       pluck-faces
       frequencies
       (filter group-selection-pred)))

(defn- groups-of [number size cards]
  (= number (count (faces-subset (comp (partial = size) second) cards))))

(def ^:private four-kind? (partial groups-of 1 4))

(def ^:private pair? (partial groups-of 1 2))

(def ^:private triplet? (partial groups-of 1 3))

(def ^:private two-pairs? (partial groups-of 2 2))

(defn- flush? [hand]
  (apply = (map :suit hand)))

(def ^:private pluck-values (partial map :value))

(defn- sorted-values [hand]
  (->> hand
       pluck-values
       sort))

(defn- wheel? [hand]
  (= (sorted-values hand) [0 1 2 3 12]))

(defn- consecutives? [sorted-values]
  (every? #(= 1 %)
          (map #(- (second %) (first %))
               (partition 2 1 sorted-values))))

(defn- straight? [hand]
  (or (wheel? hand)
      (consecutives? (sorted-values hand))))

(defn identify [cards]
  (cond
    (and (flush? cards)
         (not (straight? cards))) {:type :flush :cards cards}

    (and (pair? cards)
         (not (triplet? cards))) {:type :pair :cards cards}

    (two-pairs? cards) {:type :two-pairs :cards cards}

    (and (triplet? cards)
         (not (pair? cards))) {:type :triplet :cards cards}

    (and (straight? cards)
         (not (flush? cards))) {:type :straight :cards cards}

    (and (straight? cards)
         (flush? cards)) {:type :straight-flush :cards cards}

    (and (triplet? cards)
         (pair? cards)) {:type :full-house :cards cards}

    (four-kind? cards) {:type :four-kind :cards cards}

    :else {:type :high-card :cards cards}))

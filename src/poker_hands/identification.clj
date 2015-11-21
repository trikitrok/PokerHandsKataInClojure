(ns poker-hands.identification
  (:require [poker-hands.cards :as cards]))

(def ^:private four-kind? (partial cards/groups-of? 1 4))

(def ^:private pair? (partial cards/groups-of? 1 2))

(def ^:private triplet? (partial cards/groups-of? 1 3))

(def ^:private two-pairs? (partial cards/groups-of? 2 2))

(defn- flush? [hand]
  (apply = (cards/suits hand)))

(defn- straight? [hand]
  (or (cards/wheel? hand)
      (cards/consecutive? hand)))

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

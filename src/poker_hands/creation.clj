(ns poker-hands.creation
  (:require [poker-hands.cards :as cards])
  (:require [poker-hands.identification :as identification]))

(defrecord HighCard [cards])
(defrecord Flush [cards])
(defrecord Pair [cards])
(defrecord TwoPairs [cards])
(defrecord Triplet [cards])
(defrecord FourKind [cards])
(defrecord FullHouse [cards])
(defrecord Straight [cards])
(defrecord StraightFlush [cards])

(defn- a-high-card [cards]
  (HighCard.
    (cards/sorted-faces cards)))

(defn- a-flush [cards]
  (Flush. (cards/sorted-faces cards)))

(defn- a-pair [cards]
  (Pair. (cards/concat-cards-of-groups-with-and-without 2 cards)))

(defn- a-triplet [cards]
  (Triplet. (cards/concat-cards-of-groups-with-and-without 3 cards)))

(defn- a-two-pairs [cards]
  (TwoPairs.
    (cards/concat-cards-of-groups-with-and-without 2 cards)))

(defn- straight-highest-card [cards]
  (:face (if (identification/wheel? cards)
           (second (cards/highest-cards cards))
           (first (cards/highest-cards cards)))))

(defn- a-straight [cards]
  (Straight. [(straight-highest-card cards)]))

(defn- a-straight-flush [cards]
  (StraightFlush. [(straight-highest-card cards)]))

(defn- a-full-house [cards]
  (FullHouse. (cards/concat-cards-of-groups-with-and-without 3 cards)))

(defn- a-four-kind [cards]
  (FourKind. (cards/concat-cards-of-groups-with-and-without 4 cards)))

(def ^:private factories-by-type
  {:high-card a-high-card
   :flush a-flush
   :pair a-pair
   :triplet a-triplet
   :two-pairs a-two-pairs
   :straight a-straight
   :straight-flush a-straight-flush
   :full-house a-full-house
   :four-kind a-four-kind})

(defn create-hand [{cards-type :type cards :cards}]
  (let [factory (get factories-by-type cards-type)]
    (factory cards)))

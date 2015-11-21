(ns poker-hands.creation
  (:require [poker-hands.cards :as cards])
  (:require [poker-hands.hand-types :as hand-types]))

(defn- a-high-card [cards]
  (hand-types/->HighCard
    (cards/sorted-faces cards)))

(defn- a-flush [cards]
  (hand-types/->Flush
    (cards/sorted-faces cards)))

(defn- a-pair [cards]
  (hand-types/->Pair
    (cards/concat-cards-of-groups-with-and-without 2 cards)))

(defn- a-triplet [cards]
  (hand-types/->Triplet
    (cards/concat-cards-of-groups-with-and-without 3 cards)))

(defn- a-two-pairs [cards]
  (hand-types/->TwoPairs
    (cards/concat-cards-of-groups-with-and-without 2 cards)))

(defn- a-straight [cards]
  (hand-types/->Straight
    [(cards/straight-highest-card-face cards)]))

(defn- a-straight-flush [cards]
  (hand-types/->StraightFlush
    [(cards/straight-highest-card-face cards)]))

(defn- a-full-house [cards]
  (hand-types/->FullHouse
    (cards/concat-cards-of-groups-with-and-without 3 cards)))

(defn- a-four-kind [cards]
  (hand-types/->FourKind
    (cards/concat-cards-of-groups-with-and-without 4 cards)))

(def ^:private factories-by-type
  {:high-card      a-high-card
   :flush          a-flush
   :pair           a-pair
   :triplet        a-triplet
   :two-pairs      a-two-pairs
   :straight       a-straight
   :straight-flush a-straight-flush
   :full-house     a-full-house
   :four-kind      a-four-kind})

(defn create-hand [{cards-type :type cards :cards}]
  (let [factory (get factories-by-type cards-type)]
    (factory cards)))

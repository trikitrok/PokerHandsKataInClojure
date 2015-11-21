(ns poker-hands.creation
  (:require [poker-hands.cards :as cards])
  (:require [poker-hands.hand-types :as hand-types]))

(defn- a-high-card-hand [cards]
  (hand-types/->HighCard
    (cards/sorted-faces cards)))

(defn- a-flush-hand [cards]
  (hand-types/->Flush
    (cards/sorted-faces cards)))

(defn- a-pair-hand [cards]
  (hand-types/->Pair
    (cards/concat-cards-of-groups-with-and-without 2 cards)))

(defn- a-triplet-hand [cards]
  (hand-types/->Triplet
    (cards/concat-cards-of-groups-with-and-without 3 cards)))

(defn- a-two-pairs-hand [cards]
  (hand-types/->TwoPairs
    (cards/concat-cards-of-groups-with-and-without 2 cards)))

(defn- a-straight-hand [cards]
  (hand-types/->Straight
    [(cards/straight-highest-card-face cards)]))

(defn- a-straight-flush-hand [cards]
  (hand-types/->StraightFlush
    [(cards/straight-highest-card-face cards)]))

(defn- a-full-house-hand [cards]
  (hand-types/->FullHouse
    (cards/concat-cards-of-groups-with-and-without 3 cards)))

(defn- a-four-kind-hand [cards]
  (hand-types/->FourKind
    (cards/concat-cards-of-groups-with-and-without 4 cards)))

(def ^:private factories-by-type
  {:high-card      a-high-card-hand
   :flush          a-flush-hand
   :pair           a-pair-hand
   :triplet        a-triplet-hand
   :two-pairs      a-two-pairs-hand
   :straight       a-straight-hand
   :straight-flush a-straight-flush-hand
   :full-house     a-full-house-hand
   :four-kind      a-four-kind-hand})

(defn create [{hand-type :type cards :cards}]
  (let [factory (get factories-by-type hand-type)]
    (factory cards)))

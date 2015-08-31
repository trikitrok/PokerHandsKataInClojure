(ns poker-hands.result-messages
  (:import (poker_hands.hands StraightFlush FourKind FullHouse)))

(def ^:private four-kind-card (comp first :four-kind-card))

(def ^:private full-house-triplet-card (comp first :triplet-card))

(def ^:private full-house-pair-card (comp first :pair-card))

(defmulti victory-message class)

(defmethod ^:private victory-message StraightFlush [_]
  "with a straight flush")

(defmethod ^:private victory-message FourKind [hand]
  (str "with a four of " (four-kind-card hand)))

(defmethod ^:private victory-message FullHouse [hand]
  (str "with a full house of three " (full-house-triplet-card hand)
       " and two " (full-house-pair-card hand)))

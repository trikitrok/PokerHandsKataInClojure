(ns poker-hands.result-messages
  (:import (poker_hands.hands StraightFlush FourKind FullHouse Flush)))

(def ^:private four-kind-card (comp first :four-kind-card))

(def ^:private full-house-triplet-card (comp first :cards))

(def ^:private flush-cards (comp (partial clojure.string/join " ") :cards))

(def ^:private full-house-pair-card (comp second :cards))

(defmulti victory-message class)

(defmethod ^:private victory-message StraightFlush [_]
  "with a straight flush")

(defmethod ^:private victory-message FourKind [hand]
  (str "with a four of " (four-kind-card hand)))

(defmethod ^:private victory-message FullHouse [hand]
  (str "with a full house of three " (full-house-triplet-card hand)
       " and two " (full-house-pair-card hand)))

(defmethod ^:private victory-message Flush [hand]
  (str "with a flush of " (flush-cards hand)))

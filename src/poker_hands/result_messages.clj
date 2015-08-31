(ns poker-hands.result-messages
  (:import (poker_hands.hands StraightFlush FourKind)))

(def ^:private four-kind-card (comp first :four-kind-card))

(defmulti victory-message class)

(defmethod ^:private victory-message StraightFlush [_]
  "with a straight flush")

(defmethod ^:private victory-message FourKind [hand]
  (str "with a four of " (four-kind-card hand)))

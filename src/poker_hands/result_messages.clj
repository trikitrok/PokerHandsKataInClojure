(ns poker-hands.result-messages
  (:import (poker_hands.hands StraightFlush FourKind FullHouse Flush Straight)))

(defmulti victory-message class)

(defmethod ^:private victory-message StraightFlush [_]
  "with a straight flush")

(defmethod ^:private victory-message FourKind [hand]
  (str "with a four of " (first (:cards hand))))

(defmethod ^:private victory-message FullHouse [hand]
  (str "with a full house of three " (first (:cards hand))
       " and two " (second (:cards hand))))

(defmethod ^:private victory-message Flush [hand]
  (str "with a flush of " (clojure.string/join " " (:cards hand))))

(defmethod ^:private victory-message Straight [hand]
  (str "with a straight of " (first (:cards hand))))

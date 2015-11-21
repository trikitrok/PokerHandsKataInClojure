(ns poker-hands.hand-types)

(defrecord HighCard [faces])
(defrecord Flush [faces])
(defrecord Pair [faces])
(defrecord TwoPairs [faces])
(defrecord Triplet [faces])
(defrecord FourKind [faces])
(defrecord FullHouse [faces])
(defrecord Straight [faces])
(defrecord StraightFlush [faces])

(def faces :faces)

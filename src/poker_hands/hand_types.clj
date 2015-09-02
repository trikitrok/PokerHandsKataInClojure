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

(defn ranking [hand]
  (let [hands-ranking [HighCard Pair TwoPairs Triplet Straight Flush FullHouse FourKind StraightFlush]]
    (.indexOf hands-ranking (class hand))))

(def faces :faces)

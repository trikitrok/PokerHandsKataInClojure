(ns poker-hands.core)

(defn- compute-value [card-description]
  (let [face-values [\2 \3 \4 \5 \6 \7 \8 \9 \J \K \Q \A]]
    (.indexOf face-values (first card-description))))

(defn- by-greater-value [card-description1 card-description2]
  (> (compute-value card-description1)
     (compute-value card-description2)))

(defn- split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(defn- card [card-description]
  {:face  (str (first card-description))
   :value (compute-value card-description)
   :suit  (str (second card-description))})

(def ^:private create-cards (partial map card))

(defn- highest-card [hand]
  (apply max-key :value hand))

(defn- a-high-card [hand]
  {:hand-type    :high-card
   :highest-card (:face (highest-card hand))})

(defn- a-flush [hand]
  {:hand-type    :flush
   :highest-card (:face (highest-card hand))})

(defn- flush? [hand]
  (apply = (map :suit hand)))

(def ^:private pluck-face (partial map :face))

(defn- make-group-selection-pred [compare-fn group-size]
  (comp (partial compare-fn group-size) second))

(def ^:private pairs-pred
  (make-group-selection-pred = 2))

(def ^:private no-pairs-pred
  (make-group-selection-pred not= 2))

(defn- faces-groups [group-selection-pred hand]
  (->> hand
       pluck-face
       frequencies
       (filter group-selection-pred)))

(defn- group-cards [group-selection-pred hand]
  (sort by-greater-value
        (map first
             (faces-groups group-selection-pred hand))))

(def ^:private face-pairs
  (partial faces-groups pairs-pred))

(defn- pair? [hand]
  (= 1 (count (face-pairs hand))))

(def ^:private no-pair-cards
  (partial group-cards no-pairs-pred))

(def ^:private pair-cards
  (partial group-cards pairs-pred))

(defn- a-pair [hand]
  {:hand-type     :pair
   :pair-card     (pair-cards hand)
   :no-pair-cards (no-pair-cards hand)})

(defn- two-pairs? [hand]
  (= 2 (count (face-pairs hand))))

(defn- a-two-pairs [hand]
  {:hand-type     :two-pairs
   :pair-cards    (pair-cards hand)
   :no-pair-cards (no-pair-cards hand)})

(def ^:private triplets-pred
  (make-group-selection-pred = 3))

(def ^:private no-triplets-pred
  (make-group-selection-pred not= 3))

(def face-triplets (partial faces-groups triplets-pred))

(defn- triplet? [hand]
  (= 1 (count (face-triplets hand))))

(def ^:private no-triplet-cards
  (partial group-cards no-triplets-pred))

(def ^:private triplet-cards
  (partial group-cards triplets-pred))

(defn- a-triplet [hand]
  {:hand-type :triplet
   :triplet-card (triplet-cards hand)
   :no-triplet-cards (no-triplet-cards hand)})

(defn- categorize [hand]
  (cond
    (flush? hand) (a-flush hand)
    (pair? hand) (a-pair hand)
    (two-pairs? hand) (a-two-pairs hand)
    (triplet? hand) (a-triplet hand)
    :else (a-high-card hand)))

(defn hand [hand-description]
  (-> hand-description
      split-in-card-descriptions
      create-cards
      categorize))

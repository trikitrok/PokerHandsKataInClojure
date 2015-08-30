(ns poker-hands.core)

(defn- compute-value [card-description]
  (let [face-values [\2 \3 \4 \5 \6 \7 \8 \9 \J \K \Q \A]]
    (.indexOf face-values (first card-description))))

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
  { :hand-type    :flush
  :highest-card (:face (highest-card hand))})

(defn- flush-scoring []
  "flush")

(defn- flush? [hand]
  (apply = (map :suit hand)))

(def ^:private pluck-face (partial map :face))

(defn- face-pairs [hand]
  (filter #(= 2 (second %)) (frequencies (pluck-face hand))))

(defn- pair? [hand]
  (= 1 (count (face-pairs hand))))

(def pair-face first)

(defn- pair-scoring [hand]
  (str "pair of " (pair-face (first (face-pairs hand)))))

(defn- two-pairs? [hand]
  (= 2 (count (face-pairs hand))))

(defn- two-pair-scoring [hand]
  (let [pairs (face-pairs hand)]
    (str "two pairs of " (pair-face (first pairs))
         " and " (pair-face (second pairs)))))

(defn- categorize [hand]
  (cond
    (flush? hand) (a-flush hand)
    :else (a-high-card hand)))

(defn hand [hand-description]
  (-> hand-description
      split-in-card-descriptions
      create-cards))

(defn hand-type [hand-description]
  (-> hand-description
      split-in-card-descriptions
      create-cards
      categorize))

(defn score [hand]
  (cond
    (flush? hand) (flush-scoring)
    (pair? hand) (pair-scoring hand)
    (two-pairs? hand) (two-pair-scoring hand)
    :else (categorize hand)))

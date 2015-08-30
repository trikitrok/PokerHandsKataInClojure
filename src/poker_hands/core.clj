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
  {:hand-type    :flush
   :highest-card (:face (highest-card hand))})

(defn- flush? [hand]
  (apply = (map :suit hand)))

(def ^:private pluck-face (partial map :face))

(defn- face-pairs [hand]
  (filter #(= 2 (second %)) (frequencies (pluck-face hand))))

(defn- face-no-pairs [hand]
  (filter #(= 1 (second %)) (frequencies (pluck-face hand))))

(defn- pair? [hand]
  (= 1 (count (face-pairs hand))))

(defn- no-pair-cards [hand]
  (sort #(> (compute-value %1) (compute-value %2))
        (map first (face-no-pairs hand))))

(defn- pair-cards [hand]
  (sort #(> (compute-value %1) (compute-value %2))
        (map first (face-pairs hand))))

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

(defn- categorize [hand]
  (cond
    (flush? hand) (a-flush hand)
    (pair? hand) (a-pair hand)
    (two-pairs? hand) (a-two-pairs hand)
    :else (a-high-card hand)))

(defn hand [hand-description]
  (-> hand-description
      split-in-card-descriptions
      create-cards
      categorize))

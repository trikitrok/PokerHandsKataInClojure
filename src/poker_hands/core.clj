(ns poker-hands.core)

(defn- compute-value [card-description]
  (let [face-values [\1 \2 \3 \4 \5 \6 \7 \8 \9 \J \K \Q]]
    (.indexOf face-values (first card-description))))

(defn- split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(defn- card [card-description]
  {:face (str (first card-description))
  :value (compute-value card-description)
  :suit (str (second card-description))})

(def ^:private create-cards (partial map card))

(defn- highest-card [hand]
  (apply max-key :value hand))

(defn- high-card-scoring [hand]
  (str "high card: "
       (:face (highest-card hand))))

(defn- flush-scoring []
  "flush")

(defn- flush? [hand]
  (apply = (map :suit hand)))

(defn- pair? [hand]
  (= 1 (count (filter #(> % 1) (vals (frequencies (map :face hand)))))))

(defn- pair-scoring [hand]
  (let [[pair-face _] (first (filter #(= 2 (second %)) (frequencies (map :face hand))))]
    (str "pair of " pair-face))
  )

(defn hand [hand-description]
  (-> hand-description
      split-in-card-descriptions
      create-cards))

(defn score [hand]
  (cond
    (flush? hand) (flush-scoring)
    (pair? hand) (pair-scoring hand)
    :else (high-card-scoring hand)))

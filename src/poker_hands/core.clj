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

(defn highest-card [hand]
  (apply max-key :value hand))

(defn hand [hand-description]
  (-> hand-description
      split-in-card-descriptions
      create-cards))

(defn score [hand]
  (str "high card: "
       (:face (highest-card hand))))

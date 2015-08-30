(ns poker-hands.cards)

(defn- face [card-description]
  (if (= 2 (count card-description))
    (str (first card-description))
    (str (first card-description)
         (second card-description))))

(defn- compute-value [card-description]
  (let [face-values ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"]]
    (.indexOf face-values (face card-description))))

(defn- by-greater-described-value [card-description1 card-description2]
  (> (compute-value card-description1)
     (compute-value card-description2)))

(defn- suit [card-description]
  (if (= 2 (count card-description))
    (str (second card-description))
    (str (nth card-description 2))))

(defn- card [card-description]
  {:face  (face card-description)
   :value (compute-value card-description)
   :suit  (suit card-description)})

(def ^:private sort-by-greater-value
  (partial sort by-greater-described-value))

(def ^:private pluck-face (partial map :face))

(defn faces-subset [group-selection-pred hand]
  (->> hand
       pluck-face
       frequencies
       (filter group-selection-pred)))

(defn subset [group-selection-pred hand]
  (sort-by-greater-value
    (map first (faces-subset group-selection-pred hand))))

(defn split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(def create-cards (partial map card))

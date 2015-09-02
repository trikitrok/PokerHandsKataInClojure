(ns poker-hands.cards)

(defn- face [card-description]
  (if (= 2 (count card-description))
    (str (first card-description))
    (str (first card-description) (second card-description))))

(defn face-rank [face-description]
  (let [face-ranks ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"]]
    (.indexOf face-ranks face-description)))

(defn- suit [card-description]
  (if (= 2 (count card-description))
    (str (second card-description))
    (str (nth card-description 2))))

(defn- card [card-description]
  (let [face (face card-description)]
    {:face  face
     :rank (face-rank face)
     :suit  (suit card-description)}))

(defn- split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(defn- by-greater-face-rank [face-description1 face-description2]
  (> (face-rank face-description1)
     (face-rank face-description2)))

(def ^:private sort-by-greater-face-rank
  (partial sort by-greater-face-rank))

(def ^:private pluck-faces (partial map :face))

(defn- faces-subset [group-selection-pred hand]
  (->> hand
       pluck-faces
       frequencies
       (filter group-selection-pred)))

(defn- subset [group-selection-pred cards]
  (sort-by-greater-face-rank
    (map first (faces-subset group-selection-pred cards))))

(defn sorted-ranks [cards]
  (->> cards
       (map :rank)
       sort))

(defn highest-cards [cards]
  (sort #(> (:rank %1) (:rank %2)) cards))

(defn groups-of? [number size cards]
  (= number (count (faces-subset (comp (partial = size) second) cards))))

(def sorted-faces (comp sort-by-greater-face-rank pluck-faces))

(defn concat-cards-of-groups-with-and-without [size cards]
  (concat
    (subset (comp (partial = size) second) cards)
    (subset (comp (partial not= size) second) cards)))

(defn create-cards [hand-description]
  (->> hand-description
       split-in-card-descriptions
       (map card)))

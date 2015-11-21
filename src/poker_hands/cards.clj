(ns poker-hands.cards)

(defn- card-face [card-description]
  (if (= 2 (count card-description))
    (str (first card-description))
    (str (first card-description) (second card-description))))

(defn face-rank [face-description]
  (let [face-ranks ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"]]
    (.indexOf face-ranks face-description)))

(defn- card-suit [card-description]
  (if (= 2 (count card-description))
    (str (second card-description))
    (str (nth card-description 2))))

(defn- card [card-description]
  (let [face (card-face card-description)]
    {:face face
     :rank (face-rank face)
     :suit (card-suit card-description)}))

(def face :face)
(def rank :rank)
(def suit :suit)

(defn- split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(defn- by-greater-face-rank [face-description1 face-description2]
  (> (face-rank face-description1)
     (face-rank face-description2)))

(def ^:private sort-by-greater-face-rank
  (partial sort by-greater-face-rank))

(def ^:private pluck-faces (partial map face))

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
       (map rank)
       sort))

(def highest-cards (partial sort-by rank >))

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

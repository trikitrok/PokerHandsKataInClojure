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

(def suits (partial map suit))

(defn- split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(defn- by-greater-face-rank [face-description1 face-description2]
  (> (face-rank face-description1)
     (face-rank face-description2)))

(def ^:private sort-by-greater-face-rank
  (partial sort by-greater-face-rank))

(def ^:private faces (partial map face))

(defn- faces-subset [group-selection-pred hand]
  (->> hand
       faces
       frequencies
       (filter group-selection-pred)))

(defn- subset [group-selection-pred cards]
  (sort-by-greater-face-rank
    (map first (faces-subset group-selection-pred cards))))

(def ^:private highest-cards (partial sort-by rank >))

(defn- sorted-ranks [cards]
  (->> cards
       (sort-by rank)
       (map rank)))

(defn wheel? [hand]
  (= (sorted-ranks hand) [0 1 2 3 12]))

(defn consecutive? [cards]
  (every? #(= 1 %)
          (map #(- (second %) (first %))
               (partition 2 1 (sorted-ranks cards)))))

(defn straight-highest-card-face [cards]
  (face (if (wheel? cards)
          (second (highest-cards cards))
          (first (highest-cards cards)))))

(defn groups-of? [number size cards]
  (= number (count (faces-subset (comp (partial = size) second) cards))))

(def sorted-faces (comp sort-by-greater-face-rank faces))

(defn concat-cards-of-groups-with-and-without [size cards]
  (concat
    (subset (comp (partial = size) second) cards)
    (subset (comp (partial not= size) second) cards)))

(defn create-cards [hand-description]
  (->> hand-description
       split-in-card-descriptions
       (map card)))

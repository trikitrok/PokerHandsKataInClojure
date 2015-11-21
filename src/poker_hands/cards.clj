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
     :suit (card-suit card-description)}))

(def ^:private face :face)
(def ^:private rank (comp face-rank face))
(def ^:private suit :suit)

(def suits (partial map suit))
(def ^:private faces (partial map face))
(def ^:private ranks (partial map rank))

(defn- split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(def ^:private sort-by-highest-rank
  (partial sort-by face-rank >))

(defn- faces-frecuencies-subset [selection-pred cards]
  (->> cards
       faces
       frequencies
       (filter selection-pred)))

(defn- subset [selection-pred cards]
  (sort-by-highest-rank
    (map first (faces-frecuencies-subset selection-pred cards))))

(def ^:private highest-cards (partial sort-by rank >))

(def ^:private sorted-ranks (comp sort ranks))

(def sorted-faces (comp sort-by-highest-rank faces))

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
  (= number (count (faces-frecuencies-subset (comp (partial = size) second) cards))))

(defn concat-cards-of-groups-with-and-without [size cards]
  (concat
    (subset (comp (partial = size) second) cards)
    (subset (comp (partial not= size) second) cards)))

(defn create [hand-description]
  (->> hand-description
       split-in-card-descriptions
       (map card)))

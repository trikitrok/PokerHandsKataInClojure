(ns poker-hands.cards)

(defn- face [card-description]
  (if (= 2 (count card-description))
    (str (first card-description))
    (str (first card-description) (second card-description))))

(defn compute-value [face-description]
  (let [face-values ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"]]
    (.indexOf face-values face-description)))

(defn- suit [card-description]
  (if (= 2 (count card-description))
    (str (second card-description))
    (str (nth card-description 2))))

(defn- card [card-description]
  (let [face (face card-description)]
    {:face  face
     :value (compute-value face)
     :suit  (suit card-description)}))

(defn- split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(defn create-cards [hand-description]
  (->> hand-description
       split-in-card-descriptions
       (map card)))

(ns poker-hands.cards)

(defn- face [face-description]
  (if (= 2 (count face-description))
    (str (first face-description))
    (str (first face-description)
         (second face-description))))

(defn compute-value [face-description]
  (let [face-values ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"]]
    (.indexOf face-values (face face-description))))

(defn- suit [card-description]
  (if (= 2 (count card-description))
    (str (second card-description))
    (str (nth card-description 2))))

(defn- card [card-description]
  {:face  (face card-description)
   :value (compute-value card-description)
   :suit  (suit card-description)})

(defn by-greater-face-value [face-description1 face-description2]
  (> (compute-value face-description1)
     (compute-value face-description2)))

(defn split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(def create-cards (partial map card))

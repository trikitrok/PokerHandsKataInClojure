(ns poker-hands.core)

(def face-values [\1 \2 \3 \4 \5 \6 \7 \8 \9 \J \K \Q])

(defn hand [hand-description]
  (map #(hash-map :face (str (first %))
                  :value (.indexOf face-values (first %))
                  :suit (str (second %)))
       (clojure.string/split hand-description #" ")))

(defn score [hand]
  (str "high card: " (:face (apply max-key :value hand))))

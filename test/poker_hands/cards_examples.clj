(ns poker-hands.cards-examples)

(defn- identified-cards [cards-type cards]
  {:type  cards-type
   :cards cards})

(def identified-as-high-card-with
  (partial identified-cards :high-card))

(def identified-as-flush-with
  (partial identified-cards :flush))

(def identified-as-pair-with
  (partial identified-cards :pair))

(def identified-as-two-pairs-with
  (partial identified-cards :two-pairs))

(def identified-as-triplet-with
  (partial identified-cards :triplet))

(def identified-as-straight-with
  (partial identified-cards :straight))

(def identified-as-straight-flush-with
  (partial identified-cards :straight-flush))

(def identified-as-full-house-with
  (partial identified-cards :full-house))

(def identified-as-four-kind-with
  (partial identified-cards :four-kind))

(def higher-cards-7H-9D-5S-3C-2D
  [{:face "7" :suit "H" :rank 5} {:face "9" :suit "D" :rank 7}
   {:face "5" :suit "S" :rank 3} {:face "3" :suit "C" :rank 1}
   {:face "2" :suit "D" :rank 0}])

(def higher-cards-5H-7D-JS-9C-KD
  [{:face "5" :suit "H" :rank 3} {:face "7" :suit "D" :rank 5}
   {:face "J" :suit "S" :rank 9} {:face "9" :suit "C" :rank 7}
   {:face "2" :suit "K" :rank 11}])

(def flush-cards-KC-JC-9C-3C-2C
  [{:face "K" :suit "C" :rank 11} {:face "J" :suit "C" :rank 9}
   {:face "9" :suit "C" :rank 7} {:face "3" :suit "C" :rank 1}
   {:face "2" :suit "C" :rank 0}])

(def pair-card-with-2S-8D-5C-QS-2D
  [{:face "2" :suit "S" :rank 0} {:face "8" :suit "D" :rank 6}
   {:face "5" :suit "C" :rank 3} {:face "Q" :suit "S" :rank 10}
   {:face "2" :suit "D" :rank 0}])

(def two-pair-with-2S-5D-5C-QS-2D
  [{:face "2" :suit "S" :rank 0} {:face "5" :suit "D" :rank 3}
   {:face "5" :suit "C" :rank 3} {:face "Q" :suit "S" :rank 10}
   {:face "2" :suit "D" :rank 0}])

(def triplet-with-5S-5D-5C-QS-2D
  [{:face "5" :suit "S" :rank 3} {:face "5" :suit "D" :rank 3}
   {:face "5" :suit "C" :rank 3} {:face "Q" :suit "S" :rank 10}
   {:face "2" :suit "D" :rank 0}])

(def straight-with-QS-JD-10H-9S-8C
  [{:face "Q" :suit "S" :rank 10} {:face "J" :suit "D" :rank 9}
   {:face "10" :suit "H" :rank 8} {:face "9" :suit "S" :rank 7}
   {:face "8" :suit "C" :rank 6}])

(def straight-with-AS-KC-QH-JH-10D
  [{:face "A" :suit "S" :rank 12} {:face "K" :suit "C" :rank 11}
   {:face "Q" :suit "H" :rank 10} {:face "J" :suit "H" :rank 9}
   {:face "10" :suit "D" :rank 8}])

(def straight-wheel-5C-4H-3D-2C-AS
  [{:face "5" :suit "C" :rank 3} {:face "4" :suit "H" :rank 2}
   {:face "3" :suit "D" :rank 1} {:face "2" :suit "C" :rank 0}
   {:face "A" :suit "S" :rank -1}])

(def straight-flush-with-QS-JS-10S-9S-8S
  [{:face "Q" :suit "S" :rank 10} {:face "J" :suit "S" :rank 9}
   {:face "10" :suit "S" :rank 8} {:face "9" :suit "S" :rank 7}
   {:face "8" :suit "S" :rank 6}])

(def full-house-with-5S-2S-5D-2D-5C
  [{:face "5" :suit "S" :rank 3} {:face "2" :suit "S" :rank 0}
   {:face "5" :suit "D" :rank 3} {:face "2" :suit "D" :rank 0}
   {:face "5" :suit "C" :rank 3}])

(def four-of-kind-with-5S-5D-5C-QS-5H
  [{:face "5" :suit "S" :rank 3} {:face "5" :suit "D" :rank 3}
   {:face "5" :suit "C" :rank 3} {:face "Q" :suit "S" :rank 10}
   {:face "5" :suit "H" :rank 3}])

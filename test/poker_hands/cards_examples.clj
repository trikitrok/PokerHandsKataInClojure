(ns poker-hands.cards-examples)

(def higher-cards-7H-9D-5S-3C-2D
  [{:face "7" :suit "H" :value 5} {:face "9" :suit "D" :value 7}
   {:face "5" :suit "S" :value 3} {:face "3" :suit "C" :value 1}
   {:face "2" :suit "D" :value 0}])

(def higher-cards-5H-7D-JS-9C-KD
  [{:face "5" :suit "H" :value 3} {:face "7" :suit "D" :value 5}
   {:face "J" :suit "S" :value 9} {:face "9" :suit "C" :value 7}
   {:face "2" :suit "K" :value 11}])

(def flush-cards-KC-JC-9C-3C-2C
  [{:face "K" :suit "C" :value 11} {:face "J" :suit "C" :value 9}
   {:face "9" :suit "C" :value 7} {:face "3" :suit "C" :value 1}
   {:face "2" :suit "C" :value 0}])

(def pair-card-with-2S-8D-5C-QS-2D
  [{:face "2" :suit "S" :value 0} {:face "8" :suit "D" :value 6}
   {:face "5" :suit "C" :value 3} {:face "Q" :suit "S" :value 10}
   {:face "2" :suit "D" :value 0}])

(def two-pair-with-2S-5D-5C-QS-2D
  [{:face "2" :suit "S" :value 0} {:face "5" :suit "D" :value 3}
   {:face "5" :suit "C" :value 3} {:face "Q" :suit "S" :value 10}
   {:face "2" :suit "D" :value 0}])

(def triplet-with-5S-5D-5C-QS-2D
  [{:face "5" :suit "S" :value 3} {:face "5" :suit "D" :value 3}
   {:face "5" :suit "C" :value 3} {:face "Q" :suit "S" :value 10}
   {:face "2" :suit "D" :value 0}])

(def straight-with-QS-JD-10H-9S-8C
  [{:face "Q" :suit "S" :value 10} {:face "J" :suit "D" :value 9}
   {:face "10" :suit "H" :value 8} {:face "9" :suit "S" :value 7}
   {:face "8" :suit "C" :value 6}])

(def straight-with-AS-KC-QH-JH-10D
  [{:face "A" :suit "S" :value 12} {:face "K" :suit "C" :value 11}
   {:face "Q" :suit "H" :value 10} {:face "J" :suit "H" :value 9}
   {:face "10" :suit "D" :value 8}])

(def straight-wheel-5C-4H-3D-2C-AS
  [{:face "5" :suit "C" :value 3} {:face "4" :suit "H" :value 2}
   {:face "3" :suit "D" :value 1} {:face "2" :suit "C" :value 0}
   {:face "A" :suit "S" :value -1}])

(def straight-flush-with-QS-JS-10S-9S-8S
  [{:face "Q" :suit "S" :value 10} {:face "J" :suit "S" :value 9}
   {:face "10" :suit "S" :value 8} {:face "9" :suit "S" :value 7}
   {:face "8" :suit "S" :value 6}])

(def full-house-with-5S-2S-5D-2D-5C
  [{:face "5" :suit "S" :value 3}{:face "2" :suit "S" :value 0}
   {:face "5" :suit "D" :value 3}{:face "2" :suit "D" :value 0}
   {:face "5" :suit "C" :value 3}])

(def four-of-kind-with-5S-5D-5C-QS-5H
  [{:face "5" :suit "S" :value 3} {:face "5" :suit "D" :value 3}
   {:face "5" :suit "C" :value 3} {:face "Q" :suit "S" :value 10}
   {:face "5" :suit "H" :value 3}])
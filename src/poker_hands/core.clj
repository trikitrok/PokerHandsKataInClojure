(ns poker-hands.core)

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

(defn- split-in-card-descriptions [hand-description]
  (clojure.string/split hand-description #" "))

(defn- suit [card-description]
  (if (= 2 (count card-description))
    (str (second card-description))
    (str (nth card-description 2))))

(defn- card [card-description]
  {:face  (face card-description)
   :value (compute-value card-description)
   :suit  (suit card-description)})

(def ^:private create-cards (partial map card))

(defn- highest-cards [hand]
  (sort #(> (:value %1) (:value %2)) hand))

(defn- highest-card [hand]
  (first (highest-cards hand)))

(defn- second-highest-card [hand]
  (second (highest-cards hand)))

(defn- a-high-card [hand]
  {:hand-type    :high-card
   :highest-card (:face (highest-card hand))})

(defn- a-flush [hand]
  {:hand-type    :flush
   :highest-card (:face (highest-card hand))})

(defn- flush? [hand]
  (apply = (map :suit hand)))

(def ^:private pluck-face (partial map :face))

(def ^:private pluck-value (partial map :value))

(defn- make-group-selection-pred [compare-fn group-size]
  (comp (partial compare-fn group-size) second))

(def ^:private pairs-pred
  (make-group-selection-pred = 2))

(def ^:private no-pairs-pred
  (make-group-selection-pred not= 2))

(defn- faces-groups [group-selection-pred hand]
  (->> hand
       pluck-face
       frequencies
       (filter group-selection-pred)))

(def ^:private sort-by-greater-value
  (partial sort by-greater-described-value))

(defn- group-cards [group-selection-pred hand]
  (sort-by-greater-value
    (map first (faces-groups group-selection-pred hand))))

(def ^:private face-pairs
  (partial faces-groups pairs-pred))

(defn- pair? [hand]
  (= 1 (count (face-pairs hand))))

(def ^:private no-pair-cards
  (partial group-cards no-pairs-pred))

(def ^:private pair-cards
  (partial group-cards pairs-pred))

(defn- a-pair [hand]
  {:hand-type     :pair
   :pair-card     (pair-cards hand)
   :no-pair-cards (no-pair-cards hand)})

(defn- two-pairs? [hand]
  (= 2 (count (face-pairs hand))))

(defn- a-two-pairs [hand]
  {:hand-type     :two-pairs
   :pair-cards    (pair-cards hand)
   :no-pair-cards (no-pair-cards hand)})

(def ^:private triplets-pred
  (make-group-selection-pred = 3))

(def ^:private no-triplets-pred
  (make-group-selection-pred not= 3))

(def ^:private face-triplets
  (partial faces-groups triplets-pred))

(defn- triplet? [hand]
  (= 1 (count (face-triplets hand))))

(def ^:private no-triplet-cards
  (partial group-cards no-triplets-pred))

(def ^:private triplet-cards
  (partial group-cards triplets-pred))

(defn- a-triplet [hand]
  {:hand-type        :triplet
   :triplet-card     (triplet-cards hand)
   :no-triplet-cards (no-triplet-cards hand)})

(defn- consecutives? [sorted-values]
  (every? #(= 1 %)
          (map #(- (second %) (first %))
               (partition 2 1 sorted-values))))

(defn- sorted-values [hand]
  (->> hand
       pluck-value
       sort))

(defn- wheel? [hand]
  (= (sorted-values hand) [0 1 2 3 12]))

(defn- straight? [hand]
  (or (wheel? hand)
      (consecutives? (sorted-values hand))))

(defn- a-straight [hand]
  {:hand-type :straight
   :highest-card
              (:face (if (wheel? hand)
                       (second-highest-card hand)
                       (highest-card hand)))})

(defn- a-straight-flush [hand]
  (assoc
    (a-straight hand)
    :hand-type :straight-flush))

(defn- a-full-house [hand]
  {:hand-type    :full-house
   :triplet-card (triplet-cards hand)
   :pair-card    (pair-cards hand)})

(def ^:private four-kind-pred
  (make-group-selection-pred = 4))

(def ^:private no-four-kind-pred
  (make-group-selection-pred not= 4))

(def ^:private face-four-kinds
  (partial faces-groups four-kind-pred))

(defn- four-kind? [hand]
  (= 1 (count (face-four-kinds hand))))

(def ^:private no-four-kind-cards
  (partial group-cards no-four-kind-pred))

(def ^:private four-kind-cards
  (partial group-cards four-kind-pred))

(defn- a-four-kind [hand]
  {:hand-type      :four-kind
   :four-kind-card (four-kind-cards hand)
   :no-four-card   (no-four-kind-cards hand)})

(defn- categorize [hand]
  (cond
    (and (flush? hand) (not (straight? hand))) (a-flush hand)
    (and (pair? hand) (not (triplet? hand))) (a-pair hand)
    (two-pairs? hand) (a-two-pairs hand)
    (and (triplet? hand) (not (pair? hand))) (a-triplet hand)
    (and (straight? hand) (not (flush? hand))) (a-straight hand)
    (and (straight? hand) (flush? hand)) (a-straight-flush hand)
    (and (triplet? hand) (pair? hand)) (a-full-house hand)
    (four-kind? hand) (a-four-kind hand)
    :else (a-high-card hand)))

(defn- cards [hand-description]
  (-> hand-description
      split-in-card-descriptions
      create-cards))

(defn hand [hand-description]
  (-> hand-description
      cards
      categorize))

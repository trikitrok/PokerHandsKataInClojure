(ns poker-hands.result-messages)

(def ^:private four-kind-card (comp first :four-kind-card))

(defmulti victory-message :type)

(defmethod ^:private victory-message :straight-flush [_]
  "with a straight flush")

(defmethod ^:private victory-message :four-kind [hand]
  (str "with a four of " (four-kind-card hand)))

(ns poker-hands.ranking-system-test
  (:use midje.sweet)
  (:use [poker-hands.ranking-system]))

(facts
  "About the hands ranking system"
  (facts
    "Straight Flush hand"
    (facts
      "beats any other kind of hand"
      (let [white-with-straight-flush {:winner  :white
                                       :message "with a straight flush"}]
        (compare-hands
          {:player :white :type :straight-flush :highest-card "Q"}
          {:player :black :type :high-card :highest-card "K"}) => white-with-straight-flush

        (compare-hands
          {:player :white :type :straight-flush :highest-card "Q"}
          {:player :black :type :pair :pair-card ["2"] :no-pair-cards ["Q" "8" "5"]}) => white-with-straight-flush

        (compare-hands
          {:player :white :type :straight-flush :highest-card "Q"}
          {:player :black :type :two-pairs :pair-cards ["5" "2"] :no-pair-cards ["Q"]}) => white-with-straight-flush

        (compare-hands
          {:player :white :type :straight-flush :highest-card "Q"}
          {:player :black :type :triplet :triplet-card ["5"] :no-triplet-cards ["Q" "2"]}) => white-with-straight-flush

        (compare-hands
          {:player :white :type :straight-flush :highest-card "Q"}
          {:player :black :type :straight :highest-card "Q"}) => white-with-straight-flush

        (compare-hands
          {:player :black :type :flush :highest-card "A"}
          {:player :white :type :straight-flush :highest-card "Q"}) => white-with-straight-flush

        (compare-hands
          {:player :white :type :straight-flush :highest-card "Q"}
          {:player :black :type :full-house :triplet-card ["5"] :pair-card ["2"]}) => white-with-straight-flush

        (compare-hands
          {:player :white :type :straight-flush :highest-card "Q"}
          {:player :black :type :four-kind :four-kind-card ["5"] :no-four-card ["Q"]}) => white-with-straight-flush))

    (facts
      "has to untie with one of its kind"
      (compare-hands
        {:player :white :type :straight-flush :highest-card "K"}
        {:player :black :type :straight-flush :highest-card "A"}) => {:winner  :black
                                                                      :message "with a straight flush"}
      (compare-hands
        {:player :white :type :straight-flush :highest-card "6"}
        {:player :black :type :straight-flush :highest-card "6"}) => {:winner :no-winner})))

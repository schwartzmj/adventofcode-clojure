(def str-input (clojure.string/split-lines (slurp "input")))

;; turn input strings to numbers
(def input (map read-string str-input))

(defn get-fuel-from-mass
  "Get fuel based on mass (mass divided by 3 rounded down, then subtract 2). NOTE: 'quot' divides and rounds towards 0 for a non-decimal number. Rounding down is required for this puzzle."
  [mass]
  (let [calculated-fuel (- (quot mass 3) 2)]
    (if (<= calculated-fuel 0) 0 calculated-fuel)
  )
)

(defn get-fuel-from-fuel-mass
"Recursively gets fuel needed for the mass of the fuel itself."
[fuel-mass]
(if (<= fuel-mass 0)
  0
  (let [calculated-fuel (get-fuel-from-mass fuel-mass)]
    (+ calculated-fuel (get-fuel-from-fuel-mass calculated-fuel))
  )
))

(defn get-total-fuel
  "Gjreklajrlkewa"
  [mass]
  (let [calculated-mass (get-fuel-from-mass mass)]
  (+ calculated-mass (get-fuel-from-fuel-mass calculated-mass)))
)

(def fuel-needed-list (map get-total-fuel input))

(def fuel-needed (reduce + fuel-needed-list))

(println "FUEL NEEDED (should be 4685788):" fuel-needed)



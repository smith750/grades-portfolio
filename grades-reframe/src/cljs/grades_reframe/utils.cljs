(ns grades-reframe.utils)

(defn format-grade [grade-value delta]
  (.toFixed grade-value delta))

(defn calculate-upper-grade [grade lower-bound delta]
  (let [one-higher-lower-bound (* grade (+ lower-bound 0.1))]
  (if (= lower-bound 0.9)
    (js/parseFloat grade)
    (- one-higher-lower-bound (if (= delta 0) 1 0.1)))))

(defn parseable-number? [s]
  (let [not-nil? (complement nil?)]
    (not-nil? (re-find #"^\d+\.*\d*$" s))))

(defn update-delta [grade current-delta]
  (cond
    (= grade "") current-delta
    (<= grade 10) 1
    (>= grade 40) 0
    :else current-delta))

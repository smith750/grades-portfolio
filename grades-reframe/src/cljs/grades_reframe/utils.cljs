(ns grades-reframe.utils)

(defn format-grade [grade-value delta]
  (.toFixed grade-value delta))

  (defn calculate-upper-grade [grade lower-bound delta]
    (let [one-higher-lower-bound (* grade (+ lower-bound 0.1))]
    (if (= lower-bound 0.9)
      (js/parseFloat grade)
      (- one-higher-lower-bound (if (= delta 0) 1 0.1)))))

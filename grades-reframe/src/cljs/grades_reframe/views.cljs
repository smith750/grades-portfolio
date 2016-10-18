(ns grades-reframe.views
    (:require [re-frame.core :as re-frame]
              [goog.string :as gstring]
              [goog.string.format]))

(defn grade-display []
  (let [grade (re-frame/subscribe [:grade])]
    (fn []
      [:div "The grade is " @grade])))

(defn grade-entry []
  (let [grade (re-frame/subscribe [:grade])]
    (fn []
      [:div
        [:label "Grade: "
        [:input {:type "text" :value @grade :on-change #(re-frame/dispatch [:grade-change (-> % .-target .-value)])}]]
      ])
  ))

(defn delta-entry []
  (let [delta (re-frame/subscribe [:delta])
        grade (re-frame/subscribe [:grade])]
      (fn []
        [:div
          [:label "Show decimal points? "
            [:input {:type "checkbox" :checked (not= @delta 0) :on-click #(re-frame/dispatch [:toggle-delta])}]]])))

(defn format-grade [grade-value delta]
  (.toFixed grade-value delta))

(defn calculate-upper-grade [grade lower-bound delta]
  (let [one-higher-lower-bound (* grade (+ lower-bound 0.1))]
  (if (= lower-bound 0.9)
    (* grade 1) ;; cheap cast
    (- one-higher-lower-bound (if (= delta 0) 1 0.1)))))

(defn grade-amount-row [letter-grade lower-bound]
  (let [grade (re-frame/subscribe [:grade])
        delta (re-frame/subscribe [:delta])]
  (fn []
    (let [lower-grade (* @grade lower-bound)
          upper-grade (calculate-upper-grade @grade lower-bound @delta)]
    [:tr
      [:td letter-grade]
      [:td (format-grade lower-grade @delta)]
      [:td (format-grade upper-grade @delta)]
    ]))))

(defn grade-failure-row [letter-grade]
  (let [grade (re-frame/subscribe [:grade])
        delta (re-frame/subscribe [:delta])]
    (fn []
      (let [upper-grade (calculate-upper-grade @grade 0.5 @delta)]
      [:tr [:td letter-grade] [:td {:colSpan "2"} (format-grade upper-grade @delta) " and below"]]
    ))))

(defn grade-amounts-table []
  [:table
    [:tbody
      [grade-amount-row "A" 0.9]
      [grade-amount-row "B" 0.8]
      [grade-amount-row "C" 0.7]
      [grade-amount-row "D" 0.6]
      [grade-failure-row "F"]
    ]])

(defn parseable-number? [s]
  (let [not-nil? (complement nil?)]
    (not-nil? (re-find #"^\d+\.*\d*$" s))
  ))

(defn grade-amounts-table-display []
  (let [grade (re-frame/subscribe [:grade])]
    (fn []
      (if (parseable-number? @grade) [grade-amounts-table] [:span]))))

(defn main-panel []
      [:div
      [:h2 "Grades"]
      [grade-entry]
      [delta-entry]
      [grade-amounts-table-display]])

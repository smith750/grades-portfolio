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
            [:input {:type "radio" :checked (not= @delta 0) :on-change #(re-frame/dispatch [:delta-change 1])}] "Yes"
            [:input {:type "radio" :checked (= @delta 0) :on-change #(re-frame/dispatch [:delta-change 0])}] "No"]])))

(defn format-grade [grade-value delta]
  (.toFixed grade-value delta))

(defn grade-amount-row [letter-grade lower-bound]
  (let [grade (re-frame/subscribe [:grade])
        delta (re-frame/subscribe [:delta])]
  (fn []
    (let [one-higher-lower-bound (* @grade (+ lower-bound 0.1))
          lower-grade (* @grade lower-bound)
          upper-grade (if (= lower-bound 0.9) (* @grade 1) (- one-higher-lower-bound (if (= @delta 0) 1 0.1)))]
    [:tr
      [:td letter-grade]
      [:td (format-grade lower-grade @delta)]
      [:td (format-grade upper-grade @delta)]
    ]))))

(defn grade-failure-row []
  (let [grade (re-frame/subscribe [:grade])
        delta (re-frame/subscribe [:delta])]
    (fn []
      (let [upper-grade (* @grade 0.59)]
      [:tr [:td "F"] [:td {:colSpan "2"} (format-grade upper-grade @delta) " and below"]]
    ))))

(defn grade-amounts-table []
  [:table
    [:tbody
      [grade-amount-row "A" 0.9]
      [grade-amount-row "B" 0.8]
      [grade-amount-row "C" 0.7]
      [grade-amount-row "D" 0.6]
      [grade-failure-row]
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

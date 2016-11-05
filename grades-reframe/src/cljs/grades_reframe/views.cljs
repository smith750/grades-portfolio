(ns grades-reframe.views
    (:require [re-frame.core :as re-frame]
              [grades-reframe.utils :as utils]))

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
  (let [delta (re-frame/subscribe [:delta])]
    (fn []
      (let [should-checked (not= @delta 0)]
      [:div
        [:label "Show decimal points? "
          [:input {:type "checkbox" :checked should-checked :on-change #(re-frame/dispatch [:toggle-delta])}]]]))))

(defn grade-amount-row [grade delta letter-grade lower-bound]
    (let [lower-grade (* grade lower-bound)
          upper-grade (utils/calculate-upper-grade grade lower-bound delta)]
    [:tr
      [:td letter-grade]
      [:td (utils/format-grade upper-grade delta)]
      [:td (utils/format-grade lower-grade delta)]
    ]))

(defn grade-failure-row [grade delta letter-grade]
  (let [upper-grade (utils/calculate-upper-grade grade 0.5 delta)]
  [:tr [:td letter-grade] [:td {:colSpan "2"} (utils/format-grade upper-grade delta) " and below"]]
))

(defn grade-amounts-table [grade delta]
  [:table
    [:tbody
      [grade-amount-row grade delta "A" 0.9]
      [grade-amount-row grade delta "B" 0.8]
      [grade-amount-row grade delta "C" 0.7]
      [grade-amount-row grade delta "D" 0.6]
      [grade-failure-row grade delta "F"]
    ]])

(defn grade-amounts-table-display []
  (let [grade (re-frame/subscribe [:grade])
        delta (re-frame/subscribe [:delta])]
    (fn []
      (if (utils/parseable-number? @grade) [grade-amounts-table @grade @delta] [:span]))))

(defn main-panel []
      [:div
      [:h2 "Grades"]
      [grade-entry]
      [delta-entry]
      [grade-amounts-table-display]])

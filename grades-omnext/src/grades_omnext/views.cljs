(ns grades-omnext.views
  (:require
    [om.next :as om :refer-macros [defui]]
    [om.dom :as dom]
    [grades-omnext.store :as app-store]
    [grades-omnext.utils :as utils]))

(defui GradeDisplay
  static om/IQuery
  (query [this]
    [:app/grade])
  Object
  (render [this]
    (let [{grade :app/grade} (om/props this)]
      (dom/div nil (str "The grade is " grade)))))

(defui GradeInput
  static om/IQuery
  (query [this]
    [:app/grade])
  Object
  (render [this]
    (let [{grade :app/grade} (om/props this)]
      (dom/div nil
        (dom/label nil "Grade "
          (dom/input
              #js {:type "text"
                   :value grade
                   :onChange
                    (fn [event]
                      (let [new-grade (-> event .-target .-value)]
                      (om/transact! this `[(app/update-grade {:new-grade ~new-grade}) :app/grade])))}))))))

(defui DeltaEntry
  static om/IQuery
  (query [this] [:app/delta])
  Object
  (render [this]
    (let [{delta :app/delta} (om/props this)
           should-checked (not= delta 0)]
           (println "rendering delta delta = " delta " and should-check = " should-checked)
      (dom/div nil
        (dom/label nil "Show decimal points? "
          (dom/input #js {:type "checkbox"
                          :checked should-checked
                          :onChange #(om/transact! this `[(app/toggle-delta) :app/delta])}))))))

(defn grade-amount-row [grade delta letter-grade lower-bound]
  (let [lower-grade (* grade lower-bound)
        upper-grade (utils/calculate-upper-grade grade lower-bound delta)]
  (dom/tr nil
    (dom/td nil letter-grade)
    (dom/td nil (utils/format-grade upper-grade delta))
    (dom/td nil (utils/format-grade lower-grade delta)))))

(defn grade-failure-row [grade delta letter-grade]
  (let [upper-grade (utils/calculate-upper-grade grade 0.5 delta)]
  (dom/tr nil (dom/td nil letter-grade) (dom/td #js {:colSpan "2"} (utils/format-grade upper-grade delta) " and below"))))

(defn grade-amounts-table [grade delta]
  (println "rendering grade amounts table grade = " grade " delta = " delta)
  (dom/table nil
    (dom/tbody nil
      (grade-amount-row grade delta "A" 0.9)
      (grade-amount-row grade delta "B" 0.8)
      (grade-amount-row grade delta "C" 0.7)
      (grade-amount-row grade delta "D" 0.6)
      (grade-failure-row grade delta "F")
    )))

(defui GradeTable
  static om/IQuery
  (query [this] [:app/grade :app/delta])
  Object
  (render [this]
    (println "Rendering grade table, props " (om/props this))
    (let [{grade :app/grade delta :app/delta} (om/props this)]
      (if (utils/parseable-number? grade)
        (dom/div nil (grade-amounts-table grade delta))
        (dom/span nil)))))

(def grade-display (om/factory GradeDisplay))
(def grade-input (om/factory GradeInput))
(def grade-table (om/factory GradeTable))
(def delta-entry (om/factory DeltaEntry))

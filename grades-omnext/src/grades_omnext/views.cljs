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

(defn grade-amounts-table [grade]
  (dom/table nil
    (dom/tbody nil
      (grade-amount-row grade 0 "A" 0.9)
      (grade-amount-row grade 0 "B" 0.8)
      (grade-amount-row grade 0 "C" 0.7)
      (grade-amount-row grade 0 "D" 0.6)
      (grade-failure-row grade 0 "F")
    )))

(defui GradeTable
  static om/IQuery
  (query [this] [:app/grade])
  Object
  (render [this]
    (let [{grade :app/grade} (om/props this)]
      (if (utils/parseable-number? grade)
        (dom/div nil (grade-amounts-table grade))
        (dom/span nil)))))

(def grade-display (om/factory GradeDisplay))
(def grade-input (om/factory GradeInput))
(def grade-table (om/factory GradeTable))

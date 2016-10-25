(ns grades-omnext.views
  (:require
    [om.next :as om :refer-macros [defui]]
    [om.dom :as dom]
    [grades-omnext.store :as app-store]))

(defui GradeDisplay
  static om/IQuery
  (query [this]
    '[:grade])
  Object
  (render [this]
    (print (om/props this))
    (let [{:keys [grade]} (om/props this)]
    (dom/div nil (str "The grade is " grade)))))

(defui GradeInput
  Object
  (render [this]
    (dom/div nil
      (dom/label nil "Grade "
        (dom/input #js {:type "text" :value "100"})))))

(def grade-display (om/factory GradeDisplay))
(def grade-input (om/factory GradeInput))

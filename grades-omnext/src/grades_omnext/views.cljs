(ns grades-omnext.views
  (:require
    [om.next :as om :refer-macros [defui]]
    [om.dom :as dom]
    [grades-omnext.store :as app-store]))

(defui GradeDisplay
  static om/IQuery
  (query [this]
    '[:app/grade])
  Object
  (render [this]
    (print "GradeDisplay props " (om/props this))
    (let [{:keys [grade]} (om/props this)]
      (dom/div nil (str "The grade is " grade)))))

(defui GradeInput
  static om/IQuery
  (query [this]
    '[:app/grade])
  Object
  (render [this]
    (let [{:keys [grade]} (om/props this)]
      (dom/div nil
        (dom/label nil "Grade "
          (dom/input
              #js {:type "text"
                   :value grade
                   :onChange
                    (fn [event]
                      (let [new-grade (-> event .-target .-value)]
                      (println "mutating " new-grade)
                      (om/transact! this `[(app/update-grade {:new-grade ~new-grade}) :app/grade])
                      (om/set-state! this {:app/grade new-grade})))}))))))

(def grade-display (om/factory GradeDisplay))
(def grade-input (om/factory GradeInput))

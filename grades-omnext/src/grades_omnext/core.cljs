(ns grades-omnext.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(enable-console-print!)

(println "running grades app")

(defn GradeDisplay [grade]
  (dom/div nil (str "The grade is " grade)))

(defn GradeInput [grade]
  (dom/div nil
    (dom/label nil "Grade "
      (dom/input #js {:type "text" :value grade}))))

(defui GradeApp
  Object
  (render [this]
    (dom/div nil
      (GradeDisplay "10")
      (GradeInput "10"))))

(def grade-app (om/factory GradeApp))

(js/ReactDOM.render (grade-app) (gdom/getElement "main"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

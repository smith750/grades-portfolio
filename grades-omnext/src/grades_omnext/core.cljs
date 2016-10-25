(ns grades-omnext.core
  (:require [goog.dom :as gdom]
            [grades-omnext.views :as grade-views]
            [grades-omnext.store :as app-store]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(enable-console-print!)

(println "running grades app")

(defui GradeApp
  Object
  (render [this]
    (dom/div nil
      (grade-views/grade-display)
      (grade-views/grade-input))))

(om/add-root! app-store/reconciler GradeApp (gdom/getElement "main"))

; (def grade-app (om/factory GradeApp))
;
; (js/ReactDOM.render (grade-app) (gdom/getElement "main"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

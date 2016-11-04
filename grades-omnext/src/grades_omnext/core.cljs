(ns grades-omnext.core
  (:require [goog.dom :as gdom]
            [grades-omnext.views :as grade-views]
            [grades-omnext.store :as app-store]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(enable-console-print!)

(defui GradeApp
  static om/IQuery
  (query [this] `[{:app/grade ~(om/get-query grade-views/GradeInput)}
                  {:app/delta ~(om/get-query grade-views/DeltaEntry)}])
  Object
  (render [this]
    (println "Rendering GradeApp, props? " (om/props this))
    (dom/div nil
      (grade-views/grade-input (om/props this))
      (grade-views/delta-entry (om/props this))
      (grade-views/grade-table (om/props this)))))

(om/add-root! app-store/reconciler GradeApp (gdom/getElement "main"))

; (def grade-app (om/factory GradeApp))
;
; (js/ReactDOM.render (grade-app) (gdom/getElement "main"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

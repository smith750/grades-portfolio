(ns grades-omnext.core
  (:require [goog.dom :as gdom]
            [grades-omnext.views :as grade-views]
            [grades-omnext.store :as app-store]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(enable-console-print!)

(defui GradeApp
  static om/IQuery
  (query [this] `[{:grade ~(om/get-query grade-views/GradeDisplay)}])
  Object
  (render [this]
    (let [{:keys [grade]} (om/props this)]
      (dom/div nil
        (grade-views/grade-display {:grade grade})
        (grade-views/grade-input (om/props this))))))

(om/add-root! app-store/reconciler GradeApp (gdom/getElement "main"))

; (def grade-app (om/factory GradeApp))
;
; (js/ReactDOM.render (grade-app) (gdom/getElement "main"))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

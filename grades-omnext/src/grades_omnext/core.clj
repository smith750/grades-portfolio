(ns grades-omnext.core
  (:require [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]))

(enable-console-print!)

(defui GradeDisplay
  Object
  (render [this]
    (dom/div nil "The grade is")))

(def grade-display (om/factory GradeDisplay))

(js/ReactDom.render (grade-display) (gdom/getElement "main"))

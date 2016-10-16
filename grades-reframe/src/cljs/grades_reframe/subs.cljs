(ns grades-reframe.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
  :grade
  (fn [db]
    (:grade @db)))

(re-frame/reg-sub
  :delta
  (fn [db]
    (:delta @db)))

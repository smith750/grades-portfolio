(ns grades-reframe.events
    (:require [re-frame.core :as re-frame]
              [grades-reframe.db :as db]
              [reagent.core :as reagent]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :grade-change
  (fn [app-db [_ new-grade]]
    (swap! app-db assoc :grade new-grade)
    ;;(swap! app-db assoc :delta (if (= new-grade "") (:delta @app-db) (if (<= new-grade-numeric 20) 1 (if (>= new-grade-numeric 40) 0 (:delta @app-db)))))
    app-db))

(re-frame/reg-event-db
  :delta-change
  (fn [app-db [_ new-delta]]
    (swap! app-db assoc :delta new-delta)
    app-db))

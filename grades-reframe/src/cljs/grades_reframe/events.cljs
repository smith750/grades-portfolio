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
    (swap! app-db assoc :grade new-grade :delta (if (= new-grade "") (:delta @app-db) (if (<= new-grade 10) 1 (if (>= new-grade 40) 0 (:delta @app-db)))))
    app-db))

(re-frame/reg-event-db
  :toggle-delta
  (fn [app-db]
    (let [delta (:delta @app-db)
          new-delta (if (= delta 0) 1 0)]
      (swap! app-db assoc :delta new-delta)
    app-db)))

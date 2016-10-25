(ns grades-omnext.store
  (:require
    [om.next :as om]))

(def app-state (atom {:grade "99"}))

(defmulti read (fn [env key params] key))

(defmethod read :grade
  [{:keys [state] :as env} key _]
  {:value (:grade @state)})

(def reconciler
  (om/reconciler
    {:state app-state
      :parser (om/parser {:read read})}))

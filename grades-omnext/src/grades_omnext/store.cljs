(ns grades-omnext.store
  (:require
    [om.next :as om]))

(def app-state (atom {:app/grade "99"}))

(defmulti read (fn [env key params] key))

(defmethod read :default [{:keys [state] :as env} key params]
  (let [st @state]
    (println "reading state from default")
    (if-let [[_ v] (find st key)]
      {:value v}
      {:value :not-found})))

(defmethod read :grade
  [{:keys [state] :as env} key _]
  (println "reading state from grade")
  {:value (:app/grade @state)})

(def reconciler
  (om/reconciler
    {:state app-state
      :parser (om/parser {:read read})}))

(ns grades-omnext.store
  (:require
    [om.next :as om]))

(def app-state (atom {:app/grade "100"}))

(defmulti read (fn [env key params] key))

(defmethod read :default [{:keys [state] :as env} key params]
  (let [st @state]
    (println "reading state from default")
    (if-let [[_ v] (find st key)]
      {:value v}
      {:value :not-found})))

(defmethod read :app/grade
  [{:keys [state] :as env} key _]
  (println "reading state from grade " @state " key " key)
  {:value (:app/grade @state)})

(defn mutate [{:keys [state] :as env} key params]
  (println "mutate keys " key " state " @state)
  (if (= 'app/grade key)
    {:value {:keys [:app/grade]}
      :action #(swap! state assoc :app/grade key)}
    {:value :not-found}))

(def reconciler
  (om/reconciler
    {:state app-state
     :parser (om/parser {:read read :mutate mutate})}))

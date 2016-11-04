(ns grades-omnext.store
  (:require
    [om.next :as om]
    [grades-omnext.utils :as utils]))

(def app-state (atom {:app/grade "100" :app/delta 0}))

(defmulti read om/dispatch)

(defmethod read :default [{:keys [state] :as env} key params]
  (let [st @state]
    (if-let [[_ v] (find st key)]
      {:value v}
      {:value :not-found})))

(defmethod read :app/grade
  [{:keys [state] :as env} key _]
  {:value (:app/grade @state)})

; (defmulti mutate om/dispatch)
;
; (defmethod mutate 'app/update-grade [{:keys [state]} _ {:keys [new-grade]}]
;   (println "mutating for app/update-grade " @state " new-grade " new-grade)
;   {:action
;     (fn []
;       (swap! state assoc :app/grade new-grade))})

(defn mutate [{:keys [state] :as env} key params]
  (cond
    (= 'app/update-grade key)
      {:value {:keys [:app/grade]}
        :action #(swap! state assoc :app/grade (:new-grade params) :app/delta (utils/update-delta (:new-grade params) (:app/delta @state)))}
    (= 'app/toggle-delta)
      (let [new-delta (if (= (:app/delta @state) 0) 1 0)]
      {:value {:keys [:app/delta]}
        :action #(swap! state assoc :app/delta new-delta)})
    :else {:value :not-found}))

(def parser (om/parser {:read read :mutate mutate}))

(def reconciler
  (om/reconciler
    {:state app-state
     :parser parser}))

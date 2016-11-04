(ns grades-omnext.store
  (:require
    [om.next :as om]))

(def app-state (atom {:app/grade "100"}))

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
  (if (= 'app/update-grade key)
    {:value {:keys [:app/grade]}
      :action #(swap! state assoc :app/grade (:new-grade params))}
    {:value :not-found}))

(def parser (om/parser {:read read :mutate mutate}))

(def reconciler
  (om/reconciler
    {:state app-state
     :parser parser}))

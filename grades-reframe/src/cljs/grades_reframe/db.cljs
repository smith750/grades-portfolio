(ns grades-reframe.db
  (:require [reagent.core :as reagent]))

(def default-db (reagent/atom
  {:grade "100"
   :delta 0}))

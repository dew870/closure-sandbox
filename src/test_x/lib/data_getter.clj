(ns test-x.lib.data_getter
  (:require [test-x.lib.data_seed :as seed]
            [datascript.core :as d]))

(defn get-data []
  (let [schema {:name {:db/cardinality :db.cardinality/many}}
        conn   (d/create-conn schema)]
    (d/transact! conn seed/seed-data)
    (d/q '[ :find  ?n ?es
           :where [1]
           [?e :name ?n]
           [?e :empty-space ?es]
           ]
         @conn)))
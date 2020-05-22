(ns test-x.core
  (:require [hiccup.core :as h]
            [datascript.core :as d]
            [org.httpkit.server :as s]))

(defn -main
  [& args]
  ; run server
  ; (s/run-server app {:port 9998})
  )

(s/run-server app {:port 9998})

(defn get-data []
  (let [schema {:name {:db/cardinality :db.cardinality/many}}
      conn   (d/create-conn schema)]
  (d/transact! conn [ { :db/id 1
                       :name  "Container-45"
                       :empty-space 500
                       :arrival   "2020-06-20"
                       :departure   "2020-06-28"}
                     { :db/id 2
                      :name  "Container-56"
                      :empty-space 1200
                      :arrival   "2020-06-23"
                      :departure   "2020-06-25"}
                     { :db/id 3
                      :name  "Container-99"
                      :empty-space 400
                      :arrival   "2020-05-20"
                      :departure   "2020-07-22"}
                     { :db/id 4
                      :name  "Container-101"
                      :empty-space 800
                      :arrival   "2020-01-23"
                      :departure   "2020-01-24"}
                     { :db/id 5
                      :name  "Container-176"
                      :empty-space 1100
                      :arrival   "2020-04-11"
                      :departure   "2020-04-13"}
                     { :db/id 6
                      :name  "Container-192"
                      :empty-space 800
                      :arrival "2020-05-06"
                      :departure "2020-05-08"}])
  (d/q '[ :find  ?n ?es
         :where [1]
         ;:where [> ?es :empty-space 500]
         [?e :name ?n]
         [?e :empty-space ?es]
          ]
       @conn)))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body (h/html [:ul
                  (for [x (get-data)]
                    [:li (str (first x) " | space: " (last x)) ])]
                 )
   })
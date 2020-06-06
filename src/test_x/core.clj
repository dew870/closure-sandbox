(ns test-x.core
  (:require [hiccup.core :as h]
            [org.httpkit.server :as s]
            [ring.adapter.jetty :as jetty]
            [compojure.core :refer [defroutes GET ANY]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [test-x.view.layout :as layout]
            [test-x.view.page.index :as index]
            [test-x.view.page.container :as container]
            [test-x.view.page.404 :as not-found-404]
            ))


(defroutes routes
           (GET "/" [] (layout/application-view "Home" (index/index)))
           (GET "/containers" [] (layout/application-view "Containers" (container/list)))
           (route/resources "/")
           (ANY "*" [] (route/not-found (layout/application-view "Page Not Found" not-found-404/not-found))))

(defn app [req] (routes))

(def server (atom nil))

(defn -main
  [& args]
  (reset! server (s/run-server #'app {:port 9997})))

(comment
  (@server)
  (-main)

  (defn app [req]
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body (h/html [:ul
                    (for [x (dg/get-data)]
                      [:li (str (first x) " | space: " (last x)) ])]
                   )
     })


  )

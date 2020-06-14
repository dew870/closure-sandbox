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
            [clojure.pprint :as pp]))

(defroutes routes
  (GET "/" []
    (layout/application-view "Home" (index/index)))

  (GET "/containers" []
    (layout/application-view "Containers" (container/list-container)))

  (route/resources "/")

  (ANY "*" []
    (route/not-found
      (layout/application-view "Page Not Found" not-found-404/not-found))))

(defn app [req]
  (pp/pprint req)
  (routes req))

(defonce server (atom nil))

(defn -main
  [& args]
  (reset! server (s/run-server #'app {:port 9997})))

(comment
  (@server)
  (-main)

  (app req)

  (defn app [req]
    {:status  200
     :headers {"Content-Type" "text/html"}
     :body    (h/html [:ul
                       (for [x (dg/get-data)]
                         [:li (str (first x) " | space: " (last x))])]
                      )
     })

  (def req
    {:remote-addr "0:0:0:0:0:0:0:1",
     :headers
                  {"sec-fetch-site" "none",
                   "host" "localhost:9997",
                   "user-agent"
                                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36",
                   "cookie" "_ga=GA1.1.633980692.1588099813",
                   "sec-fetch-user" "?1",
                   "connection" "keep-alive",
                   "upgrade-insecure-requests" "1",
                   "accept"
                   "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
                   "accept-language" "hu-HU,hu;q=0.9,en-US;q=0.8,en;q=0.7",
                   "sec-fetch-dest" "document",
                   "accept-encoding" "gzip, deflate, br",
                   "sec-fetch-mode" "navigate",
                   "cache-control" "max-age=0"},
     :server-port 9997,
     :content-length 0,
     :websocket? false,
     :content-type nil,
     :character-encoding "utf8",
     :uri "/",
     :server-name "localhost",
     :query-string nil,
     :body nil,
     :scheme :http,
     :request-method :get})
  )

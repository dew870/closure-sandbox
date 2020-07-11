(ns test-x.core-test
  (:require [clojure.test :refer :all]
            [test-x.core :refer :all]
            [test-x.view.page.index :as index]
            [clojure.string :as str]))

(def base-request
  {:remote-addr        "0:0:0:0:0:0:0:1",
   :headers            {"accept" "*/*"
                        "host" "localhost:9997"
                        "user-agent" "curl/7.64.1"},
   :server-port        9997,
   :content-length     0,
   :websocket?         false,
   :content-type       nil,
   :character-encoding "utf8",
   :uri                "/",
   :server-name        "localhost",
   :query-string       nil,
   :body               nil,
   :scheme             :http,
   :request-method     :get})

(deftest routes-test
  (let [{:keys [status headers body] :as response} (routes base-request)]
    (is (= 200 status))
    (is (str/includes? body "Hellox"))))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= [:div
            {:id "content"}
            [:h1
             {:class "text-success"}
             "Hello Hunkongize!"]]
           (index/index)))))

(ns test-x.view.page.container
  (:use [hiccup.form])
  (:require [test-x.lib.data-getter :as getter]))



(defn list-container []
  [:div {:id "content"}
   [:h1 {:class "text-success"} "Hello Hunkongize!"]
   [:ul
    (for [x (getter/get-data)]
      [:li (str (first x) " | space: " (last x)) ])]
   ])

(ns test-x.view.page.index
  (:use [hiccup.form]))

(defn index []
  [:div {:id "content"}
   [:h1 {:class "text-success"} "Hello Hunkongize!"]])

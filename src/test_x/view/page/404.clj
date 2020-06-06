(ns test-x.view.page.404
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]))

(defn not-found []
  [:div
   [:h1 {:class "info-warning"} "Page Not Found"]
   [:p "Page Not Found"]
   (link-to {:class "btn btn-primary"} "/" "Home")])
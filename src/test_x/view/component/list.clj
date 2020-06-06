(ns test-x.view.component.list)

(defn list []
  [:div {:id "content"}
   [:h1 {:class "text-success"} "Hello Hunkongize!"]
   [:ul
    (for [x (getter/get-data)]
      [:li (str (first x) " | space: " (last x)) ])]
   ])

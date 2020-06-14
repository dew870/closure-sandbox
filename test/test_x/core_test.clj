(ns test-x.core-test
  (:require [clojure.test :refer :all]
            [test-x.core :refer :all]
            [test-x.view.page.index :as index]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= "Hello, world"
           (index/index)))))

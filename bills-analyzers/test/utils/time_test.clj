(ns utils.time-test
  (:use clojure.test
        utils.time))

(deftest edge-cases-for-get-secs-from-time
  (testing "edge cases"
    (is (= 60 (get-secs-from-time "00:01:00")))
    (is (= 3600 (get-secs-from-time "01:00:00")))))


(deftest edge-cases-for-get-time-for-secs
  (testing "edge cases"
    (is (= "01:00:00" (get-time-from-secs 3600)))
    (is (= "00:01:00" (get-time-from-secs 60)))))

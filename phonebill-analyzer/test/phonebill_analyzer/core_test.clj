(ns phonebill-analyzer.core-test
  (:use clojure.test
        phonebill-analyzer.core))

(deftest edge-cases-for-get-secs-from-time
  (testing "edge cases"
    (is (= 60 (get-secs-from-time "00:01:00")))
    (is (= 3600 (get-secs-from-time "01:00:00")))))


(deftest edge-cases-for-get-time-for-secs
  (testing "edge cases"
    (is (= "01:00:00" (get-time-from-secs 3600)))
    (is (= "00:01:00" (get-time-from-secs 60)))))

(deftest all-cases-for-double-digit-format
  (testing "cases"
    (testing "number < 10"
      (is (= "09" (double-digit-format 9))))
    (testing "number > 10"
      (is (= "67" (double-digit-format 67))))
    (testing "= 10"
      (is (= "10" (double-digit-format 10))))       
    (testing "more than 2 digt"
      (is (= "567" (double-digit-format 567))))))       

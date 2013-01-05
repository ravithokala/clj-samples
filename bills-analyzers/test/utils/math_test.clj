(ns utils.math-test
  (:use clojure.test
        utils.math))

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


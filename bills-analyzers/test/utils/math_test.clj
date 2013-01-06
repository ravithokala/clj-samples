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

(deftest testing-rounding
  (testing "all-cases"
    (testing "when decimal number is less than 5"
      (is (= 99.78 (round-places 99.7812324 2))))
    (testing "when decimal number is equal to 5"
      (is (= 99.79 (round-places 99.785 2))))
    (testing "when decimal number is greater than 5"
      (is (= 99.79 (round-places 99.7852324 2)))))
  )

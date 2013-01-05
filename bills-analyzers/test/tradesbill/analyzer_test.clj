(ns tradesbill.analyzer-test
  (:use clojure.test
        utils.data
        tradesbill.analyzer))

(def icici-mf-sample (get-abs-path "tradesbill/sample_icici_mfs_report.csv"))

(deftest testing-csv-parsing-icici-reports
  (let [txs (get-all-icici-mfs-transactions icici-mf-sample)
        first-tx (first txs)
        second-tx (second txs)]
    (testing "testing-date-purchase-name-price-quantity"
      (are [x y] (= x y)
        "08-11-2006 14:01:34" (:date first-tx)
        "XYZ TAX GAIN SCHEME - DIVIDEND" (:name first-tx)
        50.0 (:price first-tx)
        500.0 (:quantity first-tx)
        -1 (:transaction first-tx)
        1 (:transaction second-tx)
        ))))

(ns tradesbill.analyzer-test
  (:use clojure.test
        utils.data
        tradesbill.analyzer))

(def icici-mf-sample (get-abs-path "tradesbill/sample_icici_mfs_report.csv"))
(def icici-shares-sample (get-abs-path "tradesbill/sample_icici_shares_report.csv"))

(deftest testing-csv-parsing-icici-mf-reports
  (let [txs (get-all-icici-mfs-transactions icici-mf-sample)
        first-tx (first txs)
        second-tx (second txs)]
    (testing "testing-date-purchase-name-price-quantity"
      (are [x y] (= x y)
        "08-11-2006 14:01:34" (:date first-tx)
        "XYZ TAX GAIN SCHEME - DIVIDEND" (:name first-tx)
        50.0 (:price first-tx)
        500.0 (:quantity first-tx)
        0.0 (:brokerage first-tx)
        "Buy" (:transaction first-tx)
        "Sell" (:transaction second-tx)
        ))))

(deftest testing-csv-parsing-icici-shares-reports
  (let [txs (get-all-icici-shares-transactions icici-shares-sample)
        first-tx (first txs)
        second-tx (second txs)]
    (testing "testing-date-purchase-name-price-quantity-brokerage"
      (are [x y] (= x y)
        "14-Oct-2010" (:date first-tx)
        "DQENTE" (:name first-tx)
        122.00 (:price first-tx)
        80.0 (:quantity first-tx)
        94.25 (:brokerage first-tx)
        "Sell" (:transaction first-tx)
        "Buy" (:transaction second-tx)
        ))))

(deftest testing-calucation
  (let [txs (get-all-icici-mfs-transactions icici-mf-sample)
        reports (get-reports txs)]
    (testing "testing-remaining-quantity-and-amount"
      (are [x y z] (= x ((reports z) y))
        625.0 :buy-quantity "XYZ TAX GAIN SCHEME - DIVIDEND"
        400.0 :sell-quantity "XYZ TAX GAIN SCHEME - DIVIDEND"
        30000.0 :buy-amount "XYZ TAX GAIN SCHEME - DIVIDEND"
        12000.0 :sell-amount "XYZ TAX GAIN SCHEME - DIVIDEND"
        ))))

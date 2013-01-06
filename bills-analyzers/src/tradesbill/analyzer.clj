(ns tradesbill.analyzer
  (:use [utils.data]))


(defn get-all-transactions [filename map-fn]
  "Returns the transations for the transactions csv file and given mapping function"
  (let [l (take-csv filename)]
    (map map-fn (rest l))))

(defn- map-icici-mf-record [record]
  "Converts the icici record to a map with appropriate keys"
  {:date (record 0)
   :transaction (if (= "Purchase" (record 2)) "Buy" "Sell")
   :name (record 4)
   :price (Double. (record 5))
   :quantity (Double. (record 9))})

(defn get-all-icici-mfs-transactions [filename]
  "Returns the transations for the icici mfs transactions csv file"
  (get-all-transactions filename map-icici-mf-record))

(defn- get-total-quantity [txs]
  "Returns the total quantity of the given transactions"
  (reduce #(+ %1 (:quantity %2)) 0 txs)
  )

(defn- get-total-amount [txs]
  "Returns the total amount for the given transactions"
  (reduce #(+ %1 (* (:quantity %2) (:price %2))) 0 txs)
  )

(defn- get-report-for-same-type [records]
  (let [buy-txs (filter #(= "Buy" (:transaction %)) records)
        sell-txs (filter #(= "Sell" (:transaction %)) records)]
    {:buy-quantity (get-total-quantity buy-txs)
     :sell-quantity (get-total-quantity sell-txs)
     :buy-amount (get-total-amount buy-txs)
     :sell-amount (get-total-amount sell-txs)
     }))

(defn get-reports [txn-records]
  "Calulates the effective quantity and the amount"
  (let [reports-per-name (group-by #(:name %) txn-records)]
    (into {}
      (for [[k v] reports-per-name]
        {k (get-report-for-same-type v)}))))

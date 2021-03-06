(ns tradesbill.analyzer
  (:use [utils.data]
        [utils.math]))


(defn get-all-transactions [filename mapping-fn]
  "Returns the transations for the transactions csv file and given mapping function"
  (let [l (take-csv filename)]
    (map mapping-fn (rest l))))

(defn- map-icici-mf-record [record]
  "Converts the icici record to a map with appropriate keys"
  {:date (record 0)
   :transaction (if (= "Purchase" (record 2)) "Buy" "Sell")
   :name (record 4)
   :price (Double. (record 5))
   :quantity (Double. (record 9))
   :brokerage 0.0})

(defn get-all-icici-mfs-transactions [filename]
  "Returns the transations for the icici mfs transactions csv file"
  (get-all-transactions filename map-icici-mf-record))

(defn- map-icici-share-record [record]
  "Converts the icici record to a map with appropriate keys"
  {:date (record 0)
   :name (record 1)
   :transaction (record 2)
   :quantity (Double. (record 3))
   :price (Double. (record 4))
   :brokerage (Double. (record 6))
   })

(defn get-all-icici-shares-transactions [filename]
  "Returns the transations for the icici shares transactions csv file"
  (get-all-transactions filename map-icici-share-record))


(defn- get-total-quantity [txs]
  "Returns the total quantity of the given transactions"
  (reduce #(+ %1 (:quantity %2)) 0.0 txs)
  )

(defn- get-amount-with-brokerage [{quantity :quantity price :price transaction :transaction brokerage :brokerage}]
  "Returns the total amount for the trasaction after taking brokerage into consideration"
  (if (= "Buy" transaction)
    (+ (* quantity price) brokerage)
    (- (* quantity price) brokerage)))

(defn- get-total-amount [txs]
  "Returns the total amount for the given transactions"
  (reduce #(+ %1 (get-amount-with-brokerage %2)) 0.0 txs))

(defn- get-report-for-same-type [records]
  (let [buy-txs (filter #(= "Buy" (:transaction %)) records)
        sell-txs (filter #(= "Sell" (:transaction %)) records)]
    {:buy-quantity (round-places (get-total-quantity buy-txs) 2)
     :sell-quantity (round-places (get-total-quantity sell-txs) 2)
     :buy-amount (round-places (get-total-amount buy-txs) 2)
     :sell-amount (round-places (get-total-amount sell-txs) 2)
     }))

(defn get-reports [txn-records]
  "Calulates the effective quantity and the amount"
  (let [reports-per-name (group-by #(:name %) txn-records)]
    (into {}
      (for [[k v] reports-per-name]
        {k (get-report-for-same-type v)}))))

(ns tradesbill.analyzer
  (:use [utils.data]))


(defn get-all-transactions [filename map-fn]
  "Returns the transations for the transactions csv file and given mapping function"
  (let [l (take-csv filename)]
    (map map-fn (rest l))))

(defn- map-icici-mf-record [record]
  "Converts the icici record to a map with appropriate keys"
  (into {}
    {:date (record 0)
     :transaction (if (= "Purchase"(record 2)) -1 1)
     :name (record  4)
     :price (Double. (record 5))
     :quantity (Double. (record 9))
     }))

(defn get-all-icici-mfs-transactions [filename]
  "Returns the transations for the icici mfs transactions csv file"
  (get-all-transactions filename map-icici-mf-record))




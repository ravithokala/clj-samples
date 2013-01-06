(ns utils.math
  (:use clojure.contrib.math)
  )

(defn double-digit-format
  "Prefixs zero in the front if n is less than 10"
  [n]
  (if (< n 10) (str "0" n) (str n)))

(defn round-places [number decimals]
  "Rounds a number to given number of decimals"
  (let [factor (expt 10 decimals)]
    (double (/ (round (* factor number)) factor))))
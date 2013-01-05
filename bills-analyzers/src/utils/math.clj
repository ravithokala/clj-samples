(ns utils.math)

(defn double-digit-format
  "Prefixs zero in the front if n is less than 10"
  [n]
  (if (< n 10) (str "0" n) (str n)))


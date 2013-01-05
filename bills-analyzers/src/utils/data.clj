(ns utils.data
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn take-csv
  "Takes file name and reads data."
  [fname]
  (with-open [in-file (io/reader fname)]
    (doall
      (csv/read-csv in-file :separator \,))))


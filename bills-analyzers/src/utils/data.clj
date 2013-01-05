(ns utils.data
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn take-csv
  "Takes file name and reads data."
  [fname]
  (with-open [in-file (io/reader fname)]
    (doall
      (csv/read-csv in-file :separator \,))))

(defn get-abs-path [classpath-loc]
  "Returns the absolute path for a given classpath relative path"
  (.getFile (-> (Thread/currentThread)
              (.getContextClassLoader)
              (.getResource classpath-loc))))
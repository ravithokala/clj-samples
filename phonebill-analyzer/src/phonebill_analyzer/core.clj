(ns phonebill-analyzer.core
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as cstr]))

(defn take-csv
    "Takes file name and reads data."
    [fname]
    (with-open [in-file (io/reader fname)]
         (doall
              (csv/read-csv in-file :separator \,)))) 

(defn double-digit-format
  "Prefixs zero in the front if n is less than 10"
  [n]
  (if (< n 10) (str "0" n) (str n)))

(defn get-secs-from-time
  "Returns the number of seconds for time duration given in format HH:MM:SS"
  [^String td] 
  (reduce + (map #(* %2 (. Integer parseInt %)) (cstr/split td #":") [3600 60 1])))

(defn get-time-from-secs
  "Returns time duration in HH:MM:SS format from secs"
  [secs]
  (let [hr (quot secs 3600)
        mins (rem secs 3600)
        mn (quot mins 60)
        sc (rem mins 60)]
    (str (double-digit-format hr) ":" (double-digit-format mn) ":" (double-digit-format sc))))

(defn- get-talk-times-unsorted [fname]
  (let [x (take-csv fname)
        y (filter #(= "VOICE" (% 0)) x)
        z (group-by #(% 1) y)] 
   (into '() (for [[k v] z] (list k (get-time-from-secs (reduce #(+ (get-secs-from-time (%2 4)) %) 0 v)))))))


(defn get-talk-times [fname]
 (sort-by #(last %) (get-talk-times-unsorted fname)))

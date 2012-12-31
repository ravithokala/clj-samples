(ns phonebill-analyzer.core
  (:use [utils.time] [utils.data] [utils.math])
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as cstr]))


(defn- get-talk-times-unsorted [fname]
  (let [x (take-csv fname)
        y (filter #(= "VOICE" (% 0)) x)
        z (group-by #(% 1) y)] 
   (into '() (for [[k v] z] (list k (get-time-from-secs (reduce #(+ (get-secs-from-time (%2 4)) %) 0 v)))))))


(defn get-talk-times [fname]
  "Returns the list of phone numbers sorted based on talk-time"
 (sort-by #(last %) (get-talk-times-unsorted fname)))

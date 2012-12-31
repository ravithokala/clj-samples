(ns utils.time
  (:require [clojure.string :as cstr]
            [utils.math :as math]))

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
    (str (math/double-digit-format hr) ":" (math/double-digit-format mn) ":" (math/double-digit-format sc))))

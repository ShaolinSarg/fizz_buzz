(ns fizz-buzz.core
  (:require [clojure.string :as str])
  (:gen-class))

(defn fizz? [num] 
  (zero? (rem num 3)))

(defn buzz? [num] 
  (zero? (rem num 5)))

(defn fizz-buzz? [num] 
  (and (fizz? num) (buzz? num)))

(defn conv [num]
  (cond
    (fizz-buzz? num) 'fizz-buzz
    (buzz? num) 'buzz
    (fizz? num) 'fizz
    :else num))

(defn fb [end] 
  (map conv (range 1 (inc end))))

(defn -main
  "Takes a number to stop fizz buzzing to"
  [& args]
  (->> args
       (first)
       (Integer/parseInt)
       (fb)
       (str/join " ")
       (println)))

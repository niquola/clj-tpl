;; internacionaz
(ns i
  (:require
    [clojure.string :as cs]))

(defn parse-key [x]
  (map keyword (cs/split (name x) #"\.")))

(defn lookup-key [m ps]
  (cond
    (nil? ps) nil
    (get-in m ps) (get-in m ps)
    :else (recur m (next ps))))

(defn mk-i [m]
  (fn [s] (or (lookup-key m (parse-key s)) (name s))))

(def testi (mk-i
            {:w "w"
             :a {:b {:c "x"}}}))

(testi :a.b.c)
(testi :a.w)

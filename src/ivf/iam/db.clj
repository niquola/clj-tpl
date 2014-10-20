(ns ivf.iam.db
  (:require [clj-time.core :as t]))

(defn- uuid  []  (str  (java.util.UUID/randomUUID)))

(def usrs (atom {"nicola" {:login "nicola"
                           :name "Nikolay"}}))
(def sess (atom {}))

(defn add-usr [usr]
  (swap! usrs assoc (:login usr) usr))

(defn mk-sess [{login :login password :password}]
  (when-let [u (get @usrs login)]
    (let [sid (uuid)]
      (swap! sess assoc sid {:user u :created-at (t/now)})
      sid)))

(defn rm-sess [sid]
  (swap! sess dissoc sid))

(defn get-sess [sid]
  (get @sess sid))

(defn list-usrs []
  (vals @usrs))


(ns ivf.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [org.httpkit.server :as ohs]
            [ivf.views :as iv]
            [ivf.iam.core :as iic]
            [ring.middleware.resource :as rmr]
            [ivf.routes :as ir]
            [zero :as z]
            [clojure.stacktrace :as cs]
            [ring.util.response :as rur]
            [compojure.route :as route]))


(defn wrap-exception [h]
  (fn [req]
    (try
      (h req)
      (catch Exception e
        {:body (str "Exception:" (with-out-str (cs/print-stack-trace e)))
         :status 500}))))
(def app
  (-> ir/routed
      z/wrap-method
      iic/wrap-user-session
      (rmr/wrap-resource "/assets/")
      handler/site
      wrap-exception))

(comment
  (stop)
  (def stop
    (ohs/run-server #'app {:port 3000})))

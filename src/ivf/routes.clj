(ns ivf.routes
  (:require [compojure.core :refer :all]
            [ivf.views :as iv]
            [ivf.iam.core :as iic]
            [ivf.welcome.core :as iwc]
            [route-map :as rm]
            [ring.util.response :as rur]
            [compojure.route :as route]))

(def rts
  {:GET       {:fn iwc/welcome}
   "signin"   {:GET  {:fn iic/signin}
               :POST {:fn iic/signin!}}
   "signout"  {:DELETE {:fn iic/signout!}}
   "users"    {:POST {:fn iic/mk-user}
               :GET {:fn iic/users}
               "new" {:GET {:fn iic/new-user}}}})


(defn routed [{uri :uri meth :request-method :as req}]
  (println meth " " uri)
  (let [m (rm/match [meth uri] rts)]
    (if-let [h (get-in m [:match :fn])]
      (let [res (h req)]
        (println h)
        (if (map? res) res {:body res :status 200}))
      {:body (str "Route for " meth " " req " not found")
       :status 404})))

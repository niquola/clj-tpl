(ns ivf.iam.core
  (:require
    [ring.util.response :as rur]
    [ivf.iam.views :as v]
    [ivf.iam.db :as d]
    [clj-time.core :as t]))

(defn wrap-user-session
  "crete :user key in request if user present"
  [h]
  (fn [{{sid :sid} :session :as req}]
    (h (if-let [s (and sid (d/get-sess sid))]
         (assoc req :user (:user s))
         req))))

(defn signin [req]
  (v/sign-in req {}))

(defn signin! [{prm :params :as req}]
  (if-let [sid (d/mk-sess prm)]
    (merge (rur/redirect "/") {:session {:sid sid}})
    (rur/redirect "/signin?error")))

(defn signout! [{{sid :sid} :session :as req}]
  (when sid (d/rm-sess sid))
  (merge (rur/redirect "/signin")
         {:session nil}))

(defn users [req]
  (let [usrs (d/list-usrs)]
    (v/users req {:users usrs})))

(defn new-user [req]
  (v/new-user req {}))

(defn mk-user [{{user :entity} :params :as req}]
  (if (:login user)
    (do
      (d/add-usr user)
      (rur/redirect "/users"))
    (rur/redirect "/users/new?error")))

(ns zero)

(defn keyify [s]
  (keyword  (.toLowerCase s)))

(defn wrap-method [h]
  (fn [req]
    (h (if-let [meth (get-in req [:params :_method])]
         (assoc req :request-method (keyify meth))
         req))))

(ns ivf.welcome.core
  (:require
    [ivf.views :refer [layout null-layout] :as iv]
    [ivf.i :refer [i]]))

(defn welcome [req]
  (layout (i :welcome) req
          [:h1 (i :welcome.header)]))

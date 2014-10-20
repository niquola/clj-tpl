(ns ivf.styles
  (:require [garden.core :as gc]))

(def center
  {:display "block"
   :margin "0 auto"})

(defn em [x] (str x "em"))
(defn px [x] (str x "px"))
(defn sq [& dim]
  (apply str (interleave dim (repeatedly (constantly " ")))))

(def large
  {:font-size (em 1.5)})

(def bordered
  {:border "1px solid #ddd"})

(def padded
  {:padding (em 1)})

(def login
  [:div.signin
   [:form.form-signin
    {:max-width (px 30)
     :padding (px 15)
     :border "1px solid #ddd"
     :marigin [0 "auto"]}
    [:.form-control
     {:position "relative"
      :height "auto"
      :box-sizing "border-box"
      :padding (px 10)
      :font-size (px 16)}]
    [:.form-control:focus :z-index 2 ]
    [:input [:type := :password]]]])

(def form
  [:form.form-horizontal
   {:background "#eee"
    :padding (sq 0 (px 20) (px 20) (px 20))
    :border "1px solid #ddd"
    :box-shadow "0px 0px 2px #ddd"
    :border-radius (px 5)}
   [:h1 :h2 :h3 {:border-bottom "1px solid #ddd"}]])

(def main
  [:body
   form
   [:div.signin
    {:margin-top (em 10)}
    login ]])

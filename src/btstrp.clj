(ns btstrp
  (:require [hiccup.core :as hc]))

(defn menu-item [url lbl]
  [:li [:a {:href url} lbl]])

(defn menu
  "opt
  * :brand
  * :left
  * :right
  "
  [opt]
  [:div.navbar {:role "navigation"}
   [:div.container-fluid
    [:div.navbar-header
     [:button.navbar-toggle.collapsed
      {:data-target ".navbar-collapse", :data-toggle "collapse", :type "button"}
      [:span.sr-only "Toggle navigation"]
      [:span.icon-bar]
      [:span.icon-bar]
      [:span.icon-bar]]
     [:a.navbar-brand {:href "/"} (:brand opt)]]
    [:div.navbar-collapse.collapse
     (into [:ul.nav.navbar-nav] (:left opt))
     (into [:ul.nav.navbar-nav.navbar-right] (:right opt))]]])

(defn mk-row
  ([[tag attrs & cnt]]
   (into [tag (assoc attrs
                     :class
                     (str "col-xs-" (:col attrs)))] cnt)))

(defn row [& cols]
  (for [col cols]
    (mk-row col)))

(defn table [heads & rows]
  [:table.table
   (for [h heads]
     [:th h])
   (for [cols rows]
     [:tr
      (for [c cols]
        [:td c])])])

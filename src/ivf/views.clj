(ns ivf.views
  (:require
    [hiccup.page :as hp]
    [hiccup.core :as hc]
    [ivf.i :refer [i]]
    [ivf.styles :as is]
    [btstrp :as b]
    [garden.core :as gc]))

(def nbsp "&nbsp;")

(defn post-link-to [meth href lbl]
  [:form {:style "display: inline;" :method :POST :action href}
   [:input {:type "hidden" :name "_method" :value meth}]
   [:button.btn.btn-default.navbar-btn {:type "submit"} lbl]])

(defn menu [req]
  (b/menu
    {:brand (i :brand)
     :left [(b/menu-item "/users" (i :menu.users))]
     :right (if (:user req)
              [[:li [:a (:name (:user req))]]
               [:li (post-link-to :DELETE "/signout" (i :signout.link))]]
              [[:li [:a {:href "/signin"} (i :signin.link)]]])}))

(defn form-row [lbl cnt]
  [:div.form-group
   [:label.col-sm-3.control-label.not-bold-label lbl]
   [:div.col-sm-9 cnt]])

(defn form-to [fo & cnt]
  (into [:form.form-horizontal
         {:method (:method fo)
          :action (:action fo)}]
        cnt))

(defn- mk-i-key [fo k]
  (if-let [i (:i fo)]
    (keyword (str (name i) "." (name k)))
    k))

(defn input [fo nm tp opt]
  (form-row (i (mk-i-key fo nm))
            [:input.form-control
             (merge {:type tp
                     :name (str "entity[" (name nm) "]")}
                    opt)]))

(defn null-layout [& [head body]]
  (hp/html5
    [:head
     [:meta  {:http-equiv "Content-type" :content "text/html; charset=utf-8"}]
     (hp/include-css
       "//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css")
     [:style (gc/css is/main)]
     head]
    [:body body ]))

(defn layout [title req & cnt]
  (null-layout
    [:title title]
    [:div.container
     (menu req)
     cnt]))

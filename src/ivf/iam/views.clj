(ns ivf.iam.views
  (:require
    [ivf.views :refer [layout null-layout] :as iv]
    [ivf.i :refer [i]]))

(defn sign-in [req data]
  (null-layout
    [:title (i :singnin)]
    [:div.container
     [:div.signin
      [:form.form-signin
       {:method :POST
        :action "/signin"
        :role "form"}
       [:h2.form-signin-heading (i :signin.title)]
       [:input.form-control
        {:type "text" :name "login" :placeholder (i :login)}]
       [:input.form-control
        {:type "password" :name "password" :placeholder (i :password)}]
       [:button.btn.btn-lg.btn-primary.btn-block
        {:type "submit"}  (i :signin.action)]]]]))

(defn new-user [req _]
  (layout
    (i :users.new) req
    (let [fo {:method :POST
              :action "/users"
              :i :users}]
      (iv/form-to
        fo
        [:h3 (i :users.new)]
        (iv/input fo :login :text {:required true})
        (iv/input fo :name :text {:required true})
        (iv/input fo :password :password {:required true})
        (iv/input fo :email :email {})
        [:div.row
         [:div.col-xs-3.pull-right
          [:button.btn.btn-primary.col-xs-12 (i :user.create)]]
         ]))))

(defn users [req {usrs :users}]
  (layout (i :users.title) req
          [:h3 (i :users.title)
           iv/nbsp
           [:a.btn.btn-success {:href "/users/new"} (i :users.new)]]
          [:hr]
          [:table.table
           [:thead
            [:th (i :users.login)]
            [:th (i :users.name)]
            [:th (i :users.password)]
            [:th (i :users.action)]]
           (for [u usrs]
             [:tr
              [:td (:login u)]
              [:td (:name u)]
              [:td (:password u)]
              [:td [:a {:href "/user/x"}]]])]))

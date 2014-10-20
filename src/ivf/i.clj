(ns ivf.i
  (:require [i :as i]))

(def strings
  {:signin "Войти"
   :brand "ЭКО"
   :action "Действие"
   :password "Пароль"
   :signout {:link "Выйти"}
   :new "Создать"
   :create "Создать"
   :menu {:users "Пользователи" }
   :users {:title "Пользователи"
           :login "Логин"
           :email "Почта"
           :name "Имя"
           :password "Пароль"}})

(def i (i/mk-i strings))

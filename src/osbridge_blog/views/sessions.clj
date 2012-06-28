(ns osbridge-blog.views.sessions
  (:require [osbridge-blog.views.common :as common]
            [noir.session :as session]
            [noir.request :as request])
  (:use noir.core
        osbridge-blog.views.helpers.bootstrap-forms
        [noir.response :only [redirect]]
        [hiccup.core :only [html]]
        [hiccup.form-helpers]))

(defn login? [user]
  (and (= "osbridge" (:username user)) (= "demo1" (:password user))))

; Login Form
(defpage "/login" []
  (if (common/logged_in?)
    (redirect "/posts/new")
    (common/site-layout
      [:h1 "Login"]
      (form :post "/login" "Enter your credentials to login to OSBridge Blog"
        (if-let [error (session/flash-get)]
          `([:br][:div.alert.alert-error [:strong "Error:"] " email or password is invalid"]))
        (form-input "text"     "Username" "username" nil)
        (form-input "password" "Password" "password" nil)
        [:div.form-actions
          [:button.btn.btn-primary {:type "submit"} "Login"] " "
          [:a.btn {:href "/"} "Cancel"]]))))

; Login Action
(defpage [:post "/login"] {:as user}
  (if (login? user)
    (do
      (session/put! :username (:username user))
      (redirect "/"))
    (do
      (session/flash-put! "email or password is invalid")
      (render "/login" user))))

;(session/put! :email (:email user)
; Logout action
(defpage "/logout" []
  (session/clear!)
  (redirect "/login"))

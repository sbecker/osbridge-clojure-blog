(ns osbridge-blog.views.common
  (:require [noir.session :as session])
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css include-js html5]]))

; (defpartial site-layout [& content]
;   (html5
;     [:head
;       [:title "OSBridge Clojure Blog"]]
;     [:body
;       [:div#wrapper
;         [:h1 "Getting Clojure"]
;         content]]))

; (defn logged_in? []
;   (if (nil? (session/get :username)) false true))

(defn logged_in? []
  (if (nil? (session/get :username)) false true))

(defpartial navlink [url, title]
  [:li [:a {:href url} title]])

(defpartial site-layout [& content]
            (html5
              [:head
               [:title "OSBridge Clojure Blog"]
               (include-css "/css/bootstrap.css"
                            "/css/screen.css"
                            "/css/bootstrap-responsive.css")
               (include-js "/js/bootstrap.js")]
              [:body
               [:div.navbar.navbar-fixed-top
                [:div.navbar-inner
                 [:div.container
                  [:a.btn.btn-navbar {:data-toggle "collapse" :data-target ".nav-collapse"}
                   [:span.icon-bar]
                   [:span.icon-bar]
                   [:span.icon-bar]]
                  [:a.brand "OSBridge Clojure Blog"]
                  [:div.class "nav-collapse"
                   [:ul.nav
                    (navlink "/" "Home")
                    (if (logged_in?)
                      (seq [(navlink "/posts/new" "New Post")
                            (navlink "/logout" "Logout")])
                      (seq [(navlink "/login" "Login")]))
                    ]]]]]
               [:div.container
                content]]))
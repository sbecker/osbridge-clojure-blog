(ns osbridge-blog.views.posts
  (:require [osbridge-blog.views.common :as common]
            [osbridge-blog.models.post :as post])
  (:use osbridge-blog.views.helpers.bootstrap-forms
        [noir.core :only [defpage defpartial]]
        [noir.response :only [redirect]]
        [hiccup.core :only [html]]
        [hiccup.form-helpers]))

;;;;;;;;; Partials

(def posts [{:id 1
             :title "First"
             :content "Hello"}
            {:id 2
             :title "Second"
             :content "Goodbye"}])


(defpartial post-item [{:keys [id title content]}]
  [:div.post {:id id}
    [:h3
      [:a {:href (str "/posts/" id)} title]]
    [:div content]])

(defpartial post-list [posts]
  [:div#posts
    (map post-item posts)])

;;;;;;;;; Pages

;; Post List
(defpage "/" []
  (common/site-layout
    (post-list (post/all))))

;; Single Post
(defpage "/posts/:id" {post-id :id}
  (common/site-layout
    (post-item (post/find-by-id post-id))))

;; New Post Form
(defpage "/posts/new" []
  (if (common/logged_in?)
    (common/site-layout
      [:h1 "New Blog Post"]
        (form :post "/posts" "What'll you blog today?"
          (form-input "text" "Title"    "title"    nil)
          (textarea-input "Content" "content" nil)
          [:div.form-actions
            [:button.btn.btn-primary {:type "submit"} "Create"] " "
            [:a.btn {:href "/"} "Cancel"]]))
    (redirect "/login")))


;; Create Post Action
(defpage [:post "/posts"] {:as post-params}
  (if (common/logged_in?)
    (do
      (post/create post-params)
      (redirect "/"))
    (redirect "/")))

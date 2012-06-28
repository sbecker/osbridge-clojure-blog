(ns osbridge-blog.models.post
  (:use [korma.core]
        [osbridge-blog.models.helpers]))

(defentity posts)

; Find all posts
(defn all []
  (select posts (order :id :desc)))

; Find a single post by an ID
(defn find-by-id [id]
  (first (select posts (where {:id (to-int id)}))))

; Create a post
(defn create [{:keys [title content] :as post}]
  (insert posts
    (values {:title   title
             :content content})))

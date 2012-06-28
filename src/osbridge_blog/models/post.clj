(ns osbridge-blog.models.post
  (:use [korma.core]
        [osbridge-blog.models.helpers]))

(defentity posts)

; Find by user id and uri
(defn all []
  (select posts (order :id :desc)))

(defn find-by-id [id]
  (first (select posts (where {:id (to-int id)}))))

(defn create [{:keys [title content] :as post}]
  (insert posts
    (values {:title   title
             :content content})))

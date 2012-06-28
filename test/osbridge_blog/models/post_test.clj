(ns osbridge-blog.models.post-test
  (:require [osbridge-blog.models.post :as post])
  (:use clojure.test)
  (:use midje.sweet))

(fact "post/all should return all posts"
  (post/all) => [{:id "1"
                  :title "First Post"
                  :body "First Post Body"}
                 {:id "2"
                  :title "Second Post"
                  :body "Second Post Body"}])

(fact "post/find-by-id should return a single post that matches the id"
  (post/find-by-id "1") => {:id "1"
                      :title "First Post"
                      :body "First Post Body"})
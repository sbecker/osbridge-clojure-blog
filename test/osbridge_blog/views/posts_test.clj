(ns osbridge-blog.views.posts-test
  (:require [osbridge-blog.views.posts :as posts])
  (:use [clojure.test]
        [midje.sweet]
        [noir.util.test]))

(defn body-contains
  "Returns true if body contains content"
  [content response]
  (= content (re-find (re-pattern content) (:body response))))

(fact "the homepage displays all posts"
  (let [response (send-request "/" {})]
    (:status response) => 200
    (body-contains "First Post" response) => true
    (body-contains "Second Post" response) => true))

(fact "the post displays a single post"
  (let [response (send-request "/posts/1" {})]
    (:status response) => 200
    (body-contains "First Post" response) => true
    (body-contains "Second Post" response) => false))

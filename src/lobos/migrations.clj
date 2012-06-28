(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers)))

; Add initial users table
(defmigration add-posts-table
  (up [] (create
          (tbl :posts
            (varchar :title 200 :unique)
            (text :content))))
  (down [] (drop (table :posts))))
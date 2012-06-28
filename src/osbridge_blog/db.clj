(ns osbridge-blog.db
  (:use korma.db)
  (:require [clojure.string :as str]
            [osbridge-blog.core :as core])
  (:import (java.net URI)))

(defn url->resource [url-str]
  (let [url (URI. url-str)
        host (.getHost url)
        port (if (pos? (.getPort url)) (.getPort url) 5432)
        path (.getPath url)]

    (merge
     {:subname (str "//" host ":" port path)
      :classname "com.postgresql.Driver"
      :subprotocol "postgresql"}
      (if-let [user-info (.getUserInfo url)]
        {:user (first (str/split user-info #":"))
         :password (second (str/split user-info #":"))}))))

(defn conn-info []
  (url->resource (:db-url core/config)))

(defn connect! []
  (println (conn-info))

  (defdb appdb (conn-info)))

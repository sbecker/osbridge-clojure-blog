(ns osbridge-blog.server
  (:use [clojure.tools.cli :only [cli]])
  (:require [osbridge-blog.core :as core]
            [osbridge-blog.db :as db]
            [noir.server :as server]))

(defn -main [& args]

  (let [[{:keys [help mode port] :as opts} args banner]
        (cli args
             ["-h" "--help" "Show help" :default false :flag true]
             ["--mode" "mode, production or dev?" :default :dev :parse-fn keyword]
             ["--port" "Webserver port"
              :default (Integer. (get (System/getenv) "PORT" "8081"))
              :parse-fn #(Integer. %)]

             ["--password-salt"
              :default (get (System/getenv) "PASSWORD_SALT"
                            "$2a$10$pX8qxGZie49BQ27tKGALVeikmJb//TNAC2un7j.3aHhk45Uhdxncy")]

             ["--db-url" "Database URL"
              :default (get (System/getenv) "DATABASE_URL"
                            "postgresql://localhost/osbridge_blog_dev")])]

    (println "opts:")
    (println opts)

    (when help
      (println banner)
      (System/exit 0))

    (core/update-config! opts)
    (db/connect!)

    (server/load-views "src/osbridge_blog/views/")

    (server/start port {:mode mode
                        :ns 'osbridge-blog})))


; (defdb db (postgres {:db "osbridge_blog_dev"}))
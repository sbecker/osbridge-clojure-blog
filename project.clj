
(defproject osbridge-blog "0.1.0-SNAPSHOT"
            :description "A demo blog for Open Source Bridge"

            :dependencies [[org.clojure/clojure "1.3.0"]
                           [org.clojure/tools.cli "0.2.1"]
                           [noir "1.2.1"]
                           [postgresql "9.1-901.jdbc4"]
                           [korma "0.3.0-beta10"]
                           [lobos "1.0.0-SNAPSHOT"]]

            :dev-dependencies [[midje "1.3.1"]
                               [lein-midje "1.0.9"]
                               [com.stuartsierra/lazytest "1.2.3"]
                               [lein-lobos "0.8.0-SNAPSHOT"]]

            :main osbridge-blog.server)

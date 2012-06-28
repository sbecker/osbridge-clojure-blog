(ns osbridge-blog.core) ;;Contains shared configuration

;;Filled in by defaults in -main
(def ^:dynamic config {})

(defn update-config! [new-config]
  (def config new-config))

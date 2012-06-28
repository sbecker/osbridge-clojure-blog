(ns lobos.config
  (:use osbridge-blog.db
        lobos.connectivity))

(def db (url->resource (get (System/getenv) "DATABASE_URL"
                                            "postgresql://localhost/osbridge_blog_dev")))

; This line causes an error to throw - "A global connection by that name already exists" - maybe lein-lobos does this for you.
; (open-global db)
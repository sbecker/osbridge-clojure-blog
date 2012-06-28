(ns osbridge-blog.models.helpers)

(defn to-int [val]
  (if (string? val) (Integer/parseInt val) val))


(ns zk-web.conf
  (:require [clojure.java.io :as io])
  (:import [java.io File PushbackReader]))

(defn- valid-conf-file?
  "Check if a file exists and is a normal file"
  [path]
  (let [file (File. path)]
    (and (.exists file)
         (.isFile file))))

(defn- load-conf-file [path]
  (when (valid-conf-file? path)
    (read-string (slurp path :encoding "utf-8"))))

(defn load-conf []
  "load the config from ~/.zk-web-conf.clj or conf/zk-web-conf.clj"
  (let [home-conf (str (get (System/getenv) "HOME") File/separator ".zk-web-conf.clj")
        pwd-conf "conf/zk-web-conf.clj"]
    (or (load-conf-file home-conf) (load-conf-file pwd-conf)
        {:server-port (or (get (System/getenv) "ZKWEB_SERVER_PORT")
                          8080)
         :users {"admin"
                 (or (get (System/getenv) "ZKWEB_ADMIN_PASSWORD")
                     "hello")}
         :default-node (get (System/getenv) "ZKWEB_DEFAULT_NODE")})))
